package com.jon.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Pollcomments extends Fragment {
	
	JSONParser jParser = new JSONParser();
	private String URL_POLLRESULTS = "http://www.masterclass.co.ke/projects/mypoll/pollstat.php";
	
	private ArrayList<PollResults> pollresultList;
	ArrayList<HashMap<String, String>> pollresults;

	List<Option4> listoptnames = new ArrayList<>();
	
	Button btnchart;
	
	JSONArray polls;
	SharedPreferences sp1;
	Editor se1;
	
	// JSON Node names
				
	List<Integer> labid = new ArrayList<Integer>();
	List<Integer> labresp = new ArrayList<Integer>();
	List<Integer> labrespyes = new ArrayList<Integer>();
	List<Integer> labcode = new ArrayList<Integer>();
	List<String> labcomment = new ArrayList<String>();
	
	String cont,contb,optnames,theQn,thecode,options;
	
	int getd=-1,id,typecode,checker,opt;
	
	private ListView listcomments;
	private TextView polldesc;
	SimpleAdapter listAdapter;
	AlertDialogManager alert = new AlertDialogManager();

	// Connection detector
	ConnectionDetector cd;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.pollcomments, container, false);


		cd = new ConnectionDetector(getActivity());

		if (!cd.isConnectingToInternet()) {
			// Internet Connection is not present
			alert.showAlertDialog(getActivity(),
					"Internet Connection Error",
					"Please connect to working Internet connection", false);
			// stop executing code by return
		}
	        
	        new LoadPollResults().execute();

			sp1 = getActivity().getSharedPreferences("desc", 0);
			sp1 = getActivity().getSharedPreferences("id", 0);
			sp1 = getActivity().getSharedPreferences("codep", 0);
			sp1 = getActivity().getSharedPreferences("ladcod", 0);
			theQn = sp1.getString("desc", null);
			thecode = sp1.getString("ladcod", null);
			id = sp1.getInt("id",0 );
			typecode = sp1.getInt("codep",0 );
			
	        
			pollresults = new ArrayList<HashMap<String, String>>();
	        pollresultList = new ArrayList<PollResults>();
	        
	        btnchart = (Button)view.findViewById(R.id.btnchart);
	        listcomments  = (ListView)view.findViewById(R.id.pollcom);
	        polldesc = (TextView)view.findViewById(R.id.polldesc);
	        polldesc.setText(thecode + ": " + theQn);

	/*	if (getd==0||getd==1) {

			if (getd == 0) {
				tryp = "YES";
			} else {
				tryp = "NO";
			}
		}else{
			tryp="";
		}
*/
	        btnchart.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {

					if(typecode==6){
						Intent i = new Intent(getActivity(), PollStats.class);
						startActivity(i);

				}else{
						Toast.makeText(getActivity(), "The Options does not have a Chart", Toast.LENGTH_LONG).show();
					}
				}
			});
		return view;
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
		                	cont = catObj.getString("poll_comment");
							contb = catObj.getString("comment_by");
							options = catObj.getString("poll_option_choice");
							optnames = catObj.getString("poll_option_desc");

							try{

							if(id==cont1 && opt>-1){

								HashMap<String, String> map = new HashMap<String, String>();

								// adding each child node to HashMap key => value
								map.put("comment",optnames);
								map.put("comment_by",contb);
								
								pollresults.add(map);
			                }
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
		getActivity().runOnUiThread(new Runnable() {

			@Override
			public void run() {

				listAdapter = new SimpleAdapter(getActivity(), pollresults,
						R.layout.row_listview1,
						new String[]{"comment", "comment_by"}, new int[]{
						R.id.comment, R.id.comment_by});
				listcomments.setAdapter(listAdapter);
				}
			});

		}
	}
	public Pollcomments(){

	}
	public Pollcomments newInstance(){
		Pollcomments fragment = new Pollcomments();
		Bundle args = new Bundle();
		args.putInt("section_number", 1);
		fragment.setArguments(args);
		return fragment;
	}

	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(
				getArguments().getInt("section_number"));
	}
}
