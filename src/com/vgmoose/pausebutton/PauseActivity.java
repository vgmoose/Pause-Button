package com.vgmoose.pausebutton;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PauseActivity extends Activity 
{
	static PauseActivity a;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pause);
		
		a = this;
		
		Button b = (Button) findViewById(R.id.button1);
		b.setOnClickListener(new Button.OnClickListener() {
		    public void onClick(View v) {
//		    	Toast.makeText(a.getBaseContext(),"onTouchEvent", Toast.LENGTH_LONG).show();
		    	startService(new Intent(a, PauseService.class));
	    }
	});
		

	}

}
