package com.jon.talii;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.jon.talii.lib.AroundMe;
import com.jon.talii.lib.AroundMeArrayAdapter;
import com.jon.talii.lib.AroundMeParser;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class AroundMeActivity extends Activity {
	
	Button btnRss;
	Button btnHome;
	Button btnAbout;

private List<AroundMe> aroundList= new ArrayList<AroundMe>();
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Set the View layer
		setContentView(R.layout.around_me);
		
	//	TextView txt_talii = (TextView) findViewById(R.id.talii);
		TextView txt_talii_header = (TextView) findViewById(R.id.txt_talii);
		 // Font path
        String fontPath = "Fonts/Ala Carte.ttf";
        
        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
        
        txt_talii_header.setTypeface(tf);
		
		btnRss = (Button) findViewById(R.id.btn_rss);
		btnHome = (Button) findViewById(R.id.btn_home);
		btnAbout = (Button) findViewById(R.id.btn_about);
		
		// Create Parser for raw/countries.xml
		AroundMeParser aroundParser = new AroundMeParser();
		InputStream inputStream = getResources().openRawResource(
				R.raw.list);
		
		// Parse the inputstream
		aroundParser.parse(inputStream);

		// Get place types
		List<AroundMe> aroundList = aroundParser.getList();
		
		
		// Create a customized ArrayAdapter
		AroundMeArrayAdapter adapter = new AroundMeArrayAdapter(
				getApplicationContext(), R.layout.list_item, aroundList);
		
		// Get reference to ListView holder
		ListView lv = (ListView) this.findViewById(R.id.label);
		
		
		// Set the ListView adapter
		lv.setAdapter((ListAdapter) adapter);
		
		lv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {
          
            	/*	if(position==0)
                {
                     Intent i = new Intent(getApplicationContext(), RssChannelActivity.class);
                  // i.putExtra("places", "campground");
                   startActivity(i);
                	
                }
            	
            	if(position==1)
                {
                     Intent i = new Intent(getApplicationContext(), RssChannelActivity.class);
                  // i.putExtra("places", "campground");
                   startActivity(i);
                	
                }
            	
            	if(position==2)
                {
                     Intent i = new Intent(getApplicationContext(), RssChannelActivity.class);
                  // i.putExtra("places", "campground");
                   startActivity(i);
                	
                }
            	
            	if(position==3)
                {
                	 Intent i = new Intent(getApplicationContext(), RssChannelActivity.class);
                  // i.putExtra("places", "campground");
                   startActivity(i);
                	
                }
            	
            	if(position==4)
                {
            	   Intent i = new Intent(getApplicationContext(), RssChannelActivity.class);
                  // i.putExtra("places", "campground");
                   startActivity(i);
                }
            	
            	 if(position==5)
                 {
                 	Intent i = new Intent(getApplicationContext(), PostActivity.class);
                     //i.putExtra("places", "amusement_park|art_gallery|museum");
                     startActivity(i);
                 	
                 }
                 
                 	
                 }*/
            }
          }); 
		
		btnHome.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(), DashboardActivity.class);
                startActivity(i);
				
			}
		});
		
        btnRss.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(), RssTabsActivity.class);
                startActivity(i);
				
			}
		});
        
        btnAbout.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(i);
				
			}
		});
		
	}
}

