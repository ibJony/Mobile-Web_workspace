package com.jon.mypoll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.apache.http.NameValuePair;
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
import android.widget.Toast;


public class PollStats11 extends Activity{
	
	JSONParser jParser = new JSONParser();
	private String URL_POLLRESULTS = "http://www.masterclass.co.ke/projects/mypoll/pollchart.php";
	private ArrayList<PollResults> polresults;
	ArrayList<HashMap<String, String>> pollitemsList;
	JSONArray polls;
	JSONArray pollresults;
	SharedPreferences sp;
	Editor se;
	
	// JSON Node names
				public static final String TAG_ID = "id";
				public static final String TAG_TYPEID = "type_id";
				public static final String TAG_POLLCODE = "poll_code";
				public static final String TAG_POLLRESPONSE = "poll_response";
				public static final String TAG_POLLCOMMENT = "poll_comment";
				public static final String TAG_REPOLLS = "poll_results";
				public static final String TAG_SUCCESS = "success";
				
				List<Integer> labid = new ArrayList<Integer>();
				List<Integer> labresp = new ArrayList<Integer>();
				List<Integer> labrespyes = new ArrayList<Integer>();
				List<Integer> labrespno = new ArrayList<Integer>();
				List<Integer> labcode = new ArrayList<Integer>();
				List<String> labcomment = new ArrayList<String>();
	
	String total,totalyes,totalno,theQn;
	
	int idpoll,codepoll,totalvar,var1,var2,resp,id,polltype_id,pid,cont1,reid,retype;
	Integer respoll,pollid;
	
	private ListView listcomments;
	//double datastat;
	SimpleAdapter listAdapter;
	
	@Override
	    public void onCreate(Bundle savedInstanceState) {
		 super.finish();
	        super.onCreate(savedInstanceState);
	        
	        new LoadPollResults().execute();
	        
	        sp = this.getSharedPreferences("desc", 0);
			sp = this.getSharedPreferences("id", 0);
			sp = this.getSharedPreferences("codep", 0);
			sp = this.getSharedPreferences("ladcod", 0);
			
			theQn = sp.getString("desc", null);
			id = sp.getInt("id",0 );
			retype = sp.getInt("codep", 0);
			totalvar = sp.getInt("totalv", 0);
			var1 = sp.getInt("va1", 0);
	        var2 = totalvar-var1;
	        
	        polresults = new ArrayList<PollResults>();
	        
	        listcomments  = (ListView)findViewById(R.id.listView);
	      
	        pollitemsList = new ArrayList<HashMap<String, String>>();
	        
	        //Toast.makeText(getApplicationContext()," Selected Redirecting..." ,Toast.LENGTH_LONG).show();
	        
	        //openChart();

	 }
	 
	 private void openChart(){    	
	    	
		 
	    	// Pie Chart Section Names
	    	String[] code = new String[] {
	    			"Yes", "No" 
	    	};    	
	    	
	    	// Pie Chart Section Value
	    	//double[] distribution = { 3.9, 12.9} ;
	    	double[] distribution = { var1, var2 };
	    	
	    	// Color of each Pie Chart Sections
	    	int[] colors = { Color.BLUE, Color.MAGENTA, Color.GREEN, Color.CYAN, Color.RED,
	    					 Color.YELLOW };
	    	
	    	// Instantiating CategorySeries to plot Pie Chart    	
	    	CategorySeries distributionSeries = new CategorySeries(" Android version distribution as on October 1, 2012");
	    	for(int i=0 ;i < distribution.length;i++){
	    		// Adding a slice with its values and name to the Pie Chart
	    		distributionSeries.add(code[i], distribution[i]);
	    	}   
	    	
	    	// Instantiating a renderer for the Pie Chart
	    	DefaultRenderer defaultRenderer  = new DefaultRenderer();    	
	    	for(int i = 0 ;i<distribution.length;i++){    		
	    		SimpleSeriesRenderer seriesRenderer = new SimpleSeriesRenderer();    	
	    		seriesRenderer.setColor(colors[i]);
	    		seriesRenderer.setDisplayChartValues(true);
	    		seriesRenderer.isGradientEnabled();
	    		// Adding a renderer for a slice
	    		defaultRenderer.addSeriesRenderer(seriesRenderer);
	    	}
	    	
	    	defaultRenderer.setChartTitle(theQn);
	    	defaultRenderer.setChartTitleTextSize(20);
	    	defaultRenderer.setLabelsColor(Color.BLACK);
	    	defaultRenderer.setPanEnabled(false);
	    	defaultRenderer.setFitLegend(true);
	    	defaultRenderer.setZoomEnabled(false);
	    		
	    	// Creating an intent to plot bar chart using dataset and multipleRenderer    	
	    	Intent intent = ChartFactory.getPieChartIntent(getBaseContext(), distributionSeries , defaultRenderer, "Polls");    	
	    	
	    	// Start Activity
	    	startActivity(intent);
	    	
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
				// Building Parameters
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				
				
				
				// getting JSON string from URL
				JSONObject json = jParser.makeHttpRequest(URL_POLLRESULTS, "GET", params);
				
				// Check your log cat for JSON reponse
				Log.d("All Polls: ", json.toString());

				try {
					
					
					JSONObject jsonObj = new JSONObject();
					
					int success = json.getInt(TAG_SUCCESS);

					if (success==1) {
						
						// Getting Array of Products
						pollresults = json.getJSONArray(TAG_REPOLLS);
						
						
						for (int i = 0; i < pollresults.length(); i++) {
							JSONObject catObj = (JSONObject) pollresults.get(i);
							
							int cont1 = catObj.getInt("poll_id");
		                	int cont = catObj.getInt("poll_typeid");
		                	int contb = catObj.getInt("poll_response");
		                	resp = catObj.getInt("poll_response");
		                	
		                	labid.add(cont1);
		                	labcode.add(cont);
		                	
		                	try{
		                		
							if(id==labid.get(i) && retype==labcode.get(i)){
								
								labresp.add(resp);
								
								if(resp==0)
								{
									labrespyes.add(resp);
								}
								
			                }
		                	} catch (Exception e) {}
							
				            }
						pollcount();
						}
					
				} catch (JSONException e) {
					e.printStackTrace();
				}

				return null;
			}
			
			@Override
			protected void onPostExecute(String file_url) {
				
				Toast.makeText(getApplicationContext(),id+" "+retype+" "+totalvar+" "+var1+" "+var2,Toast.LENGTH_LONG).show();

				// updating UI from Background Thread
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						//finish();
						openChart();
						
					}
				});
			}
		}
	 
	 public void pollcount()
		{
		 
		 	totalvar = labresp.size();
			var1 = labrespyes.size();
			var2= totalvar-var1;
			
		}
	 
}

