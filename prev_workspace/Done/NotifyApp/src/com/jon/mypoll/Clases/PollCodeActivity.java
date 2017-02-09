package com.jon.mypoll.Clases;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jon.mypoll.R;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PollCodeActivity extends Activity implements OnItemSelectedListener {

			private DrawerLayout mDrawerLayout;
			private ActionBarDrawerToggle mDrawerToggle;
			private CharSequence mDrawerTitle;
			
			// Progress Dialog
			private ProgressDialog pDialog;
			SharedPreferences sp;

			// Creating JSON Parser object
			JSONParser jParser = new JSONParser();
			//public static final String TAG_TYPEID = "type_id";
			
			private Button btnSearchPoll;
			private Button btnSubmit;
			private TextView txtsearch;

			String pollQn,txtpollQn,codepoll;
			
			private Spinner spinnerPollCode;
			
			ArrayAdapter<String> spinnerAdapter;
			
			private String URL_POLLCODE = "http://www.masterclass.co.ke/projects/mypoll/polls.php";
			private String URL_POLLRESULTS = "http://www.masterclass.co.ke/projects/mypoll/pollstat.php";
			
			ArrayList<Category> pollcodeList = new ArrayList<Category>();
			
			// JSON Node names
						public static final String TAG_SUCCESS = "success";
						public static final String TAG_POLLS = "polls";
						public static final String TAG_REPOLLS = "poll_results";
						
						JSONArray polls = null;
						JSONArray pollresults = null;
						
						
						int polltype_id,pid,cont1,reid,retype;
						
						//polls returned
						List<Integer> plabid = new ArrayList<Integer>();
						List<String> plabcod = new ArrayList<String>();
						List<Integer> plabtpid = new ArrayList<Integer>();
						List<String> labQn = new ArrayList<String>();
						
						
						String total,totalyes,totalno,theQn,p,sn;
						
						int totalvar,var1,var2,resp,id,tt1,t1;
						Integer respoll,pollid;

						Boolean isInternetPresent = false;
						ConnectionDetector cd;
						Editor se;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.poll_main);
		
		 getActionBar().setDisplayHomeAsUpEnabled(true);
		 
		btnSearchPoll = (Button) findViewById(R.id.btnSubmit);
		spinnerPollCode = (Spinner) findViewById(R.id.spinFood);
		//txtsearch = (TextView)findViewById(R.id.txtCategory);
		//txtsearch.setEnabled(false);
		
		cd = new ConnectionDetector(getApplicationContext());
		
		sp = this.getSharedPreferences("desc", 0);
		sp = this.getSharedPreferences("id", 0);
		sp = this.getSharedPreferences("codep", 0);
		sp = this.getSharedPreferences("ladcod", 0);
		
		
        
		// spinner item select listener
		spinnerPollCode.setOnItemSelectedListener(this);
		
		int initialposition=spinnerPollCode.getSelectedItemPosition();
		spinnerPollCode.setSelection(initialposition, false);

		TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		sn = telephonyManager.getDeviceId().toString();
		
		btnSearchPoll.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				
				 // get Internet status
               isInternetPresent = cd.isConnectingToInternet();

               // check for Internet status
               if (isInternetPresent) {
                   
            	   new LoadAllPolls().execute();
            	   
   				
               } else {
                   // Internet connection is not present
                   // Ask user to connect to Internet
                   showAlertDialog(PollCodeActivity.this, "No Internet Connection",
                           "You don't have internet connection.", false);
               }
               
			}
			
		});
}
	public void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
 
        // Setting Dialog Title
        alertDialog.setTitle(title);
 
        // Setting Dialog Message
        alertDialog.setMessage(message);
         
        // Setting alert dialog icon
 
        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            @Override
			public void onClick(final DialogInterface dialog, final int which) {
            }
        });
 
        // Showing Alert Message
        alertDialog.show();
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
						
						int co = catObj.getInt("id");
						String cont = catObj.getString("poll_code");
						int cont1 = catObj.getInt("type_id");
		                pollQn = catObj.getString("poll_desc");
		                
		                plabid.add(co);
		                plabcod.add(cont);
		                plabtpid.add(cont1);
		                labQn.add(pollQn);
		                
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
			ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(PollCodeActivity.this,
					android.R.layout.simple_spinner_dropdown_item, plabcod);
			spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			spinnerPollCode.getOnItemClickListener();
		        // attaching data adapter to spinner
			
		        spinnerPollCode.setAdapter(spinnerAdapter);
		        spinnerPollCode.setPrompt(TAG_POLLS);
		        
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
			pid = plabid.get(position);
			polltype_id = plabtpid.get(position);
			codepoll = plabcod.get(position);
		
			//Toast.makeText(getApplicationContext(),retype+" "+polltype_id+" "+var1+" "+totalvar,Toast.LENGTH_LONG).show();
			Toast.makeText(getApplicationContext(),sn,Toast.LENGTH_LONG).show();
			
	        txtpollQn = labQn.get(position);
	        
	        se = sp.edit();
	        
			se.putString("desc",txtpollQn);
			se.putInt("id",pid);
			se.putInt("codep", polltype_id);
			se.putString("ladcod", codepoll);
			se.commit();
			
			if(polltype_id==5)
        	{
				
        		Intent i = new Intent(getApplication(),Option1.class);
        		startActivity(i);
        		
			}
        	else if(polltype_id==6)
        	{
        		
        		Intent i = new Intent(getApplication(),Option2.class);
    			startActivity(i);
    			
        	}
        	else if(polltype_id==7)
        	{
        		Intent i = new Intent(getApplication(),Option3.class);
    			startActivity(i);
    			
        	}
        	else if(polltype_id==8)
        	{
        		
        		Intent i = new Intent(getApplication(),Option4.class);
    			startActivity(i);
    			
        	}
		}
	}	

	@Override
	public void onNothingSelected(AdapterView<?> parent) {		
	}

}
