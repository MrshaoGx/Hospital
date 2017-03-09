package com.sgx.hospital;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import func.LoginAndReg;

public class MainActivity extends ActionBarActivity {
	
	EditText user,pass;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	Handler hand=new Handler(){@Override
		public void handleMessage(Message msg) {
				Toast.makeText(MainActivity.this, "µÇÂ½³É¹¦£¡", 0).show();
				Intent intent=new Intent(MainActivity.this,MainPage.class);
				startActivity(intent);
			super.handleMessage(msg);
		}};
	public void Logins(View v){
		user=(EditText) findViewById(R.id.reguser);
		pass=(EditText) findViewById(R.id.password);
		final String username=user.getText().toString();
		final String password=pass.getText().toString();
		new Thread(new Runnable() {
			public void run() {
				String dd=LoginAndReg.Login(username, password);
				Message msg=hand.obtainMessage();
				msg.obj=dd;
				msg.what=1;
				hand.sendMessage(msg);
			}
		}).start();
		
		
	}
	public void reg(View v){
		Intent intent=new Intent(MainActivity.this,RegActivity.class);
		startActivity(intent);
	}
	
}
