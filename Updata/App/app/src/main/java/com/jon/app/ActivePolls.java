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

public class ActivePolls extends Fragment {

	JSONParser jParser = new JSONParser();
	private String URL_POLLRESULTS = "http://www.masterclass.co.ke/projects/mypoll/polls.php";
	private String URL_POLLCODE = "http://www.masterclass.co.ke/projects/mypoll/polls.php";

	private ArrayList<PollResults> pollresultList;
	ArrayList<HashMap<String, String>> pollresults;
	ArrayList<HashMap<String, String>> defaultp;

	Button btnchart;
	JSONArray polls;
	SharedPreferences sp, sp1;
	Editor se, se1;

	List<Integer> labid = new ArrayList<Integer>();
	List<String> theQns = new ArrayList<String>();

	List<Integer> plabid = new ArrayList<Integer>();
	List<String> plabcod = new ArrayList<String>();
	List<Integer> plabtpid = new ArrayList<Integer>();
	List<String> labQn = new ArrayList<String>();

	int type, id, accid;
	int polltype_id, pid, ptypeid;
	String theQn;
	String pollQn, txtpollQn, codepoll;
	private ListView listcomments, listdefault;
	SimpleAdapter listAdapter, listAdapter1;
	ArrayList<Category> pollcodeList = new ArrayList<Category>();
	ProgressDialog pDialog;

	public static final String TAG_SUCCESS = "success";
	public static final String TAG_POLLS = "polls";

	AlertDialogManager alert = new AlertDialogManager();
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

		}
		sp = getActivity().getSharedPreferences("codeid", 0);
		accid = sp.getInt("codeid", 0);

		new LoadPollResults().execute();

		sp = getActivity().getSharedPreferences("desc", 0);
		sp = getActivity().getSharedPreferences("type", 0);

		sp1 = getActivity().getSharedPreferences("desc", 0);
		sp1 = getActivity().getSharedPreferences("id", 0);
		sp1 = getActivity().getSharedPreferences("codep", 0);
		sp1 = getActivity().getSharedPreferences("ladcod", 0);

		pollresults = new ArrayList<HashMap<String, String>>();
		defaultp = new ArrayList<HashMap<String, String>>();

		listcomments = (ListView) view.findViewById(R.id.pollcom);
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

				if (type == 5) {
					Intent i = new Intent(getActivity(), Option1.class);
					getActivity().startActivity(i);
				} else if (type == 6) {
					Intent i = new Intent(getActivity(), Option2.class);
					startActivity(i);
				} else if (type == 7) {
					Intent i = new Intent(getActivity(), Option3.class);
					startActivity(i);
				} else if (type == 8) {
					Intent i = new Intent(getActivity(), Option4.class);
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
							ptypeid = catObj.getInt("account_id");
							labid.add(cont1);
							theQns.add(pollQn);
		                	try{

								HashMap<String, String> map = new HashMap<String, String>();
								
								// adding each child node to HashMap key => value
								map.put("poll",cont);
								map.put("description",pollQn);
								
								pollresults.add(map);

								if(accid==ptypeid) {

									HashMap<String, String> map1 = new HashMap<String, String>();

									map1.put("poll", cont);
									map1.put("description", pollQn);

									defaultp.add(map1);
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
						R.layout.row_polls,
						new String[] { "poll","description"}, new int[] {
						R.id.comment,R.id.comment_by});
				
		        listcomments.setAdapter(listAdapter);
			
			}
		});
		}
	}

	class LoadAllPolls extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Loading polls Active Polls. Please wait...");
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

	public ActivePolls(){

	}
	public ActivePolls newInstance(){
		ActivePolls fragment = new ActivePolls();
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
