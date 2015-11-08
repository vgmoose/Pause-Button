package com.vgmoose.pausebutton;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.google.android.gms.ads.*;

import java.io.IOException;

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
					boolean hasRoot = false;
					try {
						java.lang.Process process = Runtime.getRuntime().exec("su");
						hasRoot = true;
					} catch (IOException e) {
                        rootMessage();
					}
					if (hasRoot) {
						serviceIntent = new Intent(a, PauseService.class);
						startService(serviceIntent);
						activated = true;
						b.setText(getText());
						onBackPressed();
					}
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

	private void rootMessage()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("This application requires system level access (root) that your device currently" +
                " does not support.\n\nIt isn't possible to pause other applications without root access!").setTitle("No Root Detected!")

                .setPositiveButton("Learn more", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.helproot.me/"+android.os.Build.DEVICE));
                        startActivity(browserIntent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
	}

}
