package com.jon.talii;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class AboutActivity extends Activity {
		
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Set the View layer
		setContentView(R.layout.about);
		
		TextView txt_talii = (TextView) findViewById(R.id.talii);
		TextView txt_talii_header = (TextView) findViewById(R.id.txt_talii);
		TextView txt_version = (TextView) findViewById(R.id.version);
		 // Font path
        String fontPath = "Fonts/Ala Carte.ttf";
        
        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
 
        // Applying font
        txt_talii.setTypeface(tf);
        txt_talii_header.setTypeface(tf);
        
        PackageManager pm = getPackageManager();
        try {
        //---get the package info---
        PackageInfo pi =
        pm.getPackageInfo("com.kenito.talii", 0);
                
        txt_version.setText("Version "+Integer.toString(pi.versionCode));
        } catch (NameNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }
	}
}
		
		