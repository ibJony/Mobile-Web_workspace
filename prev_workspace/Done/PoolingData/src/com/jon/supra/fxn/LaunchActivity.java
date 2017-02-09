package com.jon.supra.fxn;

import com.example.supra.R;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LaunchActivity extends Activity{
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.launch);
		
		Button btnnew  =(Button)findViewById(R.id.btnnew);
		btnnew.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplication(),UpdataActivity.class);
				startActivity(i);
			}
		});
		Button btnlost =(Button)findViewById(R.id.btnlost);
		btnlost.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplication(),FinderActivity.class);
				startActivity(i);
			}
		});
		
		Button btnpost =(Button)findViewById(R.id.btnpost);
		btnpost.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplication(),PostActivity.class);
				startActivity(i);
			}
		});
	}


}
