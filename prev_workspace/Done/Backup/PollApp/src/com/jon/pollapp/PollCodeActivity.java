package com.jon.pollapp;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jon.pollApp.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PollCodeActivity extends Activity implements OnItemSelectedListener {

			// Progress Dialog
			private ProgressDialog pDialog;

			// Creating JSON Parser object
			JSONParser jParser = new JSONParser();
			public static final String TAG_TYPEID = "type_id";
			
			private Button btnAddNewCategory;
			private Button btnSubmit;
			private TextView txtPollCode;
			private Spinner spinnerPollCode;
			
			ArrayAdapter<String> spinnerAdapter;
			
			private String URL_POLLCODE = "http://www.masterclass.co.ke/projects/mypoll/polls.php";
			
			ArrayList<Category> pollcodeList = new ArrayList<Category>();
			
			// JSON Node names
						public static final String TAG_SUCCESS = "success";
						public static final String TAG_POLLS = "polls";
						public static final String TAG_POLLCODE = "poll_code";
						public static final String TAG_PROMPT = "Select Pollcode";
						//String id, type_id,poll_code, poll_desc, date_created;
						
						JSONArray polls = null;
						
						TextView tx;
						TextView tx1;
						
						List<String> lables = new ArrayList<String>();
						List<Integer> lables1 = new ArrayList<Integer>();
						List<Integer> lables2 = new ArrayList<Integer>();
						String code;
						String typeid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnAddNewCategory = (Button) findViewById(R.id.btnSubmit);
		spinnerPollCode = (Spinner) findViewById(R.id.spinFood);
		spinnerPollCode.setPrompt("Select Pollcode");
		
		
		//txtPollCode = (TextView) findViewById(R.id.txtCategory);

		// spinner item select listener
		spinnerPollCode.setOnItemSelectedListener(this);

		// Add new category click event
		btnAddNewCategory.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
					new LoadAllPolls().execute();
			}
		});
		
	}

	/**
	 * Background Async Task to Load all product by making HTTP Request
	 * */
	class LoadAllPolls extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(PollCodeActivity.this);
			pDialog.setMessage("Loading polls. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}

		/**
		 * getting All polls from url
		 * */
		@Override
		protected String doInBackground(String... args) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			
			
			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(URL_POLLCODE, "GET", params);
			
			// Check your log cat for JSON reponse
			Log.d("All Polls: ", json.toString());

			try {
				
				
				JSONObject jsonObj = new JSONObject();
				
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {

					// Getting Array of Products
					polls = json.getJSONArray(TAG_POLLS);
					
					for (int i = 0; i < polls.length(); i++) {
						JSONObject catObj = (JSONObject) polls.get(i);
						Category cat = new Category(catObj.getInt("id"),catObj.getInt("type_id"),
								catObj.getString("poll_code"),catObj.getString("poll_desc"),catObj.getString("date_created"));
						pollcodeList.add(cat);
						
						int cont2 = catObj.getInt("id");
						int cont1 = catObj.getInt("type_id");
		                String cont = catObj.getString("poll_code");

		                lables.add(cont);
		                lables1.add(cont1);
		                lables2.add(cont2);
		                
						}
					

		            for (int i = 0; i < polls.length(); i++) {
		                

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
		@Override
		protected void onPostExecute(String file_url) {

			// Creating adapter for spinner
			ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(PollCodeActivity.this,
					android.R.layout.simple_spinner_item, lables);
			spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
		        // attaching data adapter to spinner
		        spinnerPollCode.setAdapter(spinnerAdapter);
			
			pDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					
				}
			});

		}

	}
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) 
	{
		int type_id = lables1.get(position);
		//code = lables.get(position).toString(); 
		code = "KTN";
		//typeid = toString();
		typeid = lables2.get(position).toString();
		switch (position)
        {
		case 0:
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

	@Override
	public void onNothingSelected(AdapterView<?> parent) {		
	}

}
