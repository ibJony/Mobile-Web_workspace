package com.jon.pollApp;


import com.jon.pollApp.Category;

import java.util.ArrayList;
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
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;


public class Option2 extends Activity{
	
	private Button btnsubmit;
	private TextView txtcomment;
	String txtpollcode;
	private Spinner spinner;
	ProgressDialog pDialog;
	
	int id, type_id;
	
	String value,txtyes,txtno,code,txtid="2",txtcode="6",name;
	
	Category cat = new Category(id, type_id, name);

	// Url to create new update
	private String URL_NEW_UPDATE = "http://www.masterclass.co.ke/projects/mypoll/newoption1.php";
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.option2);
		
		btnsubmit = (Button)findViewById(R.id.btnpollsubmit);
		
		txtcomment = (TextView)findViewById(R.id.txtcomment);
		
		txtyes = "0";
		txtno = "1";
		
			//txtid = getText(id).toString();
			//txtpollcode = cat.getName();
		txtpollcode = "KTN";
		
		RadioGroup options = (RadioGroup) findViewById(R.id.option1);

		options.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
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
				if (txtyes.trim().length() > 0|txtno.trim().length() > 0) {
					
					// Call Async task to create new category
					new AddNewUpdate().execute();
					
					txtcomment.setText("");
					//Intent i = new Intent(getApplication(),Option2.class);
					//startActivity(i);
	
				}	
			}
		});
	}
	
	private class AddNewUpdate extends AsyncTask<String, Void, Void> {

		boolean isNewUpdateCreated = false;

		@Override
		protected void onPreExecute() {			
			super.onPreExecute();
			pDialog = new ProgressDialog(Option2.this);
			pDialog.setMessage("Polling ...");
			pDialog.setCancelable(false);
			pDialog.show();

		}

		@Override
		protected Void doInBackground(String... arg) {
			
			String choice = value.toString();
			String comment = txtcomment.getText().toString();

			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("poll_id", txtid.toString()));
			params.add(new BasicNameValuePair("poll_code","KTN"));
			params.add(new BasicNameValuePair("poll_reponse", choice));
			params.add(new BasicNameValuePair("poll_comment", comment));
			

			ServiceHandler serviceClient = new ServiceHandler();

			String json = serviceClient.makeServiceCall(URL_NEW_UPDATE,
					ServiceHandler.POST, params);

			Log.d("Create Response: ", "> " + json);

			if (json != null) {
				try {
					
					//JSONArray jsonObj = new JSONArray(json);
					//JsonObject jsonObj = jsonArr.getJSONObject(0);
					
					JSONObject jsonObj = new JSONObject(json);
					boolean error = jsonObj.getBoolean("error");
					
					// checking for error node in json
					
					if (!error) {	
						// new category created successfully
						isNewUpdateCreated = true;						
					} else {
						Log.e("Create Update Error: ", "> " + jsonObj.getString( "message"));
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
			if (isNewUpdateCreated) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
			
						txtcomment.setText("");
					}
				});
			}
			
		}
		
		
	}
}
