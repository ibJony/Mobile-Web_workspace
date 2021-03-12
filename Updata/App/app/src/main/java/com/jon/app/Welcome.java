package com.jon.app;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

/**
 * Created by JON on 7/12/2015.
 */
public class Welcome extends Activity{

    SharedPreferences sp1;
    String isfirst;

    AlertDialogManager alert = new AlertDialogManager();

    // Connection detector
    ConnectionDetector cd;

     protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.welcome);

         cd = new ConnectionDetector(getApplicationContext());

         if (!cd.isConnectingToInternet()) {
             // Internet Connection is not present
             alert.showAlertDialog(Welcome.this,
                     "Internet Connection Error",
                     "Please connect to working Internet connection", false);
             // stop executing code by return
             return;
         }

         //btnla = (Button)findViewById(R.id.btnpic);
         sp1 = this.getSharedPreferences("regid", 0);
         isfirst = sp1.getString("regid", null);

         if(isfirst==null)
         {
             Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
             startActivity(i);
             finish();
         }else{
             Intent i = new Intent(getApplicationContext(), MainActivity.class);
             startActivity(i);
             finish();
         }

     }
}
