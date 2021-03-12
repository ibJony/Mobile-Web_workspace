package com.jon.supra.fxn;

import java.util.*;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.supra.R;
import com.jon.supra.db.ServiceHandler;
import com.jon.supra.db.Updata;

import android.app.*;
import android.content.Intent;
import android.os.*;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;

public class PostActivity extends Activity{
	
	private Button btnpost;
	private TextView txttitle,txtnew,txtdate,txtdateat;
	private Spinner spinner;
	ProgressDialog pDialog;
	

	// Url to create new update
	private String URL_NEW_UPDATE = "http://10.0.2.2/supra_api/new_update.php";
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post);
		
		btnpost = (Button)findViewById(R.id.btnpost);
		txttitle = (TextView)findViewById(R.id.txttitle);
		txtnew = (TextView)findViewById(R.id.txtnew);
		txtdate = (TextView)findViewById(R.id.created_at);
		txtdateat = (TextView)findViewById(R.id.updated_at);
		
		spinner = (Spinner)findViewById(R.id.spinner1);
		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) 
			{
				 
					
			}
			
				public void onNothingSelected(AdapterView<?> arg0) {

				 	}
				 });
		
		btnpost.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (txttitle.getText().toString().trim().length() > 0 && txtnew.getText().toString().trim().length() > 0 
						/*&& txtdate.getText().toString().trim().length() > 0 && txtdateat.getText().toString().trim().length() > 0*/) {
					

					// Call Async task to create new category
					new AddNewUpdate().execute();
					
					Toast.makeText(getApplicationContext(), "Updated successful", Toast.LENGTH_SHORT).show();
					
					Intent i = new Intent(getApplicationContext(),TabsListView.class);
					startActivity(i);
					
					
					Toast.makeText(getApplicationContext(),
							"Please enter Title, description and category of information you wsh to post", Toast.LENGTH_SHORT)
							.show();
				}	
			}
		});
	}
		
	private class AddNewUpdate extends AsyncTask<String, Void, Void> {

		boolean isNewUpdateCreated = false;

		@Override
		protected void onPreExecute() {			
			super.onPreExecute();
			pDialog = new ProgressDialog(PostActivity.this);
			pDialog.setMessage("Creating new update...");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected Void doInBackground(String... arg) {
			
			String title = txttitle.getText().toString();
			String upnew = txtnew.getText().toString();

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("title", title));
			params.add(new BasicNameValuePair("descrip", upnew));
			

			ServiceHandler serviceClient = new ServiceHandler();

			String json = serviceClient.makeServiceCall(URL_NEW_UPDATE,
					ServiceHandler.POST, params);

			Log.d("Create Response: ", "> " + json);

			if (json != null) {
				try {
					JSONObject jsonObj = new JSONObject(json);
					boolean error = jsonObj.getBoolean("error");
					// checking for error node in json
					if (!error) {	
						// new category created successfully
						isNewUpdateCreated = true;						
					} else {
						Log.e("Create Update Error: ", "> " + jsonObj.getString("message"));
					}

				} catch (JSONException e) {
					e.printStackTrace();
				}

			} else {
				Log.e("JSON Data", "Didn't receive any data from server!");
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			if (pDialog.isShowing())
				pDialog.dismiss();
			if (isNewUpdateCreated) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						
						
						// fetching all categories
						//new DataActivity.GetUpdates().execute();
						txttitle.setText("");
						txtnew.setText("");
					}
				});
			}
		}
	}
	
}

