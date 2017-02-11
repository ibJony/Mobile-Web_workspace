package com.jon.updatafinally.functions;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.jon.updatafinally.*;
import com.jon.updatafinally.toolbox.JsonObjectRequest;
import com.jon.updatafinally.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

@SuppressLint("NewApi")
public class Enter_Lfstyle extends Fragment{
	
	 private String TAG = this.getClass().getSimpleName();
	    private ListView lstView;
	    private RequestQueue mRequestQueue;
	    private ArrayList<NewsModel> arrNews ;
	    private LayoutInflater lf;
	    private VolleyAdapter va;
	    private ProgressDialog pd;
	    
	    

	    @Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
	    	View rootView = inflater.inflate(R.layout.activity_pmain, container, false);
			
			mRequestQueue =  Volley.newRequestQueue(getActivity());

		    String url = "http://pipes.yahoo.com/pipes/pipe.run?_id=7a47eb5eb8e7dc4ebc92abafaabd58ee&_render=json";
			String url1 = "http://pipes.yahoo.com/pipes/pipe.run?_id=c26b18550f96e064d82a533c6ee701b5&_render=json";
			String url2 = "http://pipes.yahoo.com/pipes/pipe.run?_id=a253845a891eb836ff2bd8c44ce50b54&_render=json";

		    pd = ProgressDialog.show(getActivity(),"Please Wait...","Tegea upashwe...  Please Wait...");

			//View rootView = inflater.inflate(R.layout.activity_pmain, container, false);
	        lf = LayoutInflater.from(getActivity());

	        arrNews = new ArrayList<NewsModel>();
	        va = new VolleyAdapter();

	        lstView = (ListView) rootView.findViewById(R.id.listView);
	       
	       lstView.setAdapter(va);
	       
	       lstView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View v, int pos,long id) {
					String url = ((NewsModel) va.getItem(pos)).getLink();
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse(url));
					startActivity(i);
				}
				
			});
	        
	        try{
	            Thread.sleep(2000);
	        }catch(Exception e){

	            }
			JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET,url,null,new Response.Listener<JSONObject>() {
				@Override
				public void onResponse(JSONObject response) {
					Log.i(TAG,response.toString());
					parseJSON(response);
					va.notifyDataSetChanged();
					pd.dismiss();
					;            }
			},new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {

					Log.i(TAG,error.getMessage());

				}
			});

			JsonObjectRequest jr1 = new JsonObjectRequest(Request.Method.GET,url1,null,new Response.Listener<JSONObject>() {
				@Override
				public void onResponse(JSONObject response) {
					Log.i(TAG,response.toString());
					parseJSON(response);
					va.notifyDataSetChanged();
					pd.dismiss();
					;            }
			},new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {

					Log.i(TAG,error.getMessage());

				}
			});

			JsonObjectRequest jr2 = new JsonObjectRequest(Request.Method.GET,url2,null,new Response.Listener<JSONObject>() {
				@Override
				public void onResponse(JSONObject response) {
					Log.i(TAG,response.toString());
					parseJSON(response);
					va.notifyDataSetChanged();
					pd.dismiss();
					;            }
			},new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {

					Log.i(TAG,error.getMessage());

				}
			});

			mRequestQueue.add(jr);
			mRequestQueue.add(jr1);
			mRequestQueue.add(jr2);
	        
			return rootView;
	    }

		private void parseJSON(JSONObject json){
	        try{
	            JSONObject value = json.getJSONObject("value");
	            JSONArray items = value.getJSONArray("items");
	            for(int i=0;i<items.length();i++){

	                    JSONObject item = items.getJSONObject(i);
	                    NewsModel nm = new NewsModel();
	                    nm.setTitle(item.optString("title"));
	                    nm.setDescription(item.optString("description"));
	                    nm.setLink(item.optString("link"));
	                    nm.setPubDate(item.optString("pubDate"));
	                    arrNews.add(nm);
	            }
	        }
	        catch(Exception e){
	            e.printStackTrace();
	        }
	    }


	    class NewsModel{
	        private String title;
	        private String link;
	        private String description;
	        private String pubDate;

	        void setTitle(String title) {
	            this.title = title;
	        }

	        void setLink(String link) {
	            this.link = link;
	        }

	        void setDescription(String description) {
	            this.description = description;
	        }

	        void setPubDate(String pubDate) {
	            this.pubDate = pubDate;
	        }

	        String getLink() {
	            return link;
	        }

	        String getDescription() {
	            return description;
	        }

	        String getPubDate() {
	            return pubDate;
	        }

	        String getTitle() {

	            return title;
	        }
	    }


	    class VolleyAdapter extends BaseAdapter{

	        @Override
	        public int getCount() {
	            return arrNews.size();
	        }

	        @Override
	        public Object getItem(int i) {
	            return arrNews.get(i);
	        }

	        @Override
	        public long getItemId(int i) {
	            return 0;
	        }

	        @Override
	        public View getView(int i, View view, ViewGroup viewGroup) {
	            ViewHolder vh ;
	           if(view == null){
	               vh = new ViewHolder();
	               view = lf.inflate(R.layout.row_listview1,null);
	               vh.tvTitle = (TextView) view.findViewById(R.id.txtTitle);
	               vh.tvDesc = (TextView) view.findViewById(R.id.txtDesc);
	               vh.tvDate = (TextView) view.findViewById(R.id.txtDate);
	               view.setTag(vh);
	          }
	            else{
	               vh = (ViewHolder) view.getTag();
	           }

	            NewsModel nm = arrNews.get(i);
	            vh.tvTitle.setText(nm.getTitle());
	            vh.tvDesc.setText(nm.getDescription());
	            vh.tvDate.setText(nm.getPubDate());
	            return view;
	        }

	         class  ViewHolder{
	            TextView tvTitle;
	             TextView tvDesc;
	             TextView tvDate;

	        }

	    }

}
