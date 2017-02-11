package com.jon.news_app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class WelcomeActivity extends CustomActivity{
	
	 // flag for Internet connection status
    Boolean isInternetPresent = false;
     
    // Connection detector class
    ConnectionDetector cd;
    
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		
		cd = new ConnectionDetector(getApplicationContext());
		
		//this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		Button btnpic =(Button)findViewById(R.id.btnpic);
		btnpic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				 // get Internet status
                isInternetPresent = cd.isConnectingToInternet();
 
                // check for Internet status
                if (isInternetPresent) {
                    
                    Intent i = new Intent(getApplication(),MainAppActivity.class);
                    startActivity(i);
    				
    				
                } else {
                    // Internet connection is not present
                    // Ask user to connect to Internet
                    showAlertDialog(WelcomeActivity.this, "No Internet Connection",
                            "You don't have internet connection.", false);
                }
				
			}
		});
		
	}
	
	@SuppressWarnings("deprecation")
	public void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
 
        // Setting Dialog Title
        alertDialog.setTitle(title);
 
        // Setting Dialog Message
        alertDialog.setMessage(message);
         
        // Setting alert dialog icon
 
        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            @Override
			public void onClick(final DialogInterface dialog, final int which) {
            }
        });
 
        // Showing Alert Message
        alertDialog.show();
	}
}
