package com.jon.supra.fxn;

import android.app.Activity;
import android.os.*;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.app.*;
import android.content.Intent;

import com.example.supra.*;

public class WelcomeActivity extends Activity{
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		
		Button btnpic =(Button)findViewById(R.id.btnpic);
		btnpic.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent i = new Intent(getApplication(),TabsListView.class);
				startActivity(i);
				
			}
		});
		
		Button btnlogin =(Button)findViewById(R.id.btnlog);
		btnlogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplication(),LoginActivity.class);
				startActivity(i);
			}
		});
		Button btnsignin =(Button)findViewById(R.id.btnSign);
		btnsignin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplication(),SigninActivity.class);
				startActivity(i);
			}
		});
		
	}

}
