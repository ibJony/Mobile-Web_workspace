package com.jon.talii;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class DashboardActivity extends Activity {
	TextView txtAroundMe;
	TextView txtFlights;
	
	Button btn_rss;
	Button btn_about;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard);
		
		ViewFlipper flipper = (ViewFlipper) findViewById(R.id.flipper1);		
				
					flipper.startFlipping();
					
		txtAroundMe = (TextView) findViewById(R.id.txt_around_me);
	//	txtFlights = (TextView) findViewById(R.id.txt_flights);
		btn_rss = (Button) findViewById(R.id.btnrss);
		btn_about = (Button) findViewById(R.id.btnabout);
		
		txtAroundMe.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(),AroundMeActivity.class);
				startActivity(i);
				
			}
		});
		
               
        btn_rss.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(),RssTabsActivity.class);
				startActivity(i);
				
			}
		});
        
        btn_about.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(),AboutActivity.class);
				startActivity(i);
				
			}
		});
					
				
	}

}

