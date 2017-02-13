package com.jon.app;

import android.app.Activity;
import android.app.ProgressDialog;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ViewPollStats extends Fragment {
	
	JSONParser jParser = new JSONParser();
	private String URL_POLLRESULTS = "http://www.masterclass.co.ke/projects/mypoll/polls.php";
	private String URL_POLLCODE = "http://www.masterclass.co.ke/projects/mypoll/polls.php";
	
	private ArrayList<PollResults> pollresultList;
	ArrayList<HashMap<String, String>> pollresults;
	
	Button btnchart;
	
	JSONArray polls;
	SharedPreferences sp,sp1;
	Editor se,se1;

	// JSON Node names

	List<Integer> labid = new ArrayList<Integer>();
	List<String> theQns = new ArrayList<String>();

	List<Integer> plabid = new ArrayList<Integer>();
	List<String> plabcod = new ArrayList<String>();
	List<Integer> plabtpid = new ArrayList<Integer>();
	List<String> labQn = new ArrayList<String>();
	
	int type,id;
	int polltype_id,pid,cont1,reid,retype;
	String theQn;
	String pollQn,txtpollQn,codepoll;
	private ListView listcomments;
	SimpleAdapter listAdapter;
	ArrayList<Category> pollcodeList = new ArrayList<Category>();
	ProgressDialog pDialog;

	public static final String TAG_SUCCESS = "success";
	public static final String TAG_POLLS = "polls";

	AlertDialogManager alert = new AlertDialogManager();

	// Connection detector
	ConnectionDetector cd;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activepolls, container, false);

		cd = new ConnectionDetector(getActivity());

		if (!cd.isConnectingToInternet()) {
			// Internet Connection is not present
			alert.showAlertDialog(getActivity(),
					"Internet Connection Error",
					"Please connect to working Internet connection", false);
			// stop executing code by return
		}
	        
	        new LoadPollResults().execute();

		sp = getActivity().getSharedPreferences("desc", 0);
		sp = getActivity().getSharedPreferences("type", 0);

		sp1 = getActivity().getSharedPreferences("desc", 0);
		sp1 = getActivity().getSharedPreferences("id", 0);
		sp1 = getActivity().getSharedPreferences("codep", 0);
		sp1 = getActivity().getSharedPreferences("ladcod", 0);


			pollresults = new ArrayList<HashMap<String, String>>();
	        pollresultList = new ArrayList<PollResults>();

	        listcomments  = (ListView)view.findViewById(R.id.pollcom);
		listcomments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent,
									View view, int position, long id) {
				type = labid.get(position);
				theQn = theQns.get(position);

				se = sp.edit();
				se.putString("desc", theQn);
				se.putInt("type", type);
				se.commit();

				pid = plabid.get(position);
				polltype_id = plabtpid.get(position);
				codepoll = plabcod.get(position);

				txtpollQn = labQn.get(position);

				se1 = sp1.edit();

				se1.putString("desc", txtpollQn);
				se1.putInt("id", pid);
				se1.putInt("codep", polltype_id);
				se1.putString("ladcod", codepoll);
				se1.commit();

				if (polltype_id == 5) {
					Intent i = new Intent(getActivity(), PollStats.class);
					startActivity(i);
				} else {
					Intent i = new Intent(getActivity(), Pollcomments.class);
					startActivity(i);
				}
			}
		});

		new LoadAllPolls().execute();
		return view;
	 }
	
class LoadPollResults extends AsyncTask<String, String, String> {

	/**
	 * Before starting background thread Show Progress Dialog
	 * */
	@Override
	protected void onPreExecute() {

		super.onPreExecute();
		//pDialog = new ProgressDialog(ActivePolls.this);
		//pDialog.setMessage("Updating...");
		//pDialog.setIndeterminate(false);
		//pDialog.setCancelable(true);
		//pDialog.show();
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
								.getJSONArray("polls");
						for (int i = 0; i < polls.length(); i++) {
							JSONObject catObj = (JSONObject) polls.get(i);
							Category cat = new Category(catObj.getInt("id"),catObj.getInt("type_id"),
									catObj.getString("poll_code"),catObj.getString("poll_desc"),catObj.getString("date_created"));
							pollcodeList.add(cat);

							int co = catObj.getInt("id");
							String cont = catObj.getString("poll_code");
							int cont1 = catObj.getInt("type_id");
							String pollQn = catObj.getString("poll_desc");
							labid.add(cont1);
							theQns.add(pollQn);
		                	try{
		                		
							//if(id==cont1 && cont.toString().trim().length() > 0 && contb.toString().trim().length() > 0){
								
								HashMap<String, String> map = new HashMap<String, String>();
								
								// adding each child node to HashMap key => value
								map.put("poll",cont);
								map.put("description",pollQn);
								
								pollresults.add(map);
			               	 //}
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
		getActivity(). runOnUiThread(new Runnable() {

			@Override
			public void run() {

				listAdapter = new SimpleAdapter(getActivity(), pollresults,
						R.layout.row_polls,
						new String[]{"poll", "description"}, new int[]{
						R.id.comment, R.id.comment_by});

				listcomments.setAdapter(listAdapter);

			}
		});
		//pDialog.dismiss();

		}
	}

	class LoadAllPolls extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Loading polls Stastics...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();

		}

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

		@Override
		protected void onPostExecute(String file_url) {


			pDialog.dismiss();
			// updating UI from Background Thread
			getActivity().runOnUiThread(new Runnable() {
				@Override
				public void run() {
				}
			});
		}
	}
	public ViewPollStats(){

	}
	public ViewPollStats newInstance(){
		ViewPollStats fragment = new ViewPollStats();
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
