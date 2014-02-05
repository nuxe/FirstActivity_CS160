package edu.berkeley.cs160.brittanycheng.storytime;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class PreviewActivity extends Activity {
	
	TextView titleView;
	TextView dateView;
	TextView locationView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_preview);
		
		titleView = (TextView) findViewById(R.id.titlePreview);
		dateView = (TextView) findViewById(R.id.datePreview);
		locationView = (TextView) findViewById(R.id.placePreview);
		
		Bundle extras = getIntent().getExtras(); 

		if (extras != null) {
			String title = extras.getString("title");
			String date = extras.getString("date");
			String location = extras.getString("location");
			
			titleView.setText(title);
			dateView.setText("Date: " + date);
			locationView.setText("Location: " + location);
		}
		
		VideoView video = (VideoView) findViewById(R.id.videoPreview);
		video.setVideoPath("http://kushis.me/story3.mp4");
		video.start();
	
		Button button = (Button) findViewById(R.id.donePreviewing);
		 
		button.setOnClickListener(new OnClickListener() {
 
			  @Override
			  public void onClick(View arg0) {
 
			     Toast.makeText(getApplicationContext(), 
                               "Story Published!", Toast.LENGTH_LONG).show();
 
			  }
		});
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.preview, menu);
		return true;
	}
	
}
