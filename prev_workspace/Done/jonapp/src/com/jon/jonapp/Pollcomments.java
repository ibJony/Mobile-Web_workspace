package com.jon.jonapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jon.jonapp.gcm.push.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class Pollcomments extends Activity{
	
	JSONParser jParser = new JSONParser();
	private String URL_POLLRESULTS = "http://www.masterclass.co.ke/projects/mypoll/pollstat.php";
	
	private ArrayList<PollResults> pollresultList;
	ArrayList<HashMap<String, String>> pollresults;
	
	Button btnchart;
	
	JSONArray polls;
	SharedPreferences sp;
	Editor se;
	
	// JSON Node names
				
	List<Integer> labid = new ArrayList<Integer>();
	List<Integer> labresp = new ArrayList<Integer>();
	List<Integer> labrespyes = new ArrayList<Integer>();
	List<Integer> labcode = new ArrayList<Integer>();
	List<String> labcomment = new ArrayList<String>();
	
	String poll_code, poll_desc, date_created,poll_response,poll_comment,theQn,thecode;
	
	int resp,id,typecode;
	
	private ListView listcomments;
	private TextView polldesc;
	SimpleAdapter listAdapter;
	
	@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.pollcomments);
	        
	        new LoadPollResults().execute();
	        
	        
	        sp = this.getSharedPreferences("desc", 0);
			sp = this.getSharedPreferences("id", 0);
			sp = this.getSharedPreferences("codep", 0);
			sp = this.getSharedPreferences("ladcod", 0);
			theQn = sp.getString("desc", null);
			thecode = sp.getString("ladcod", null);
			id = sp.getInt("id",0 );
			typecode = sp.getInt("codep",0 );
			
	        
			pollresults = new ArrayList<HashMap<String, String>>();
	        pollresultList = new ArrayList<PollResults>();
	        
	        btnchart = (Button)findViewById(R.id.btnchart);
	        listcomments  = (ListView)findViewById(R.id.pollcom);
	        polldesc = (TextView)findViewById(R.id.polldesc);
	        polldesc.setText(thecode+": "+theQn);
	        
	        
	        if(typecode==6){
	        	
	        btnchart.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					
					Intent i = new Intent(getApplication(),PollStats11.class);
	    			startActivity(i);
					
				}
			});
	        }
	        
	 }
	
class LoadPollResults extends AsyncTask<String, String, String> {

	/**
	 * Before starting background thread Show Progress Dialog
	 * */
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	/**
	 * getting All pollstats from url
	 * */
	@Override
	protected String doInBackground(String... args) {
		
		
		ServiceHandler jsonParser = new ServiceHandler();
		String json = jsonParser.makeServiceCall(URL_POLLRESULTS, ServiceHandler.GET);

		Log.e("Response: ", "> " + json);


		if (json != null) {
			try {
				JSONObject jsonObj = new JSONObject(json);
				if (jsonObj != null) {
						polls = jsonObj
								.getJSONArray("poll_results");
						for (int i = 0; i < polls.length(); i++) {
							JSONObject catObj = (JSONObject) polls.get(i);
									
							int cont1 = catObj.getInt("poll_id");
		                	String cont = catObj.getString("poll_comment");
		                	String contb = catObj.getString("comment_by");
		                	try{
		                		
							if(id==cont1 && cont.toString().trim().length() > 0 && contb.toString().trim().length() > 0){
								
								HashMap<String, String> map = new HashMap<String, String>();
								
								// adding each child node to HashMap key => value
								map.put("comment",cont);
								map.put("comment_by",contb);
								
								pollresults.add(map);
			                }
		                	} catch (Exception e) {}
		    				
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
	protected void onPostExecute(String results) {

		// updating UI from Background Thread
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				
				listAdapter = new SimpleAdapter(Pollcomments.this, pollresults,
						R.layout.row_listview1,
						new String[] { "comment","comment_by"}, new int[] {
						R.id.comment,R.id.comment_by});
				
		        listcomments.setAdapter(listAdapter);
			
			}
		});

	}

}
}
