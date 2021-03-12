package com.jon.mypollfinal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class AllProductsActivity extends ListActivity {

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
	private static final String TAG_NAME = "name";
	private static final String TAG_POLLID = "pollid";
	private static final String TAG_POLLCODE = "pollcode";
	private static final String TAG_POLLRESPONSE = "pollresponse";
	private static final String TAG_POLLCOMMENT = "pollcomment";
	private static final String TAG_POLLMOPTION = "pollmoption";

	// polls JSONArray
	JSONArray polls = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.all_products);

		// Hashmap for ListView
		pollitemsList = new ArrayList<HashMap<String, String>>();

		// Loading polls in Background Thread
		new LoadAllProducts().execute();

		// Get listview
		ListView lv = getListView();

		// on seleting single product
		// launching Edit Product Screen
		/*lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// getting values from selected ListItem
				String pid = ((TextView) view.findViewById(R.id.pid)).getText()
						.toString();

				// Starting new intent
				Intent in = new Intent(getApplicationContext(),
						EditProductActivity.class);
				// sending pid to next activity
				in.putExtra(TAG_PID, pid);
				
				// starting new activity and expecting some response back
				startActivityForResult(in, 100);
			}
		});*/

	}

	/*
	// Response from Edit Product Activity
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		// if result code 100
		if (resultCode == 100) {
			// if result code 100 is received 
			// means user edited/deleted product
			// reload this screen again
			Intent intent = getIntent();
			finish();
			startActivity(intent);
		}

	}*/

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
			pDialog = new ProgressDialog(AllProductsActivity.this);
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
						String name = c.getString(TAG_NAME);
						String pollid = c.getString(TAG_POLLID);
						String pollcode = c.getString(TAG_POLLCODE);
						String pollresponse = c.getString(TAG_POLLRESPONSE);
						String pollcomment = c.getString(TAG_POLLCOMMENT);
						String pollmoption = c.getString(TAG_POLLMOPTION);
						// creating new HashMap
						HashMap<String, String> map = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						map.put(TAG_PID, id);
						map.put(TAG_NAME, name);
						map.put(TAG_POLLID, pollid);
						map.put(TAG_POLLCODE, pollcode);
						map.put(TAG_POLLRESPONSE, pollresponse);
						map.put(TAG_POLLCOMMENT, pollcomment);
						map.put(TAG_POLLMOPTION, pollmoption);

						// adding HashList to ArrayList
						pollitemsList.add(map);
						/*	}
				} else {
					// no polls found
					// Launch Add New product Activity
					Intent i = new Intent(getApplicationContext(),
							NewProductActivity.class);
					// Closing all previous activities
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
				}*/
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
					/**
					 * Updating parsed JSON data into ListView
					 * */
					ListAdapter adapter = new SimpleAdapter(
							AllProductsActivity.this, pollitemsList,
							R.layout.list_item, new String[] { TAG_PID,
									TAG_NAME},
							new int[] { R.id.pid, R.id.name });
					// updating listview
					setListAdapter(adapter);
				}
			});

		}

	}
}