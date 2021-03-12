package com.jon.pollapp;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
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
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class Option3 extends Activity{
	
	JSONParser jsonParser = new JSONParser();
	PollCodeActivity polls = new PollCodeActivity();
	
	private Button btnsubmit;
	private TextView txtcomment;
	String txtpollcode;
	private Spinner spinner;
	ProgressDialog pDialog;
	
	private static final String TAG_SUCCESS = "success";
	
	//int id, type_id;
	
	String value,txtyes,txtno,code,txtid,txtcode,name;
	// Url to create new update
	private String URL_NEW_UPDATE = "http://www.masterclass.co.ke/projects/mypoll/pollresult3.php";
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.option3);
		
		btnsubmit = (Button)findViewById(R.id.btnpollsubmit);
		txtcomment = (TextView)findViewById(R.id.txtcomment);
		
		btnsubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//if (txtyes.trim().length() > 0|txtno.trim().length() > 0) {
					
					new CreateNewPoll().execute();
					
					txtcomment.setText("");
					
					/*Toast.makeText(
	        				getApplicationContext(),
	        						value.toString() + " Selected Redirecting..." ,
	        				Toast.LENGTH_LONG).show();*/
					
	
				}	
			//}
		});
	}
	
	/**
	 * Background Async Task to Create new product
	 * */
	class CreateNewPoll extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Option3.this);
			pDialog.setMessage("Submitting Polls..");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Creating product
		 * */
		@Override
		protected String doInBackground(String... args) {
			
			//String pollid = polls.getString(id).toString();
			//String pollcode = polls.getString(type_id).toString();
			String pollresponse = value;
			String pollcomment = txtcomment.getText().toString();
			//String poll_option_choice = inputDesc.getText().toString();

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("id", "9"));
			params.add(new BasicNameValuePair("poll_id", "3"));
			params.add(new BasicNameValuePair("poll_code", "6"));
			params.add(new BasicNameValuePair("poll_response", pollresponse));
			params.add(new BasicNameValuePair("poll_comment", pollcomment));
			//params.add(new BasicNameValuePair("poll_option_choice", poll_option_choice));

			// getting JSON Object
			// Note that create product url accepts POST method
			JSONObject json = jsonParser.makeHttpRequest(URL_NEW_UPDATE,
					"POST", params);
			
			// check log cat fro response
			Log.d("Create Response", json.toString());

			// check for success tag
			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// successfully created product
					Intent i = new Intent(getApplicationContext(), PollCodeActivity.class);
					startActivity(i);
					
					// closing this screen
					//finish();
				} else {
					// failed to create product
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
			// dismiss the dialog once done
			pDialog.dismiss();
		}

	}

}
