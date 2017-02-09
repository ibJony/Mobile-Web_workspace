package com.jon.supra.fxn;

import java.util.*;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.supra.R;
import com.jon.supra.db.DbHandler;
import com.jon.supra.db.ServiceHandler;
import com.jon.supra.db.Updata;

import android.app.*;
import android.content.Intent;
import android.os.*;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import android.app.*;
import android.os.*;
import android.view.*;

public class UpdataActivity extends Activity{
	
	private ListView listnews;//listview
	private ArrayList<Updata> updatesList;//movielist
	ProgressDialog pDialog;
	//DbHandler db;
	
	private String URL_UPDATE = "http://pipes.yahoo.com/pipes/pipe.run?_id=1a28c5d18099676e0725ae0d1b36db6b&_render=json";
	ArrayList<HashMap<String, String>> dataList;
	//List<Updata> dataList1;
	//private MenuItem refreshMenuItem;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.updata);
        		
		new UpdataActivity.GetUpdates().execute();
	
		updatesList = new ArrayList<Updata>();
		
		dataList = new ArrayList<HashMap<String, String>>();
		
		listnews = (ListView)findViewById(R.id.spinnew);
		
		//dataList1 = db.getAllUpdates();  
		
		}
	
		/**
		 * Adding spinner data
		 * */
	
		private void populateSpinner() {
			List<String> lables = new ArrayList<String>();
			
			for (int i = 0; i < updatesList.size(); i++) {
				lables.add(updatesList.get(i).getTitle());
				lables.add(updatesList.get(i).getDescrip());
				lables.add(updatesList.get(i).getCreated_at());
				lables.add(updatesList.get(i).getUpdated_at());
			}
			/*private void populateListview()
			{
				List<String> lables = new ArrayList<String>();
				for (int i = 0; i < updatesList.size(); i++) {
						lables.add(updatesList.get(i).getTitle());
						lables.add(updatesList.get(i).getDescrip());
					}
			}*/
			
			
			 //Creating adapter for spinner
			 ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, lables);
			

			// Drop down layout style - list view with radio button
			//spinnerAdapter
				//.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			
			//View header = (View)getLayoutInflater().inflate(R.layout.list_row, null);
	        //spinnernew.addHeaderView(header);
	       
	        //spinnerFood.setAdapter(adapter);
			
			// attaching data adapter to spinner
			//spinnernew.setAdapter(spinnerAdapter);
			
			SimpleAdapter listAdapter = new SimpleAdapter(this, dataList,
					R.layout.list_row,
					new String[] { "title", "descrip","created_at","updated_at"}, new int[] { R.id.title,
					R.id.descrip,R.id.created_at,R.id.updated_at});
			
			listnews.setAdapter(listAdapter);
			
		
		}
		
		class GetUpdates extends AsyncTask<Void, Void, Void> {

			
			@Override
			protected void onPreExecute() {
				
				/*refreshMenuItem.setActionView(R.layout.action_progressbar);
				 refreshMenuItem.expandActionView();*/
				 
				super.onPreExecute();
				pDialog = new ProgressDialog(UpdataActivity.this);
				pDialog.setMessage("Fetching news updates..");
				pDialog.setCancelable(false);
				pDialog.show();

			}
			@Override
			protected Void doInBackground(Void... arg0) {
				ServiceHandler jsonParser = new ServiceHandler();
				String json = jsonParser.makeServiceCall(URL_UPDATE, ServiceHandler.GET, null);

				Log.e("Response: ", "> " + json);

				if (json != null) {
					try {
						JSONObject jsonObj = new JSONObject(json);
						if (jsonObj != null) {
							JSONArray updates = jsonObj
									.getJSONArray("updates");						

							for (int i = 0; i < updates.length(); i++) {
								JSONObject catObj = (JSONObject) updates.get(i);
								/*Data cat = new Data(catObj.getInt("id"),
										catObj.getString("title"),catObj.getString("descrip")
										,catObj.getString("created_at"),catObj.getString("updated_at"));
								updatesList.add(cat);*/
								
								
								HashMap<String, String> map = new HashMap<String, String>();
							
								// adding each child node to HashMap key => value
								map.put("id", String.valueOf(catObj.getInt("id")));
								map.put("title", catObj.getString("title"));
								map.put("descrip",catObj.getString("descrip"));
								map.put("created_at",catObj.getString("created_at"));
								map.put("updated_at",catObj.getString("updated_at"));
								// adding HashList to ArrayList
								dataList.add(map);
								
								    
						         
						        /*for (Updata cn : dataList1) {
						            String log = "Id: "+cn.getId()+" ,Title: " + cn.getTitle() + " ,Description: " 
						        + cn.getDescrip() +" ,Created_at: " + cn.getCreated_at() + " ,Updated_at: " + cn.getUpdated_at();
						                // Writing Contacts to log
						        Log.d("updates: ", log);
								
						        }*/
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
			protected void onPostExecute(Void result) {
				super.onPostExecute(result);
				if (pDialog.isShowing())
					pDialog.dismiss();
				//populateListview();
				populateSpinner();
			}

		}
		
		public void onItemSelected(AdapterView<?> parent, View view, int position,
				long id) {
			Toast.makeText(
					getApplicationContext(),
							parent.getItemAtPosition(position).toString() + " Selected" ,
					Toast.LENGTH_LONG).show();

		}

		public void onNothingSelected(AdapterView<?> arg0) {		
		}
}
