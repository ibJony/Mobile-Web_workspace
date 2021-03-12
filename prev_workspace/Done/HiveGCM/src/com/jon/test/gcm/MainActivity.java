package com.jon.test.gcm;

import static com.jon.test.gcm.CommonUtilities.DISPLAY_MESSAGE_ACTION;
import static com.jon.test.gcm.CommonUtilities.EXTRA_MESSAGE;
import static com.jon.test.gcm.CommonUtilities.SENDER_ID;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.hivegcm.pushnotifications.R;
import com.google.android.gcm.GCMRegistrar;

public class MainActivity extends Activity {
	// label to display gcm messages
	TextView lblMessage;
	// Asyntask
	
	String regId;
	
	AsyncTask<Void, Void, Void> mRegisterTask;
	
	// Alert dialog manager
	AlertDialogManager alert = new AlertDialogManager();
	
	// Connection detector
	ConnectionDetector cd;
	
	public static String poll_code;
	public static String email;
	public static String imei;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		imei = telephonyManager.getDeviceId().toString();
		
		cd = new ConnectionDetector(getApplicationContext());

		// Check if Internet present
		if (!cd.isConnectingToInternet()) {
			// Internet Connection is not present
			alert.showAlertDialog(MainActivity.this,
					"Internet Connection Error",
					"Please connect to working Internet connection", false);
			// stop executing code by return
			return;
		}
		
		// Getting name, email from intent
		Intent i = getIntent();
		
		poll_code = i.getStringExtra("poll_code");
		email = i.getStringExtra("email");		
		
		// Make sure the device has the proper dependencies.
		GCMRegistrar.checkDevice(this);

		// Make sure the manifest was properly set - comment out this line
		// while developing the app, then uncomment it when it's ready.
		GCMRegistrar.checkManifest(this);

		lblMessage = (TextView) findViewById(R.id.lblMessage);
		
		registerReceiver(mHandleMessageReceiver, new IntentFilter(
				DISPLAY_MESSAGE_ACTION));
		
		// Get GCM registration id
		regId = GCMRegistrar.getRegistrationId(this);
		
		registerGSM();

		// Check if regid already presents
		if (regId.equals("")) {
			// Registration is not present, register now with GCM			
			GCMRegistrar.register(this, SENDER_ID);

		} else {
			// Device is already registered on GCM
			if (GCMRegistrar.isRegisteredOnServer(this)) {
				// Skips registration.				
				Toast.makeText(getApplicationContext(), "Already registered with GCM", Toast.LENGTH_LONG).show();
			} else {

				final Context context = this;
				mRegisterTask = new AsyncTask<Void, Void, Void>() {

					@Override
					protected Void doInBackground(Void... params) {
						// Register on our server
						ServerUtilities.register(context, poll_code, email,imei, regId);
						
						return null;
					}

					@Override
					protected void onPostExecute(Void result) {
						
						mRegisterTask = null;
					}

				};
				mRegisterTask.execute(null, null, null);
			}
		}
	}		

	public void registerGSM(){
		
		 cd = new ConnectionDetector(getApplicationContext());

			// Check if Internet present
			if (!cd.isConnectingToInternet()) {
				// Internet Connection is not present
				alert.showAlertDialog(MainActivity.this,
						"Internet Connection Error",
						"Please connect to working Internet connection", false);
				// stop executing code by return
				return;
			}
			
			// Make sure the device has the proper dependencies.
			GCMRegistrar.checkDevice(this);
			GCMRegistrar.checkManifest(this);
			final String regId = GCMRegistrar.getRegistrationId(this);
			if (regId.equals("")) {
				// Registration is not present, register now with GCM			
				GCMRegistrar.register(this, SENDER_ID);
			} else {
				// Device is already registered on GCM
				if (GCMRegistrar.isRegisteredOnServer(this)) {
					final Context context = this;
					
					
				} else {
					
					final Context context = this;
					mRegisterTask = new AsyncTask<Void, Void, Void>() {

						@Override
						protected Void doInBackground(Void... params) {
							
							// Register on our server
							ServerUtilities.register(context, poll_code, email,imei, regId);
							return null;
							
						}

						@Override
						protected void onPostExecute(Void result) {
							
							mRegisterTask = null;
						}

					};
					mRegisterTask.execute(null, null, null);
				}
			}
		 
	 }
	
	private final BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String newMessage = intent.getExtras().getString(EXTRA_MESSAGE);
			// Waking up mobile if it is sleeping
			WakeLocker.acquire(getApplicationContext());
			
			// Showing received message
			lblMessage.append(newMessage + "\n");			
			Toast.makeText(getApplicationContext(), "New Message: " + newMessage, Toast.LENGTH_LONG).show();
			
			// Releasing wake lock
			WakeLocker.release();
		}
	};
	
	
}
