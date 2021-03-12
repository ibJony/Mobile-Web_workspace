package com.jon.mypoll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;


public class PollStats extends Activity{
	
	JSONParser jParser = new JSONParser();
	private String URL_POLLRESULTS = "http://www.masterclass.co.ke/projects/mypoll/pollstat.php";
	private ArrayList<PollResults> pollcodeList;
	ArrayList<HashMap<String, String>> pollitemsList;
	JSONArray polls;
	SharedPreferences sp;
	Editor se;
	
	// JSON Node names
				public static final String TAG_ID = "id";
				public static final String TAG_TYPEID = "type_id";
				public static final String TAG_POLLCODE = "poll_code";
				public static final String TAG_POLLRESPONSE = "poll_response";
				public static final String TAG_POLLCOMMENT = "poll_comment";
				
				List<Integer> labid = new ArrayList<Integer>();
				List<Integer> labresp = new ArrayList<Integer>();
				List<Integer> labrespyes = new ArrayList<Integer>();
				List<Integer> labcode = new ArrayList<Integer>();
				List<String> labcomment = new ArrayList<String>();
	
	String type_id,poll_code, poll_desc, date_created,poll_response,poll_comment,total,totalyes,theQn;
	
	int idpoll,codepoll,totalvar,var1,var2,resp,id;
	Integer respoll,pollid;
	
	private ListView listcomments;
	//double datastat;
	SimpleAdapter listAdapter;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.pollstats);
	        
			sp = this.getSharedPreferences("id", 0);
			sp = this.getSharedPreferences("codep", 0);
			theQn = sp.getString("desc", null);
			id = sp.getInt("id",0 );
	        
	        pollcodeList = new ArrayList<PollResults>();
	        
	        listcomments  = (ListView)findViewById(R.id.listView);
	      
	        pollitemsList = new ArrayList<HashMap<String, String>>();
	        
	       /* listAdapter = new SimpleAdapter(this, pollitemsList,
					R.layout.row_listview1,
					new String[] { "poll_comment"}, new int[] {R.id.txtDesc});*/
			
	        new LoadAllPolls().execute();
	 }
	 
	 void openChart(){   
	    	// Pie Chart Section Names
		 	String[] pollresponse= new String[] {
	    			"Yes", "No" 
	    	};
			
			//double[] distribution = { var1, var2 } ; 
		 	double[] distribution = { 3.9, 12.9} ;
	    	
	    	
	    	// Color of each Pie Chart Sections
	    	int[] colors = { Color.BLUE, Color.LTGRAY };
	    	
	    	// Instantiating CategorySeries to plot Pie Chart    	
	    	CategorySeries distributionSeries = new CategorySeries("Polling Results");
	    	for(int i=0 ;i < distribution.length;i++){
	    		// Adding a slice with its values and name to the Pie Chart
	    		distributionSeries.add(pollresponse[i], distribution[i]);
	    	} 
	    	
	    	
	    	// Instantiating a renderer for the Pie Chart
	    	DefaultRenderer defaultRenderer  = new DefaultRenderer();    	
	    	for(int i = 0 ;i<distribution.length;i++){    		
	    		SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();    	
	    		seriesRenderer.setColor(colors[i]);
	    		seriesRenderer.setDisplayChartValues(true);
	    		// Adding a renderer for a slice
	    		defaultRenderer.addSeriesRenderer(seriesRenderer);
	    	}
	    	
	    	defaultRenderer.setChartTitle(theQn);
	    	defaultRenderer.setChartTitleTextSize(40);
	    	defaultRenderer.setLabelsColor(Color.BLACK);
	    	defaultRenderer.isFitLegend();
	    		
	    	// Creating an intent to plot bar chart using dataset and multipleRenderer    	
	    	Intent intent = ChartFactory.getPieChartIntent(getBaseContext(), distributionSeries , defaultRenderer, "Poll Chart Results");    	
	    		intent.getFlags();
	    		//intent.
	    	//intent.setClass(getApplicationContext(), PollStats11.class);
	    	startActivity(intent);
	    	
	    }  
	 
	 
class LoadAllPolls extends AsyncTask<String, String, String> {

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
							/*PollResults cat = new PollResults(catObj.getInt("id"),catObj.getInt("poll_id"),
									catObj.getInt("poll_typeid"),catObj.getInt("poll_response"),catObj.getString("poll_comment"));*/
									
							int cont1 = catObj.getInt("poll_id");
							resp = catObj.getInt("poll_response");
		                	String cont = catObj.getString("poll_comment");
							
		                	try{
		                		
							if(id==cont1 && resp >-1){
								
			                		labresp.add(resp);
			                		
			                		if(resp == 0)
			                		{
			                			labrespyes.add(resp);
			                		}
			                	
			                }
		                	} catch (Exception e) {}
		    				
			                
			                labcode.add(cont1);
			                
					pollcount();
					
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
	protected void onPostExecute(String file_url) {

		// updating UI from Background Thread
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				
				openChart();
				
			}
		});

	}

}
	
	public void pollcount()
	{
		total = String.valueOf(labresp.size());
		
		totalvar = Integer.parseInt(total);
		
		totalyes = String.valueOf(labrespyes.size());
		
		var1 = Integer.parseInt(totalyes);
		
		var2 = totalvar - var1;
	}
}
