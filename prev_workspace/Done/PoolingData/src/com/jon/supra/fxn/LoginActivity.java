package com.jon.supra.fxn;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.supra.R;

public class LoginActivity extends Activity{
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		Button btncontinue =(Button)findViewById(R.id.btncontinue);
		btncontinue.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplication(),LaunchActivity.class);
				startActivity(i);
			}
		});
		Button btnLinkToRegisterScreen =(Button)findViewById(R.id.btnLinkToRegisterScreen);
		btnLinkToRegisterScreen.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplication(),SigninActivity.class);
				startActivity(i);
			}
		});
	}

}
