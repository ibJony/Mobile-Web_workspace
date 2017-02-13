package com.jon.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Option4 extends Fragment {

	private String URL_OPTIONS = "http://www.masterclass.co.ke/projects/mypoll/multioptions.php";
	private String URL_NEW_UPDATE = "http://www.masterclass.co.ke/projects/mypoll/optionresults.php";
	JSONParser jParser = new JSONParser();

	private Button btnsubmit;
	private Button btnstats;

	SharedPreferences sp1,sph1,sp;
	SharedPreferences.Editor se1,se,seh1;
	String theQn,optname,pollcode,username,ischoice;
	Integer id,code,idopt,optno,value,postn;
	ProgressDialog pDialog;
	public static final String TAG_SUCCESS = "success";
	public static final String TAG_POLLS = "polls";

	JSONArray polls;
	AlertDialogManager alert = new AlertDialogManager();
	ConnectionDetector cd;

	List<Integer> listid = new ArrayList<>();
	List<String> listoptnames = new ArrayList<>();
	List<Integer> pollids = new ArrayList<>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.option4, container, false);

		cd = new ConnectionDetector(getActivity());

		if (!cd.isConnectingToInternet()) {
			// Internet Connection is not present
			alert.showAlertDialog(getActivity(),
					"Internet Connection Error",
					"Please connect to working Internet connection", false);
			// stop executing code by return
		}

		sp= getActivity().getSharedPreferences("user", 0);
		username = sp.getString("user", null);

		sp1 = getActivity().getSharedPreferences("desc", 0);
		sp1 = getActivity().getSharedPreferences("id", 0);
		sp1 = getActivity().getSharedPreferences("codep", 0);
		sp1 = getActivity().getSharedPreferences("ladcod", 0);
		theQn = sp1.getString("desc", null);
		id = sp1.getInt("id", 0);
		code = sp1.getInt("codep", 0);
		pollcode = sp1.getString("ladcod", null);

		sph1 = getActivity().getSharedPreferences("no_opts", 0);

		new LoadOptions().execute();

		btnsubmit = (Button)view.findViewById(R.id.btnpollsubmit);
		btnstats = (Button)view.findViewById(R.id.btnStats4);


		btnsubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (ischoice.trim().length()>0) {

					new CreateNewProduct().execute();

				} else {
					Toast.makeText(getActivity(), "Choose one option", Toast.LENGTH_LONG).show();

				}
			}

		});

		btnstats.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent i = new Intent(getActivity(),OptChart.class);
				startActivity(i);

			}
		});
	return view;
	}

	public void CreateLayout(){


		LinearLayout mLinearLayout = (LinearLayout)getActivity().findViewById(R.id.layout);
		TextView title = new TextView(getActivity());
		title.setTextSize(20);
		title.setText(pollcode+":"+theQn);
		mLinearLayout.addView(title);
		// create radio button
		final RadioButton[] rb = new RadioButton[optno];
		final RadioGroup rg = new RadioGroup(getActivity());
		rg.setOrientation(RadioGroup.VERTICAL);

		for (int i = 0; i < optno; i++) {
			rb[i] = new RadioButton(getActivity());
			rg.addView(rb[i]);
			rb[i].setText(listoptnames.get(i));
			rb[i].setTextSize(20);
		}

		mLinearLayout.addView(rg);

		rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				int pos = rg.indexOfChild(getActivity().findViewById(checkedId));
				switch (pos)
				{
					case 0 :
						value=pos;
						ischoice=listoptnames.get(pos);
						break;
					case 1 :
						value=pos;
						ischoice=listoptnames.get(pos);
						break;
					case 2 :
						value=pos;
						ischoice=listoptnames.get(pos);
						break;
					case 3 :
						value=pos;
						ischoice=listoptnames.get(pos);
						break;
					case 4 :
						value=pos;
						ischoice=listoptnames.get(pos);
						break;
					case 5 :
						value=pos;
						ischoice=listoptnames.get(pos);
						break;
					case 6 :
						value=pos;
						ischoice=listoptnames.get(pos);
						break;
					case 7 :
						value=pos;
						ischoice=listoptnames.get(pos);
						break;
					case 8 :
						value=pos;
						ischoice=listoptnames.get(pos);
						break;
					case 9 :
						value=pos;
						ischoice=listoptnames.get(pos);
						break;
					case 10 :
						value=pos;
						ischoice=listoptnames.get(pos);
						break;
					default :
						Toast.makeText(getActivity(), "Nothing Selected", Toast.LENGTH_SHORT).show();
						break;
				}
				//Toast.makeText(getApplicationContext(), ischoice, Toast.LENGTH_LONG).show();
			}
		});

	}
	class LoadOptions extends AsyncTask<String, String, String> {

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
			// Building Parameters{


			ServiceHandler jsonParser = new ServiceHandler();
			String json = jsonParser.makeServiceCall(URL_OPTIONS, ServiceHandler.GET);

			Log.e("Response: ", "> " + json);

			if (json != null) {
				try {
					JSONObject jsonObj = new JSONObject(json);
					if (jsonObj != null) {
						polls = jsonObj
								.getJSONArray("polls");
						for (int i = 0; i < polls.length(); i++) {
							JSONObject catObj = (JSONObject) polls.get(i);

							int opt_id = catObj.getInt("poll_id");

							if(id==opt_id){

							idopt = catObj.getInt("id");
							int pidopt = catObj.getInt("poll_id");
							optname = catObj.getString("option_name");

							listoptnames.add(optname);
							listid.add(idopt);
							pollids.add(pidopt);
							}
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

			pDialog.dismiss();
			// updating UI from Background Thread
			getActivity().runOnUiThread(new Runnable() {
				@Override
				public void run() {

					optno = listid.size();
					seh1 = sph1.edit();
					seh1.putInt("no_opts", optno);
					seh1.commit();

					CreateLayout();
				}

			});
		}
	}
	class CreateNewProduct extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Submitting Polls..");
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... args) {

			String idvalue = String.valueOf(id);
			String codevalue = String.valueOf(code);
			String pollresponse = String.valueOf(value);
			String poll_by = username;


			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("poll_id", idvalue));
			params.add(new BasicNameValuePair("poll_typeid", codevalue));
			params.add(new BasicNameValuePair("poll_option_choice", pollresponse));
			params.add(new BasicNameValuePair("poll_option_desc", ischoice));
			params.add(new BasicNameValuePair("comment_by", poll_by));

			// Note that create product url accepts POST method
			JSONObject json = jParser.makeHttpRequest(URL_NEW_UPDATE,
					"POST", params);

			// check log cat fro response
			Log.d("Create Response", json.toString());

			// check for success tag
			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {

					Intent i = new Intent(getActivity(),OptChart.class);
					startActivity(i);

				} else {
					Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
				}



			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once done
			Toast.makeText(getActivity(),"Poll submitted Successfully" ,Toast.LENGTH_LONG).show();

			pDialog.dismiss();
			getActivity().finish();

		}
	}
	public Option4(){

	}
	public Option4 newInstance(){
		Option4 fragment = new Option4();
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
