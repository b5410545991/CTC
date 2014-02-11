package com.example.ctc;



import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

	private int age;
	private int height;
	private int weight;
	private boolean sex;
	private boolean have = false;
	RadioGroup radioGroup4;
	RadioButton radioButton1;
	RadioButton radioButton0;
	
	TextView editText1;
	TextView editText2;
	TextView editText3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		radioGroup4 = (RadioGroup) findViewById(R.id.radioGroup4);
		radioButton1 = (RadioButton) findViewById(R.id.radio1);
		radioButton0 = (RadioButton) findViewById(R.id.radio0);

		OnClickListener radioClickListener = new OnClickListener() {

			public void onClick(View v) {
				if (v.getId() == radioGroup4.getCheckedRadioButtonId() && have) {
					radioGroup4.clearCheck();
				} else {
					have = true;
				}
			}
		};

		OnCheckedChangeListener radioCheckChangeListener = new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				have = false;
				
			}
		};
		
		radioButton1.setOnCheckedChangeListener(radioCheckChangeListener);
		radioButton0.setOnCheckedChangeListener(radioCheckChangeListener);

		radioButton1.setOnClickListener(radioClickListener);
		radioButton0.setOnClickListener(radioClickListener);	
		
		if(radioButton0.isChecked()){
			sex = false;
		}
		if(radioButton1.isChecked()) {
			sex = true;
		}
		
		//read string in text box
		editText1 = (TextView) findViewById(R.id.editText1);
		editText2 = (TextView) findViewById(R.id.editText2);
		editText3 = (TextView) findViewById(R.id.editText3);

		final Button btn_CalBMR = (Button) findViewById(R.id.CalButton);
		btn_CalBMR.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				
				Bundle localBundle = new Bundle();
				
				localBundle.putString("Age", editText1.getText().toString());
				localBundle.putString("Height", editText2.getText().toString());
				localBundle.putString("Weight", editText3.getText().toString());
				Intent localIntent = new Intent(MainActivity.this, ResultActivity.class);
				localIntent.putExtras(localBundle);
				startActivity(localIntent);
			
				finish();

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean setSex(boolean sex){
		return this.sex = sex;
	}
	
	public boolean getSex(){
		return sex;
	}

}
