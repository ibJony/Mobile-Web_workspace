package com.jon.pollapp;


import com.jon.pollApp.R;
import com.jon.pollapp.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;


public class Option2 extends Activity{
	
	JSONParser jsonParser = new JSONParser();
	
	private Button btnsubmit;
	private TextView txtcomment;
	String txtpollcode;
	ProgressDialog pDialog;
	JSONArray polls = null;
	
	private TextView txtPollCode;
	private Spinner spinnerPollCode;
	
	ArrayAdapter<String> spinnerAdapter;
	//List<String> lables = new ArrayList<String>();
//	
//	List<PollCodeActivity> lables = new ArrayList<PollCodeActivity>();
//	List<PollCodeActivity> lables1 = new ArrayList<PollCodeActivity>();
//	List<PollCodeActivity> lables2 = new ArrayList<PollCodeActivity>();
	
	private String URL_POLLCODE = "http://www.masterclass.co.ke/projects/mypoll/polls.php";
	
	ArrayList<Category> pollcodeList = new ArrayList<Category>();
	String id, type_id,poll_code, poll_desc, poll_date;


	// JSON Node names
	public static final String TAG_SUCCESS = "success";
	public static final String TAG_POLLS = "polls";
	//public static final String TAG_POLLCODE = "poll_code";
	//public static final String TAG_PROMPT = "Select Pollcode";
	
	PollCodeActivity code;
	PollCodeActivity typeid;
	
	//int id, type_id;
	
	HashMap<String, String> map ;
	
	ArrayList<HashMap<String, String>> pollitemsList;
	
	String value,txtyes,txtno,txtid,txtcode,name;
	// Url to create new update
	private String URL_NEW_UPDATE = "http://www.masterclass.co.ke/projects/mypoll/pollresult.php";
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.option2);
		
		btnsubmit = (Button)findViewById(R.id.btnpollsubmit);
		txtcomment = (TextView)findViewById(R.id.txtcomment);
		
		txtyes = "0";
		txtno = "1";
		
			
		RadioGroup options = (RadioGroup) findViewById(R.id.option1);

		options.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
            @Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                
                case R.id.radio0:
                value  = txtyes;
                	
                    break;
                    
                case R.id.radio1:
                	
                	 value  = txtno;
                	
                    break;
                }
            }
        });
		
		btnsubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
					new CreateNewProduct().execute();
					
					txtcomment.setText("");
				}	
			//}
		});
	}
	
	/**
	 * Background Async Task to Create new product
	 * */
	class CreateNewProduct extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Option2.this);
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
			
			//String pollid = typeid.toString();
			//String pollcode = code.toString();
			String pollresponse = value;
			String pollcomment = txtcomment.getText().toString();

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("poll_id", "4"));
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
		
		@Override
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			pDialog.dismiss();
		}

	}
}
