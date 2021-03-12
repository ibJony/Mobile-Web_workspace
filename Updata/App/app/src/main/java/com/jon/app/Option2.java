package com.jon.app;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Option2 extends Fragment {

	JSONParser jsonParser = new JSONParser();
	final Context context = getActivity();

	private Button btnsubmit;
	private Button btnstats;
	private TextView txtcomment;
	private TextView txtby;
	String txtpollcode;
	ProgressDialog pDialog;
	//JSONArray polls = null;

	private TextView txtPollCode;
	private Spinner spinnerPollCode;
	SharedPreferences sp1;
	ArrayAdapter<String> spinnerAdapter;

	private String URL_POLLCODE = "http://www.masterclass.co.ke/projects/mypoll/pollresult.php";

	ArrayList<Category> pollcodeList = new ArrayList<Category>();
	// JSON Node names
	public static final String TAG_SUCCESS = "success";
	public static final String TAG_POLLS = "pollstats";

	RadioGroup options;


	HashMap<String, String> map ;

	ArrayList<HashMap<String, String>> pollitemsList;

	TextView pollQn;

	String value,txtyes,txtno;
	int txtid,txtcode,id,code;

	// Url to create new update
	private String URL_NEW_UPDATE = "http://www.masterclass.co.ke/projects/mypoll/pollresult.php";
	Editor se1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.option2, container, false);

		sp1 = getActivity().getSharedPreferences("desc", 0);
		sp1 = getActivity().getSharedPreferences("id", 0);
		sp1 = getActivity().getSharedPreferences("codep", 0);
		sp1= getActivity().getSharedPreferences("ladcod", 0);
		String theQn = sp1.getString("desc", null);
		id = sp1.getInt("id",0 );
		code = sp1.getInt("codep", 0);

		btnsubmit = (Button)view.findViewById(R.id.btnpollsubmit);
		btnstats = (Button)view.findViewById(R.id.btnStats2);
		txtcomment = (TextView)view.findViewById(R.id.txtcomment);
		txtby = (TextView)view.findViewById(R.id.txtby);
		pollQn = (TextView)view.findViewById(R.id.pollQn2);
		pollQn.setText(theQn);

		txtyes = "0";
		txtno = "1";

		final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);


		options = (RadioGroup)view.findViewById(R.id.option1);

		options.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {

					case R.id.radio0:
						value  = txtyes;
						break;

					case R.id.radio1:
						value  = txtno;

						break;
				}

			}
		});

		btnsubmit.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (value==txtyes||value==txtno && txtcomment.getText().toString().trim().length() > 0 && txtby.getText().toString().trim().length() > 0)
				{

					new CreateNewProduct().execute();

				}else
				{
					Toast.makeText(getActivity(), "Fill all required fields" ,Toast.LENGTH_LONG).show();

				}
			}

		});

		btnstats.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent i = new Intent(getActivity(),Pollcomments.class);
				startActivity(i);

			}
		});
		return view;
	}

	/**
	 * Background Async Task to Create new product
	 * */
	class CreateNewProduct extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(getActivity());
			pDialog.setMessage("Submitting Polls..");
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Creating product
		 * */
		@Override
		protected String doInBackground(String... args) {

			String idvalue = String.valueOf(id);
			String codevalue = String.valueOf(code);
			String pollresponse = value;
			String pollcomment = txtcomment.getText().toString();
			String pollby2 = txtby.getText().toString();


			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("poll_id", idvalue));
			params.add(new BasicNameValuePair("poll_typeid", codevalue));
			params.add(new BasicNameValuePair("poll_response", pollresponse));
			params.add(new BasicNameValuePair("poll_comment", pollcomment));
			params.add(new BasicNameValuePair("comment_by", pollby2));

			// getting JSON Object
			// Note that create product url accepts POST method
			JSONObject json = jsonParser.makeHttpRequest(URL_NEW_UPDATE,
					"POST", params);

			// check log cat fro response
			Log.d("Create Response", json.toString());

			// check for success tag
			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {

					Intent i = new Intent(getActivity(),Pollcomments.class);
					startActivity(i);
					// successfully created product

				} else {
					// failed to create product
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
	public Option2(){

	}
	public Option2 newInstance(){
		Option2 fragment = new Option2();
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
