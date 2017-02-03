package com.jon.pollApp;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
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

	private Button btnAddNewCategory;
	private Button btnSubmit;
	private TextView txtPollCode;
	private Spinner spinnerPollCode;
	// array list for spinner adapter
	private ArrayList<Category> pollcodeList;
	ProgressDialog pDialog;
	
	ArrayAdapter<String> spinnerAdapter;
	
	Cursor cursor;

	int type_id;
	
	private String URL_POLLCODE = "http://www.masterclass.co.ke/projects/mypoll/getCategories.php";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnAddNewCategory = (Button) findViewById(R.id.btnAddNewCategory);
		
		spinnerPollCode = (Spinner) findViewById(R.id.spinFood);
		txtPollCode = (TextView) findViewById(R.id.txtCategory);
		
		pollcodeList = new ArrayList<Category>();

		// spinner item select listener
		spinnerPollCode.setOnItemSelectedListener(this);
		
		List<String> lables = new ArrayList<String>();

		// Add new category click event
		btnAddNewCategory.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
					Toast.makeText(getApplicationContext(),
							"Getting PollCodes", Toast.LENGTH_SHORT)
							.show();
				
					new GetCategories().execute();
			}
		});
		
		//for(int m=0;m<pollcodeList.size();m++){
			Category me = new Category(type_id);
			type_id = (int) me.getType();
			//}
	}
	/**
	 * Adding spinner data
	 * */
	private void populateSpinner() {
		List<String> lables = new ArrayList<String>();
		
		txtPollCode.setText("");

		for (int i = 0; i < pollcodeList.size(); i++) {
			lables.add(pollcodeList.get(i).getName());
			}
		
		// Creating adapter for spinner
		spinnerAdapter= new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, lables);


		// Drop down layout style - list view with radio button
		spinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// attaching data adapter to spinner
		spinnerPollCode.setAdapter(spinnerAdapter);
	}

	/**
	 * Async task to get all food categories
	 * */
	private class GetCategories extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(PollCodeActivity.this);
			pDialog.setMessage("Fetching PollCodes..");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected Void doInBackground(Void... arg0) {
			ServiceHandler jsonParser = new ServiceHandler();
			String json = jsonParser.makeServiceCall(URL_POLLCODE, ServiceHandler.GET);

			Log.e("Response: ", "> " + json);

			if (json != null) {
				try {
					JSONObject jsonObj = new JSONObject(json);
					if (jsonObj != null) {
						JSONArray categories = jsonObj
								.getJSONArray("categories");						

						for (int i = 0; i < categories.length(); i++) {
							JSONObject catObj = (JSONObject) categories.get(i);
							Category cat = new Category(catObj.getInt("id"),catObj.getInt("type_id"),
									catObj.getString("poll_code"));
							pollcodeList.add(cat);
							
							type_id = catObj.getInt("type_id");
							
							}
							
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
			populateSpinner();
		}

	}
	
	
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) 
	{
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

	@Override
	public void onNothingSelected(AdapterView<?> parent) {		
	}

}
