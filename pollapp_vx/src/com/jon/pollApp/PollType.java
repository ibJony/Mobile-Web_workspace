package com.jon.pollApp;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class PollType extends Activity{

	private TextView txtPollCode;
	private ArrayList<Category> pollcodetype;
	ProgressDialog pDialog;
	private ArrayList<Category> pollcodeList;

	private String URL_POLLTYPE = "http://www.masterclass.co.ke/projects/mypoll/getPolltype.php";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		pollcodeList = new ArrayList<Category>();
		}
		
		private void populateSpinner() {
			List<String> lables = new ArrayList<String>();
			
			txtPollCode.setText("");

			for (int i = 0; i < pollcodeList.size(); i++) {
				lables.add(pollcodeList.get(i).getName());
			}
		}
		
		class GetPollTypes extends AsyncTask<Void, Void, Void> {

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
			}

			@Override
			protected Void doInBackground(Void... arg0) {
				ServiceHandler jsonParser = new ServiceHandler();
				String json = jsonParser.makeServiceCall(URL_POLLTYPE, ServiceHandler.GET);

				Log.e("Response: ", "> " + json);

				if (json != null) {
					try {
						JSONObject jsonObj = new JSONObject(json);
						if (jsonObj != null) {
							
								
								if (jsonObj != null) {
									JSONObject polltype = jsonObj
											.getJSONObject("polltype");
								
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
			
			
		}
	}
