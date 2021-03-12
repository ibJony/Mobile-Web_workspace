package com.jon.mypollfinal;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class LaunchPoll extends Activity{
	
	// Progress Dialog
		private ProgressDialog pDialog;

		// Creating JSON Parser object
		JSONParser jParser = new JSONParser();

		ArrayList<HashMap<String, String>> pollitemsList;

		// url to get all polls list
		private static String url_all_products = "http://10.0.2.2/android_connect/get_all_products.php";

		// JSON Node names
		private static final String TAG_SUCCESS = "success";
		private static final String TAG_POLLS = "polls";
		private static final String TAG_PID = "pid";
		private static final String TAG_POLLID = "pollid";
		private static final String TAG_POLLCODE = "pollcode";
		private static final String TAG_POLLRESPONSE = "pollresponse";
		private static final String TAG_POLLCOMMENT = "pollcomment";
		private static final String TAG_POLLMOPTION = "pollmoption";

		// polls JSONArray
		JSONArray polls = null;
		
		private Button btnAddNewCategory;
		private Button btnSubmit;
		private TextView txtPollCode;
		private Spinner spinnerPollCode;
		// array list for spinner adapter
		ArrayAdapter<String> spinnerAdapter;
		
		Cursor cursor;

		int type_id;
		
		private String URL_POLLCODE = "http://www.masterclass.co.ke/projects/mypoll/getCategories.php";

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.all_products);

			// Hashmap for ListView
			pollitemsList = new ArrayList<HashMap<String, String>>();

			// Loading polls in Background Thread
			new LoadAllProducts().execute();


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
				JSONObject json = jParser.makeHttpRequest(url_all_products, "GET", params);
				
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
							String id = c.getString(TAG_PID);
							String pollid = c.getString(TAG_POLLID);
							String pollcode = c.getString(TAG_POLLCODE);
							String pollresponse = c.getString(TAG_POLLRESPONSE);
							String pollcomment = c.getString(TAG_POLLCOMMENT);
							String pollmoption = c.getString(TAG_POLLMOPTION);
							// creating new HashMap
							HashMap<String, String> map = new HashMap<String, String>();

							// adding each child node to HashMap key => value
							map.put(TAG_PID, id);
							map.put(TAG_POLLID, pollid);
							map.put(TAG_POLLCODE, pollcode);
							map.put(TAG_POLLRESPONSE, pollresponse);
							map.put(TAG_POLLCOMMENT, pollcomment);
							map.put(TAG_POLLMOPTION, pollmoption);

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

}
