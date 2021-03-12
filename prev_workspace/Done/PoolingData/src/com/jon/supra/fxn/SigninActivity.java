package com.jon.supra.fxn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.supra.*;

public class SigninActivity extends Activity{
	public void onCreate(Bundle savedIstanceState){
		super.onCreate(savedIstanceState);
		setContentView(R.layout.signin);
		
		Button btnRegister =(Button)findViewById(R.id.btnRegister);
		btnRegister.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplication(),LaunchActivity.class);
				startActivity(i);
			}
		});
		Button btnlogin1 =(Button)findViewById(R.id.btnlogin1);
		btnlogin1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getApplication(),LoginActivity.class);
				startActivity(i);
			}
		});
	}

}
