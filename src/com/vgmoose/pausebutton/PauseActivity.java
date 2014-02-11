package com.vgmoose.pausebutton;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.gms.ads.*;

public class PauseActivity extends Activity 
{
	static PauseActivity a;
	static boolean activated = false;
	static Intent serviceIntent;
	
	AdView adView;
	
	public String getText()
	{
		if (activated)
			return "Stop Pause Button";
		else
			return "Start Pause Button";
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pause);

		a = this;

		final Button b = (Button) findViewById(R.id.button1);
		b.setText(getText());
		b.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				if (!activated)
				{
					serviceIntent = new Intent(a, PauseService.class);
					startService(serviceIntent);
					activated = true;
					b.setText(getText());
					onBackPressed();
				}
				else
				{
					stopService(serviceIntent);
					activated = false;
					b.setText(getText());
				}
			}
		});

		  // Create the adView.
	    adView = new AdView(this);
	    adView.setAdUnitId("ca-app-pub-8148658375496745/9345051306");
	    adView.setAdSize(AdSize.BANNER);

	    // Lookup your LinearLayout assuming it's been given
	    // the attribute android:id="@+id/mainLayout".
	    LinearLayout layout = (LinearLayout)findViewById(R.id.adLayout);

	    // Add the adView to it.
	    layout.addView(adView);

	    // Initiate a generic request.
	    AdRequest adRequest = new AdRequest.Builder().build();

	    // Load the adView with the ad request.
	    adView.loadAd(adRequest);

	}

}
