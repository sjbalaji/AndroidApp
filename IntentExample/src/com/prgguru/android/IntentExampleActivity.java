package com.prgguru.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IntentExampleActivity extends Activity {
	/** Called when the activity is first created. */
	EditText nameEditCtrl;
	Button btnCtlr;
	String name;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		nameEditCtrl = (EditText) findViewById(R.id.editText1);
		btnCtlr = (Button) findViewById(R.id.button1);
		btnCtlr.setOnClickListener(new ButtonClickHandler());
	}

	public class ButtonClickHandler implements View.OnClickListener {
		public void onClick(View view) {
			if (nameEditCtrl != null && nameEditCtrl.getText().length() != 0) {
				name = nameEditCtrl.getText().toString();
			} else {
				name = "Guest";
			}
			Intent intObj = new Intent(IntentExampleActivity.this,
					GreetingActivity.class);
			intObj.putExtra("USERNAME", name);
			startActivity(intObj);
		}
	}
}