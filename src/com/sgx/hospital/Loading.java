package com.sgx.hospital;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class Loading extends ActionBarActivity {
	
	Timer tt=new Timer();
	TextView tv;
	int i=5;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		tv=(TextView) findViewById(R.id.part);
		
		tt.schedule(task, 0,1000);
		hand.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Intent intent=new Intent(Loading.this,MainActivity.class);
				startActivity(intent);
				Loading.this.finish();
				
			}
		}, 0);
	}
	Handler hand=new Handler(){@Override
	public void handleMessage(Message msg) {
		tv.setText(Integer.toString(i));
		i--;
		super.handleMessage(msg);
	}};
	TimerTask task=new TimerTask() {
		
		@Override
		public void run() {
				Message msg=hand.obtainMessage();
				msg.obj=0;
				msg.what=0;
				hand.sendMessage(msg);
			
		}
	};
	
	
}
