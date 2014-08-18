package com.example.smsapp;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.Menu;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button btnSend;
	EditText etPhoneNo;
	EditText etMsg;

    private BroadcastReceiver mIntentReceiver;
    TextView timerTv;
    TextView mobNoVeryfyTv;

    private ProgressBar progressBar;
    static Boolean timeOut = true;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etPhoneNo = (EditText) findViewById(R.id.phno);
		btnSend = (Button) findViewById(R.id.send);

		btnSend.setOnClickListener(new View.OnClickListener() {
	         public void onClick(View view) {

	             sendSMSMessage();

	         }
	         
	       });
	}
	
    @Override
    protected void onResume() {
           super.onResume();

           IntentFilter intentFilter = new IntentFilter("SmsMessage.intent.MAIN");
           mIntentReceiver = new BroadcastReceiver() {
                  @Override
                  public void onReceive(Context context, Intent intent) {
                        String msg = intent.getStringExtra("get_msg");

                        // Process the sms format and extract body &amp; phoneNumber
                        msg = msg.replace("\n", "");
                        String body = msg.substring(msg.lastIndexOf(":") + 1,
                                      msg.length());
                        String pNumber = msg.substring(0, msg.lastIndexOf(":"));

                        // Add it to the list or do whatever you wish to
                        Log.e("onResume", "" + msg + body + pNumber);

                        Toast.makeText(getApplicationContext(), body, 1).show();

                        // check body content with your validation code mine is
                        // success123

                        if (body.equalsIgnoreCase("123")) {

                               Toast.makeText(getApplicationContext(),
                                             "Authentication Success.", 1).show();
                               mobNoVeryfyTv.setText("Authentication Success.");

                        } else {

                               // if message is contains some invalide code
                        	  Toast.makeText(getApplicationContext(),
                                      "Authentication Failure.", 1).show();
                               mobNoVeryfyTv.setText("Authentication Fails.");

                        //     SignInWaitingActivity.this.finish();

                        }

                  }
           };
           this.registerReceiver(mIntentReceiver, intentFilter);
    }

    @Override
    protected void onPause() {

           super.onPause();
           this.unregisterReceiver(this.mIntentReceiver);
    }
		 protected void sendSMSMessage() {
		      Log.i("Send SMS", "");

		      String phoneNo = etPhoneNo.getText().toString();
		      String message = "Register Pliz";

		      try {
		         SmsManager smsManager = SmsManager.getDefault();
		         smsManager.sendTextMessage(phoneNo, null, message, null, null);
		         Toast.makeText(getApplicationContext(), "SMS sent.",
		         Toast.LENGTH_LONG).show();
		      } catch (Exception e) {
		         Toast.makeText(getApplicationContext(),
		         "SMS faild, please try again.",
		         Toast.LENGTH_LONG).show();
		         e.printStackTrace();
		      }

		   }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}


}


   