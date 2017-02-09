package com.jon.mypoll;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Launcher extends Activity{
	
	private Button btnlaunch;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		
		btnlaunch = (Button)findViewById(R.id.btnpic);
		
		btnlaunch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(getApplicationContext(),PollCodeActivity.class);
				startActivity(i);
				
				}	
		});
		
		}

}
