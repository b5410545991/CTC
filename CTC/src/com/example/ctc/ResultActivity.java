package com.example.ctc;



import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		
		//recieve value from main page
		String a = getIntent().getStringExtra("Age");
		String h = getIntent().getStringExtra("Height");
		String w = getIntent().getStringExtra("Weight");
		boolean sex = getIntent().getBooleanExtra("Sex", false);
		
		//chang string to integer type
		double age = Integer.parseInt(a);
		double height = Integer.parseInt(h);
		double weight = Integer.parseInt(w);
		
		/*
		logic part
		Male : 66.47 + (13.75 X Weight) + (5 X height) - (6.76 X age)
		Female : 6555.1 + (9.56 X Weight) + (1.85 X height) -(4.68 X age)
		*/
		double result = 0;
		if(sex == true){
			result = 655.1 +  (9.56 * weight) + (1.85 * height) -(4.68 * age);
		}
		else{
			result = 66.47 + (13.75 * weight) + (5 * height) - (6.76 * age);
		}
		
		//show the result
		TextView text_TotalCalorie = (TextView) findViewById(R.id.textView1);
		text_TotalCalorie.setText("" + result + " Kcal/day");
		
		//back to main page
		final Button btn_back = (Button) findViewById(R.id.button1);
		btn_back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent newActivity = new Intent(ResultActivity.this,
						MainActivity.class);
				startActivity(newActivity);
				finish();

			}
		});
		
		//for temporaly
		final Button btn_food = (Button) findViewById(R.id.button2);
		btn_food.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Uri uri = Uri.parse("http://www.calorieking.com/foods/");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri); 
				startActivity(intent);
				finish();

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}

}
