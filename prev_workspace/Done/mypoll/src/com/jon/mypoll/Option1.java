package com.jon.mypoll;

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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class Option1 extends Activity{
	
	JSONParser jsonParser = new JSONParser();
	final Context context = this;
	
	private Button btnsubmit;
	private Button btnstats;
	String txtpollcode;
	private Spinner spinner;
	ProgressDialog pDialog;
	SharedPreferences sp;
	
	private static final String TAG_SUCCESS = "success";
	
	RadioGroup options;
	TextView pollQn;
	
	String value,txtyes,txtno;
	int txtid,txtcode,id,code,dvalue;
	// Url to create new update
	
	private String URL_NEW_UPDATE = "http://www.masterclass.co.ke/projects/mypoll/pollresult1.php";
	Editor se;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.option1);

		sp = this.getSharedPreferences("desc", 0);
		sp = this.getSharedPreferences("id", 0);
		sp = this.getSharedPreferences("codep", 0);
		sp = this.getSharedPreferences("ladcod", 0);
			String theQn = sp.getString("desc", null);
			id = sp.getInt("id",0 );
			code = sp.getInt("codep", 0);
			
			
			btnsubmit = (Button)findViewById(R.id.btnSubmit);
			btnstats = (Button)findViewById(R.id.btnStats1);
			pollQn = (TextView)findViewById(R.id.pollQn1);
			pollQn.setText(theQn);
			
			txtyes="0";
			txtno="1";
			
			options = (RadioGroup) findViewById(R.id.option1);

	
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
					
					if (value.toString().trim().length()>0) 
							{
						
							new CreateNewPoll1().execute();
							
							}else
							{
								Toast.makeText(getApplicationContext(), "Fill all required fields" ,Toast.LENGTH_LONG).show();
								
							}
					}	
			});
			
			btnstats.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent i = new Intent(getApplicationContext(),PollStats11.class);
					startActivity(i);
					}	
			});
		}
		
		/**
		 * Background Async Task to Create new product
		 * */
		class CreateNewPoll1 extends AsyncTask<String, String, String> {

			/**
			 * Before starting background thread Show Progress Dialog
			 * */
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				pDialog = new ProgressDialog(Option1.this);
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
				
				String pollresponse = value;
				String idvalue = String.valueOf(id);
				String codevalue = String.valueOf(code);
				
				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("poll_id",idvalue ));
				params.add(new BasicNameValuePair("poll_typeid", codevalue));
				params.add(new BasicNameValuePair("poll_response", pollresponse));

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
						//finish();
						Intent i = new Intent(getApplicationContext(),PollStats11.class);
						startActivity(i);
						
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
				Toast.makeText(getApplicationContext(),"Poll submitted Successfully" ,Toast.LENGTH_LONG).show();
				/*options.clearCheck();*/
				pDialog.dismiss();
				finish();
			}

		}
}
