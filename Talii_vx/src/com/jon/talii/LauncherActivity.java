package com.jon.talii;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LauncherActivity extends Activity {
	Button btnDashboard;
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launcher);
		
		TextView txt_talii = (TextView) findViewById(R.id.talii);
		TextView txt_talii_header = (TextView) findViewById(R.id.txt_talii);
		 // Font path
        String fontPath = "Fonts/Ala Carte.ttf";
        
        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
 
        // Applying font
        txt_talii.setTypeface(tf);
        txt_talii_header.setTypeface(tf);
		
		btnDashboard = (Button) findViewById (R.id.btn_enter_dashboard);
		btnDashboard.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(),DashboardActivity.class);
				startActivity(i);
				
			}
		});
	}

}
