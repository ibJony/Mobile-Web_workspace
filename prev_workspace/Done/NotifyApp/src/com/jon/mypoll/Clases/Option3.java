package com.jon.mypoll.Clases;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.jon.mypoll.R;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Option3 extends Activity{
	
	JSONParser jsonParser = new JSONParser();
	final Context context = this;
	
	private Button btnsubmit;
	private Button btnstats;
	private TextView txtcomment;
	private TextView txtby2;
	String txtpollcode;
	private Spinner spinner;
	ProgressDialog pDialog;
	SharedPreferences sp;
	
	TextView pollQn;
	Editor se;
	
	private static final String TAG_SUCCESS = "success";
	String value,txtyes,txtno;
	int txtid,txtcode,id,code;
	// Url to create new update
	private String URL_NEW_UPDATE = "http://www.masterclass.co.ke/projects/mypoll/pollresult3.php";
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.option3);

		sp = this.getSharedPreferences("desc", 0);
		sp = this.getSharedPreferences("id", 0);
		sp = this.getSharedPreferences("codep", 0);
		sp = this.getSharedPreferences("ladcod", 0);
		String theQn = sp.getString("desc", null);
		id = sp.getInt("id",0 );
		code = sp.getInt("codep", 0);
		
		
		btnsubmit = (Button)findViewById(R.id.btnpollsubmit);
		btnstats = (Button)findViewById(R.id.btnStats3);
		txtcomment = (TextView)findViewById(R.id.txtcomment);
		txtby2 = (TextView)findViewById(R.id.txtby2);
		pollQn = (TextView)findViewById(R.id.pollQn3);
		pollQn.setText(theQn);


		
		
		btnsubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				if(txtcomment.getText().toString().trim().length() > 0 && txtby2.getText().toString().trim().length() > 0)
				{
					
					new CreateNewPoll().execute();
					
				}else
				{
					Toast.makeText(getApplicationContext(), "Fill all required fields" ,Toast.LENGTH_LONG).show();
					
				}
					
					
				}	
			
		});
		
		btnstats.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(getApplicationContext(),Pollcomments.class);
				startActivity(i);
				
				}	
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
			
			String idvalue = String.valueOf(id);
			String codevalue = String.valueOf(code);
			String pollresponse = value;
			String pollcomment = txtcomment.getText().toString();
			String pollby = txtby2.getText().toString();

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("poll_id", idvalue));
			params.add(new BasicNameValuePair("poll_typeid", codevalue));
			params.add(new BasicNameValuePair("poll_comment", pollcomment));
			params.add(new BasicNameValuePair("comment_by", pollby));
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
					
					Intent i = new Intent(getApplicationContext(),Pollcomments.class);
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
			
			Toast.makeText(getApplicationContext(),"Poll submitted Successfully" ,Toast.LENGTH_LONG).show();
			
			pDialog.dismiss();
			finish();
		}

	}

}
