package com.jon.jonapp.gcm.push;

import static com.jon.jonapp.gcm.push.CommonUtilities.SENDER_ID;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gcm.GCMRegistrar;
import com.jon.jonapp.PollCodeActivity;

public class MainJonAppActivity extends Activity {

	private static final String TAG_SUCCESS = "success";
	private String URL_NEW_UPDATE = "http://www.masterclass.co.ke/projects/mypoll/user_reg.php";
	JSONParser jsonParser = new JSONParser();
	
	private String TAG = "** MyPollTest**";
	//private TextView mDisplay;
	String regId="",sn;
	
	EditText txtEmail;
	EditText txtPollCode;
	Button btnRegister;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		checkNotNull(SENDER_ID, "SENDER_ID");
		GCMRegistrar.checkDevice(this);
		GCMRegistrar.checkManifest(this);

		
		new sendIdOnOverServer().execute();
		
		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		sn = telephonyManager.getDeviceId();

		regId = GCMRegistrar.getRegistrationId(this);
		
		txtPollCode = (EditText) findViewById(R.id.txtDPollCode);
		txtEmail = (EditText) findViewById(R.id.txtEmail);
		btnRegister = (Button) findViewById(R.id.btnRegister);

		if (regId.equals("")) {
			GCMRegistrar.register(this, SENDER_ID);
			
		} else {
			Log.v(TAG, "Already registered");
		}
		
		btnRegister.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				//Toast.makeText(getApplicationContext(),regId, Toast.LENGTH_LONG).show();
				
				if(txtEmail.length() > 0 && txtPollCode.length() > 0 && regId!=""){
					
					//Toast.makeText(getApplicationContext(),regId, Toast.LENGTH_LONG).show();
					new CreateUser().execute();
					
				}else{

					// ask him to fill the form
					Toast.makeText(getApplicationContext(), "Registration Error!"+ "Please enter your details", Toast.LENGTH_LONG).show();
				}
			}
		});
		
	}

	private void checkNotNull(Object reference, String name) {
		if (reference == null) {
			throw new NullPointerException(getString(R.string.error_config,
					name));
		}
		
		
	}

	@Override
	protected void onPause() {
		super.onPause();
		GCMRegistrar.unregister(this);
	}

	public class sendIdOnOverServer extends AsyncTask<String, Void, String> {

		ProgressDialog pd = null;

		@Override
		protected void onPreExecute() {
			pd = ProgressDialog.show(MainJonAppActivity.this, "Please wait",
					"Loading please wait..", true);
			pd.setCancelable(true);

		}

		@Override
		protected String doInBackground(String... params) {
			try {
				
				HttpResponse response = null;
				HttpParams httpParameters = new BasicHttpParams();
				HttpClient client = new DefaultHttpClient(httpParameters);
				String url="http://www.masterclass.co.ke/projects/mypoll/user_reg.php" + "&regID="+ regId;
				Log.i("Send URL:", url);
				HttpGet request = new HttpGet(url);
				
				response = client.execute(request);

				
				
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent()));

				String webServiceInfo = "";
				while ((webServiceInfo = rd.readLine()) != null) {
					Log.d("****Status Log***", "Webservice: " + webServiceInfo);
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;

		}

		@Override
		protected void onPostExecute(String result) {
				//mDisplay.setText("RegId=" + regId);
				pd.dismiss();
				Toast.makeText(getApplicationContext(), regId, Toast.LENGTH_LONG).show();
			}
		}
	
	class CreateUser extends AsyncTask<String, String, String> {

		ProgressDialog pd = null;
		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pd = new ProgressDialog(MainJonAppActivity.this);
			pd.setMessage("Submitting Polls..");
			pd.setCancelable(true);
			pd.show();
		}

		/**
		 * Creating product
		 * */
		@Override
		protected String doInBackground(String... args) {
			
			String imeicode = sn;
			String gcm_regid = regId;
			String email = txtEmail.getText().toString();
			String pollcode = txtPollCode.getText().toString();

			// Building Parameters
			List<NameValuePair> params1 = new ArrayList<NameValuePair>();
			params1.add(new BasicNameValuePair("gcm_regid", gcm_regid));
			params1.add(new BasicNameValuePair("email", email));
			params1.add(new BasicNameValuePair("default_poll_code", pollcode));
			params1.add(new BasicNameValuePair("imei_code", imeicode));
			
			
			JSONObject json = jsonParser.makeHttpRequest(URL_NEW_UPDATE,
					"POST", params1);
			
			// check log cat fro response
			Log.d("Create Response", json.toString());

			// check for success tag
			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					
					Intent i = new Intent(getApplicationContext(),PollCodeActivity.class);
					startActivity(i);
					// successfully created product
					
				} else {
					// failed to create product
				}
				
				
				
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}
		
		@Override
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			
			Toast.makeText(getApplicationContext(),"User created Successfully" ,Toast.LENGTH_LONG).show();
			
			//pDialog.dismiss();
			finish();
			
		}

	}
}
