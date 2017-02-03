package com.jon.pollApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LaunchPoll extends Activity{
	
	// Progress Dialog
		private ProgressDialog pDialog;

		// Creating JSON Parser object
		JSONParser jParser = new JSONParser();

		ArrayList<HashMap<String, String>> pollitemsList;
		
		String id, type_id,poll_code, poll_desc, poll_date;

		// url to get all polls list
		//private static String url_all_products = "http://10.0.2.2/android_connect/get_all_products.php";

		// JSON Node names
		private static final String TAG_SUCCESS = "success";
		private static final String TAG_POLLS = "polls";
		private static final String TAG_ID = "id";
		private static final String TAG_TYPEID = "type_id";
		private static final String TAG_POLLCODE = "poll_code";
		private static final String TAG_POLLDESC = "poll_desc";
		private static final String TAG_DATE = "date_created";

		// polls JSONArray
		JSONArray polls = null;
		
		private Button btnAddNewCategory;
		private Button btnSubmit;
		private TextView txtPollCode;
		private Spinner spinnerPollCode;
		// array list for spinner adapter
		private ArrayList<Category> pollcodeList;
		
		ArrayAdapter<String> spinnerAdapter;
		
		List<String> lables = new ArrayList<String>();
		
		Cursor cursor;

		//int type_id;
		
		private String URL_POLLCODE = "http://www.masterclass.co.ke/projects/mypoll/polls.php";

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);

			// Hashmap for ListView
			pollitemsList = new ArrayList<HashMap<String, String>>();

			// Loading polls in Background Thread
			
			
			btnAddNewCategory = (Button) findViewById(R.id.btnAddNewCategory);
			
			spinnerPollCode = (Spinner) findViewById(R.id.spinFood);
			txtPollCode = (TextView) findViewById(R.id.txtCategory);
			
			//pollcodeList = new ArrayList<Category>();
			
			lables.add(poll_code);
			
			btnAddNewCategory.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					
						Toast.makeText(getApplicationContext(),
								"Getting PollCodes", Toast.LENGTH_SHORT)
								.show();
						new LoadAllProducts().execute();
						
						Toast.makeText(getApplicationContext(),
								"Getting PollCodes", Toast.LENGTH_SHORT)
								.show();
				}
			});


		}
		
		

		/**
		 * Background Async Task to Load all product by making HTTP Request
		 * */
		class LoadAllProducts extends AsyncTask<String, String, String> {

			/**
			 * Before starting background thread Show Progress Dialog
			 * */
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				pDialog = new ProgressDialog(LaunchPoll.this);
				pDialog.setMessage("Loading polls. Please wait...");
				pDialog.setIndeterminate(false);
				pDialog.setCancelable(false);
				pDialog.show();
			}

			/**
			 * getting All polls from url
			 * */
			protected String doInBackground(String... args) {
				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				// getting JSON string from URL
				JSONObject json = jParser.makeHttpRequest(URL_POLLCODE, "GET", params);
				
				// Check your log cat for JSON reponse
				Log.d("All Polls: ", json.toString());

				try {
					// Checking for SUCCESS TAG
					int success = json.getInt(TAG_SUCCESS);

					if (success == 1) {
						// polls found
						// Getting Array of Products
						polls = json.getJSONArray(TAG_POLLS);

						// looping through All Products
						for (int i = 0; i < polls.length(); i++) {
							JSONObject c = polls.getJSONObject(i);

							// Storing each json item in variable
							id = c.getString(TAG_ID);
							type_id = c.getString(TAG_TYPEID);
							poll_code = c.getString(TAG_POLLCODE);
							poll_desc = c.getString(TAG_POLLDESC);
							poll_date = c.getString(TAG_DATE);
							// creating new HashMap
							HashMap<String, String> map = new HashMap<String, String>();

							// adding each child node to HashMap key => value
							map.put(TAG_ID, id);
							map.put(TAG_TYPEID, type_id);
							map.put(TAG_POLLCODE, poll_code);
							map.put(TAG_POLLDESC, poll_desc);
							map.put(TAG_DATE, poll_date);

							// adding HashList to ArrayList
							pollitemsList.add(map);
							
							
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}

				return null;
			}

			/**
			 * After completing background task Dismiss the progress dialog
			 * **/
			protected void onPostExecute(String file_url) {
				// dismiss the dialog after getting all polls
				pDialog.dismiss();
				// updating UI from Background Thread
				runOnUiThread(new Runnable() {
					public void run() {
						
					}
				});

			}

		}
		
		public void onItemSelected(AdapterView<?> parent, View view, int position,
				long id) 
		{
			int type_id = Integer.parseInt(TAG_POLLCODE);
			
			switch (position)
	        {
			case 0:
				
				Intent m = new Intent(getApplication(),Option2.class);
				startActivity(m);
				
				Toast.makeText(
	    				getApplicationContext(),
	    						String.valueOf(type_id).toString() + " Selected Redirecting..." ,
	    				Toast.LENGTH_LONG).show();
				
	        	if(type_id==5)
	        	{
	        		Toast.makeText(
	        				getApplicationContext(),
	        						parent.getItemAtPosition(position).toString() + " Selected Redirecting..." ,
	        				Toast.LENGTH_LONG).show();
	        		Intent i = new Intent(getApplication(),Option1.class);
	        		startActivity(i);
	        		
				}
	        	else if(type_id==6)
	        	{
	        		Toast.makeText(
	        				getApplicationContext(),
	        						parent.getItemAtPosition(position).toString() + " Selected Redirecting..." ,
	        				Toast.LENGTH_LONG).show();
	        		Intent i = new Intent(getApplication(),Option2.class);
	    			startActivity(i);
	    			
	        	}
	        	else if(type_id==7)
	        	{
	        		Toast.makeText(
	        				getApplicationContext(),
	        						parent.getItemAtPosition(position).toString() + " Selected Redirecting..." ,
	        				Toast.LENGTH_LONG).show();
	        		Intent i = new Intent(getApplication(),Option3.class);
	    			startActivity(i);
	    			
	        	}
	        	else if(type_id==8)
	        	{
	        		Toast.makeText(
	        				getApplicationContext(),
	        						parent.getItemAtPosition(position).toString() + " Selected Redirecting..." ,
	        				Toast.LENGTH_LONG).show();
	        		Intent i = new Intent(getApplication(),Option4.class);
	    			startActivity(i);
	    			
	        	}
				
	        	break;
	        	
				case 1:
					
					Toast.makeText(
		    				getApplicationContext(),
		    						String.valueOf(type_id).toString() + " Selected Redirecting..." ,
		    				Toast.LENGTH_LONG).show();
					
	        	
	        	if(type_id==5)
	        	{
	        		Toast.makeText(
	        				getApplicationContext(),
	        						parent.getItemAtPosition(position).toString() + " Selected Redirecting..." ,
	        				Toast.LENGTH_LONG).show();
	        		Intent i = new Intent(getApplication(),Option1.class);
	        		startActivity(i);
	        		
				}
	        	else if(type_id==6)
	        	{
	        		Toast.makeText(
	        				getApplicationContext(),
	        						parent.getItemAtPosition(position).toString() + " Selected Redirecting..." ,
	        				Toast.LENGTH_LONG).show();
	        		Intent i = new Intent(getApplication(),Option2.class);
	    			startActivity(i);
	    			
	        	}
	        	else if(type_id==7)
	        	{
	        		Toast.makeText(
	        				getApplicationContext(),
	        						parent.getItemAtPosition(position).toString() + " Selected Redirecting..." ,
	        				Toast.LENGTH_LONG).show();
	        		Intent i = new Intent(getApplication(),Option3.class);
	    			startActivity(i);
	    			
	        	}
	        	else if(type_id==8)
	        	{
	        		Toast.makeText(
	        				getApplicationContext(),
	        						parent.getItemAtPosition(position).toString() + " Selected Redirecting..." ,
	        				Toast.LENGTH_LONG).show();
	        		Intent i = new Intent(getApplication(),Option3.class);
	    			startActivity(i);
	    			
	        	}
				
	        	break;
				}
			}	

		public void onNothingSelected(AdapterView<?> parent) {		
		}

	

}
