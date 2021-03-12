package com.jon.app;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

public class Splash extends Activity {

    SharedPreferences sp1;
    String isfirst;

    AlertDialogManager alert = new AlertDialogManager();

    // Connection detector
    ConnectionDetector cd;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.splash);

        try {
            getActionBar().hide();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        cd = new ConnectionDetector(getApplicationContext());

        if (!cd.isConnectingToInternet()) {
            // Internet Connection is not present
            alert.showAlertDialog(Splash.this,
                    "Internet Connection Error",
                    "Please connect to working Internet connection", false);
            // stop executing code by return
            return;
        }

        //btnla = (Button)findViewById(R.id.btnpic);
        sp1 = this.getSharedPreferences("regid", 0);
        isfirst = sp1.getString("regid", null);

        Thread thread = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }
                catch(InterruptedException ex){
                    ex.printStackTrace();
                }
                finally{

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
        };

        thread.start();
    }

    public void onPause(){
        super.onPause();
        finish();
    }

}
