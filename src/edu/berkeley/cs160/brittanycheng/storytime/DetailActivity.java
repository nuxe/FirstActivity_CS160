package edu.berkeley.cs160.brittanycheng.storytime;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class DetailActivity extends Activity {
	
	EditText title;
	TextView showDate;
	EditText location;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		//Bundle extras = this.getIntent().getExtras();
		//ArrayList photoList = (ArrayList) extras.getSerializable("photos");
		
		title = (EditText) findViewById(R.id.editTitle);
    	showDate = (TextView) findViewById(R.id.editDate);
		location = (EditText) findViewById(R.id.editPlace);
		
		Button nextButton = (Button) findViewById(R.id.btnNext);
        nextButton.setOnClickListener( new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(DetailActivity.this, RecordActivity.class);
				
				if (title.getText() != null) {
					i.putExtra("title", title.getText().toString());
				}
				if (showDate.getText() != null) {
					i.putExtra("date", showDate.getText().toString());
				}
				if (location.getText() != null) {
					i.putExtra("location", location.getText().toString());					
				}
				DetailActivity.this.startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail, menu);
		return true;
	}
	
	public void showDatePickerDialog(View v) {
		
		DialogFragment newFragment = new DatePickerFragment() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
            	month = month + 1;
            	showDate.setText("" + month + "/" + day + "/" + year);
            }
		};
		newFragment.show(getFragmentManager(), "datePicker");
		
		//DialogFragment newFragment = new DatePickerFragment();
		//newFragment.show(getFragmentManager(), "datePicker");
	}

}
