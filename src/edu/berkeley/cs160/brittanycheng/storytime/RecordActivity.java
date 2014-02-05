package edu.berkeley.cs160.brittanycheng.storytime;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class RecordActivity extends Activity {
    
	String title = "";
    String date = "";
    String location = "";
    ImageButton recordButton;
    Button startOver;
    WebView webView;
    ImageButton left;
    ImageButton right;
    Button doneButton;
	public boolean timerPaused = true;
	
    int photoindex;
    ArrayList<Drawable> photos = new ArrayList<Drawable>();
    ImageView currPhoto;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_record);
		
		
		
		Bundle extras = getIntent().getExtras(); 

		if (extras != null) {
			title = extras.getString("title");
			date = extras.getString("date");
			location = extras.getString("location");
		}
		
		photoindex = 0;
		photos.add(getResources().getDrawable(R.drawable.berkeley));
		photos.add(getResources().getDrawable(R.drawable.tea));
		photos.add(getResources().getDrawable(R.drawable.dumbo));
		photos.add(getResources().getDrawable(R.drawable.family));

		
		currPhoto = (ImageView) findViewById(R.id.currentImageRecording);
		right = (ImageButton) findViewById(R.id.rightArrow);
		left = (ImageButton) findViewById(R.id.leftArrow);
		
//		webView = (WebView) findViewById(R.id.webView1);
//		webView.getSettings().setJavaScriptEnabled(true);
//		webView.setVisibility(View.VISIBLE);
//		
//		webView.loadUrl("http://kushis.me/gif2.png");

		doneButton = (Button) findViewById(R.id.doneRecording);
		doneButton.setVisibility(View.INVISIBLE);
        doneButton.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(RecordActivity.this, PreviewActivity.class);
				i.putExtra("title", title);
				i.putExtra("date", date);
				i.putExtra("location", location);
				RecordActivity.this.startActivity(i);
			}
		});

        
        Thread t = new Thread() {

        	  @Override
        	  public void run() {
        	    try {
        	      while (!isInterrupted()) {
        	        Thread.sleep(1000);
        	        
        	        runOnUiThread(new Runnable() {
        	          @Override
        	          
        	          public void run() {
        	        	  if (timerPaused==false){
        	        	  updateTextView();  }
        	        	  else {
        	        		  
        	        	  }
        	            // update TextView here!
        	        	  
        	          }
        	        });
        	      }
        	    } catch (InterruptedException e) {
        	    }
        	  }
        	};

        	t.start();
        



	
	}
	
	public double i = 0.00; 
	
	
	public void updateTextView(){
		TextView timer= (TextView) findViewById(R.id.timer);

		timer.setText(""+i+"");
		i++;
		
	}


	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.record, menu);
		return true;
	}
	
	public void toggleRecord(View v) {
        recordButton = (ImageButton) findViewById(R.id.recordButton);
        startOver = (Button) findViewById(R.id.startOver);

        if (recordButton.getContentDescription().equals("record")) {
			recordButton.setContentDescription("pause");
			recordButton.setImageResource(R.drawable.pause);
			timerPaused = false;
			startOver.setVisibility(View.VISIBLE);
			
			doneButton.setVisibility(View.VISIBLE);

//			webView.loadUrl("http://kushis.me/recording.gif");


		} else {
			recordButton.setContentDescription("record");
			timerPaused = true;
			doneButton.setVisibility(View.VISIBLE);

			recordButton.setImageResource(R.drawable.mic);
//			webView.loadUrl("http://kushis.me/gif2.png");
			
		}
		startOver.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (startOver.getVisibility() == View.VISIBLE) {
					startOver.setVisibility(View.INVISIBLE);
//					webView.loadUrl("http://kushis.me/gif2.png");
					doneButton.setVisibility(View.INVISIBLE);

					timerPaused = true;
					i = 0.0;
					TextView timer = (TextView) findViewById(R.id.timer);
					timer.setText(""+i+"");
					recordButton.setContentDescription("record");
					recordButton.setImageResource(R.drawable.mic);
				}
			}
		});
		
	}
	
	public void nextPhoto(View v) {
		if (photoindex + 1 == photos.size()) {
			right.setVisibility(View.INVISIBLE);
			left.setVisibility(View.VISIBLE);

			
		} 
		else if (photoindex+2 ==photos.size()){
			photoindex++;
			right.setVisibility(View.VISIBLE);
			left.setVisibility(View.VISIBLE);

			currPhoto.setImageDrawable(photos.get(photoindex));
			right.setImageResource(R.drawable.white);
			left.setImageDrawable(photos.get(photoindex-1));
			
			
		}
		else {
			photoindex++;
			right.setVisibility(View.VISIBLE);
			left.setVisibility(View.VISIBLE);


		currPhoto.setImageDrawable(photos.get(photoindex));
		right.setImageDrawable(photos.get(photoindex+1));
		left.setImageDrawable(photos.get(photoindex-1));
		}
		
	}
	
	public void prevPhoto(View v) {
		if (photoindex == 0) {
			right.setVisibility(View.VISIBLE);

			left.setVisibility(View.INVISIBLE);
			left.setImageResource(R.drawable.white);

			
		} 
		else if (photoindex==1){
			photoindex--;
			left.setVisibility(View.INVISIBLE);
			right.setVisibility(View.VISIBLE);


			currPhoto.setImageDrawable(photos.get(photoindex));
			left.setImageResource(R.drawable.white);
			right.setImageDrawable(photos.get(photoindex+1));

			
		}
		
		else {
			photoindex--;
			left.setVisibility(View.VISIBLE);

			right.setVisibility(View.VISIBLE);

			currPhoto.setImageDrawable(photos.get(photoindex));
			right.setImageDrawable(photos.get(photoindex+1));
			left.setImageDrawable(photos.get(photoindex-1));
		
	
		}
		}

}
