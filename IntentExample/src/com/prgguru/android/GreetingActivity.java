package com.prgguru.android;

import com.prgguru.android.IntentExampleActivity.ButtonClickHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GreetingActivity extends Activity {
	TextView greetMsg;
Button btnNext;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.greeting);
		greetMsg = (TextView) findViewById(R.id.textView1);
		btnNext = (Button) findViewById(R.id.button2);
		btnNext.setOnClickListener(new ButtonClickHandler());
		Intent intename = getIntent();
		String uname = (String) intename.getSerializableExtra("USERNAME");
		greetMsg.setText("Verified " + uname);
	}
	public class ButtonClickHandler implements View.OnClickListener {
		public void onClick(View view) {
			
			Intent intObj1 = new Intent(GreetingActivity.this,PhotoCaptureExample.class);
			startActivity(intObj1);
		}
	}
}
