package com.jon.app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.jon.app.CommonUtilities.SENDER_ID;
import static com.jon.app.CommonUtilities.SERVER_URL;

public class RegisterActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {
	// alert dialog manager
	AlertDialogManager alert = new AlertDialogManager();
	JSONParser jParser = new JSONParser();
	// Internet detector
	ConnectionDetector cd;
	
	// UI elements
	EditText txtPollCode;
	EditText txtEmail;
	
	// Register button
	Button btnRegister;
	List<Integer> plabid = new ArrayList<Integer>();
	List<String> plabcod = new ArrayList<String>();

	JSONArray polls = null;
	private Spinner spinnerPollCode;

	ArrayAdapter<String> spinnerAdapter;
	Boolean isInternetPresent = false;
	ProgressDialog pDialog;

	private String URL_POLLCODE = "http://www.masterclass.co.ke/projects/mypoll/allpolls.php";

	ArrayList<Category> pollcodeList = new ArrayList<Category>();

	String codepoll,username;
	int pid;

	SharedPreferences sp;
	SharedPreferences.Editor se;
	// JSON Node names
	public static final String TAG_SUCCESS = "success";
	public static final String TAG_POLLS = "polls";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);


		cd = new ConnectionDetector(getApplicationContext());
		isInternetPresent = cd.isConnectingToInternet();
		// Check if Internet present

		// Check if GCM configuration is set
		if (SERVER_URL == null || SENDER_ID == null || SERVER_URL.length() == 0
				|| SENDER_ID.length() == 0) {
			// GCM sernder id / server url is missing
			alert.showAlertDialog(RegisterActivity.this, "Configuration Error!",
					"Please set your Server URL and GCM Sender ID", false);
			// stop executing code by return
			 return;
		}

		txtEmail = (EditText) findViewById(R.id.txtEmail);
		btnRegister = (Button) findViewById(R.id.btnRegister);
		spinnerPollCode = (Spinner) findViewById(R.id.opt);


		spinnerPollCode.setSelected(false);

		//sp = this.getSharedPreferences("ladcod", 0);
		sp = this.getSharedPreferences("codeid", 0);
		sp = this.getSharedPreferences("user",0);

		// spinner item select listener
		spinnerPollCode.setOnItemSelectedListener(this);
		spinnerPollCode.setPrompt("Choose Poll");

		if(isInternetPresent)
		{
			new LoadAllPolls().execute();
		}
		else if (!cd.isConnectingToInternet()) {
			// Internet Connection is not present
			alert.showAlertDialog(RegisterActivity.this,
					"Internet Connection Error",
					"Please connect to working Internet connection", false);
			// stop executing code by return
			return;
		}

		/*
		 * Click event on Register button
		 * */
		btnRegister.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// Read EditText dat
				String pollcode = codepoll;
				username = txtEmail.getText().toString();
				
				// Check if user filled the form
				if(pollcode.trim().length() > 0 && username.trim().length() > 0){
					// Launch Main Activity
					
					Intent i = new Intent(getApplicationContext(), MainActivity.class);
					i.putExtra("pollcode", pollcode);
					i.putExtra("username", username);
					startActivity(i);
					finish();
				}else{

					alert.showAlertDialog(RegisterActivity.this, "Registration Error!", "Please enter your details", false);
				}
			}
		});
	}


	class LoadAllPolls extends AsyncTask<String, String, String> {

			protected void onPreExecute() {
					super.onPreExecute();
					pDialog = new ProgressDialog(RegisterActivity.this);
					pDialog.setMessage("loading");
					pDialog.setIndeterminate(false);
					pDialog.setCancelable(true);
					pDialog.show();
				//plabcod.add("Select Poll");

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

					String cont = catObj.getString("poll_code");
					int acc = catObj.getInt("account_id");

					//plabcod.add(0,"Select Poll");
					plabcod.add(cont);
					plabid.add(acc);

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

		//new LoadPollResults().execute();

		// Creating adapter for spinner
		spinnerAdapter = new ArrayAdapter<String>(RegisterActivity.this,
				android.R.layout.simple_spinner_dropdown_item, plabcod);
		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinnerPollCode.getOnItemClickListener();
		// attaching data adapter to spinner
		int initialposition=spinnerPollCode.getSelectedItemPosition();
		spinnerPollCode.setSelection(initialposition, true);


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

		{
			codepoll = plabcod.get(position);
			pid= plabid.get(position);

			se = sp.edit();
			//se.putString("ladcod", codepoll);
			se.putInt("codeid", pid);
			se.putString("user",username);
			se.commit();

		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
	}
}
