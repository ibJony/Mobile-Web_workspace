package com.jon.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class OptChart extends ActionBarActivity {
	
	JSONParser jParser = new JSONParser();
	private String URL_POLLRESULTS = "http://www.masterclass.co.ke/projects/mypoll/polloptchart.php";
	private ArrayList<PollResults> polresults;
	ArrayList<HashMap<String, String>> pollitemsList;
	ArrayList<Option4>listoptnames = new ArrayList<Option4>();

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
	List<String> optlist = new ArrayList<String>();
	List<Integer> labcode = new ArrayList<Integer>();
	List<Integer> opt1 = new ArrayList<Integer>();
	List<Integer> totalopt = new ArrayList<Integer>();
	List<Integer> opt2 = new ArrayList<Integer>();
	List<Integer> opt3 = new ArrayList<Integer>();
	List<Integer> opt4 = new ArrayList<Integer>();
	List<Integer> opt5 = new ArrayList<Integer>();
	List<Integer> opt6 = new ArrayList<Integer>();
	List<Integer> opt7 = new ArrayList<Integer>();
	List<Integer> opt8 = new ArrayList<Integer>();
	List<Integer> opt9 = new ArrayList<Integer>();
	List<Integer> opt10 = new ArrayList<Integer>();

	
	int idpoll,optvalue,totalvar,var1,var2,resp,id,retype;
	String optionsis,optnames,comm,theQn;
	String[] code;
	double[] vars;
	
	private ListView listcomments;
	//double datastat;
	SimpleAdapter listAdapter;
	AlertDialogManager alert = new AlertDialogManager();

	// Connection detector
	ConnectionDetector cd;
	
	@Override
	    public void onCreate(Bundle savedInstanceState) {
		 super.finish();
	        super.onCreate(savedInstanceState);

		cd = new ConnectionDetector(getApplicationContext());

		if (!cd.isConnectingToInternet()) {
			// Internet Connection is not present
			alert.showAlertDialog(OptChart.this,
					"Internet Connection Error",
					"Please connect to working Internet connection", false);
			// stop executing code by return
			return;
		}

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

	        pollitemsList = new ArrayList<HashMap<String, String>>();

	 }
	 
	 public void openChart(){

	    	// Pie Chart Section Value
		 	double[] distribution = vars;
	    	
	    	// Color of each Pie Chart Sections
	    	int[] colors = { Color.BLUE, Color.MAGENTA, Color.GREEN, Color.CYAN, Color.RED,
	    					 Color.YELLOW };
	    	
	    	// Instantiating CategorySeries to plot Pie Chart    	
	    	CategorySeries distributionSeries = new CategorySeries(" Android version distribution as on October 1, 2012");
	    	for(int i=0 ;i < distribution.length;i++){
	    		// Adding a slice with its values and pollcode to the Pie Chart
	    		distributionSeries.add(optlist.get(i), distribution[i]);
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

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
			}

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

						pollresults = json.getJSONArray(TAG_REPOLLS);

						for (int i = 0; i < pollresults.length(); i++) {
							JSONObject catObj = (JSONObject) pollresults.get(i);
							
							int cont1 = catObj.getInt("poll_id");
		                	int cont = catObj.getInt("poll_typeid");
							//optnames = catObj.getString("poll_option_desc");
							optionsis = catObj.getString("poll_option_desc");
		                	labid.add(cont1);
		                	labcode.add(cont);

		                	try{
								if(id==labid.get(i) && retype==labcode.get(i)&& optionsis!=null){
									optnames = catObj.getString("poll_option_desc");
									optvalue= catObj.getInt("poll_option_choice");
									totalopt.add(optvalue);

									if(optvalue==0)
									{
										opt1.add(optvalue);
									}else if(optvalue==1)
									{
										opt2.add(optvalue);

									}else if(optvalue==2)
									{
										opt3.add(optvalue);

									}else if(optvalue==3)
									{
										opt4.add(optvalue);

									}else if(optvalue==4)
									{
										opt5.add(optvalue);

									}/*else if(optvalue==5)
									{
										opt6.add(optvalue);

									}else if(optvalue==6)
									{
										opt7.add(optvalue);

									}else if(optvalue==7)
									{
										opt8.add(optvalue);
									}else if(optvalue==8)
									{
										opt9.add(optvalue);
									}else if(optvalue==9)
									{
										opt10.add(optvalue);

									}*/
									optlist.add(optnames);
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
			totalvar = totalopt.size();
			// \for(int n=0; n<optlist.size();n++) {
				vars = new double[]{opt1.size(), opt2.size(), opt3.size(), opt4.size(), opt5.size()
						/*, opt6.size(), opt7.size(), opt8.size(), opt9.size(), opt10.size()*/};
			//}
		}
	 
}

