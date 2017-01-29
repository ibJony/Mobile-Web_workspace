package com.jon.talii;

import java.util.*;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.jon.*;
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
	String category;
	
	//private MenuItem refreshMenuItem;

	// Url to create new update
	private String URL_NEW_UPDATE = "http://10.0.2.2/supra_api/new_update.php";
	
	private String URL_NEW_UPDATE1 = "http://10.0.2.2/supra_api/new_update1.php";
	
	private String URL_UPDATE = "http://10.0.2.2/supra_api/get_update.php";

	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.place_item);
		
		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) 
			{
				 
				 Toast.makeText(getApplicationContext(), "You selected "+(CharSequence) arg0.getItemAtPosition(arg2), Toast.LENGTH_SHORT).show();
				 	category = spinner.getSelectedItem().toString();		
			}
			
				public void onNothingSelected(AdapterView<?> arg0) {

				 	}
				 });
		
		btnpost.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				if (txttitle.getText().toString().trim().length() > 0 && txtnew.getText().toString().trim().length() > 0 
						/*&& txtdate.getText().toString().trim().length() > 0 && txtdateat.getText().toString().trim().length() > 0*/) {
					
					if(category.equals("News and Updates")){
						

					// Call Async task to create new category
					new AddNewUpdate().execute();
					
					Toast.makeText(getApplicationContext(), "Updated successful", Toast.LENGTH_SHORT).show();
					
											
					}				
				}else{
					Toast.makeText(getApplicationContext(),
							"Please enter Title, description and category of information you wsh to post", Toast.LENGTH_SHORT)
							.show();
				}	
			}
		});
		//new GetUpdates().execute();
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
			return null;
			

			//ServiceHandler serviceClient = new ServiceHandler();

			//String json = serviceClient.makeServiceCall(URL_NEW_UPDATE,
				//	ServiceHandler.POST, params);

//			Log.d("Create Response: ", "> " + json);
//
//			if (json != null) {
//				try {
//					JSONObject jsonObj = new JSONObject(json);
//					boolean error = jsonObj.getBoolean("error");
//					// checking for error node in json
//					if (!error) {	
//						// new category created successfully
//						isNewUpdateCreated = true;						
//					} else {
//						Log.e("Create Update Error: ", "> " + jsonObj.getString("message"));
//					}
//
//				} catch (JSONException e) {
//					e.printStackTrace();
//				}
//
//			} else {
//				Log.e("JSON Data", "Didn't receive any data from server!");
//			}
//
//			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			if (pDialog.isShowing())
				pDialog.dismiss();
			if (isNewUpdateCreated) {
				runOnUiThread(new Runnable() {
					public void run() {
						
					 	category = spinner.getSelectedItem().toString();
						
						// fetching all categories
						//new DataActivity.GetUpdates().execute();
						txttitle.setText("");
						txtnew.setText("");
					}
				});
			}
		}
	}
	
	private class AddNewUpdate1 extends AsyncTask<String, Void, Void> {

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
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			if (pDialog.isShowing())
				pDialog.dismiss();
			if (isNewUpdateCreated) {
				runOnUiThread(new Runnable() {
					public void run() {
						// fetching all categories
						//new DataActivity.GetUpdates().execute();
						txttitle.setText("");
						txtnew.setText("");
					}
				});
			}
		}

		@Override
		protected Void doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			return null;
		}
	}
}

