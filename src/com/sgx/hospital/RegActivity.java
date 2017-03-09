package com.sgx.hospital;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import func.LoginAndReg;

public class RegActivity extends ActionBarActivity {
	EditText user, pass, passes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
	}

	public void regs(View v) {
		user = (EditText) findViewById(R.id.reguser);
		pass = (EditText) findViewById(R.id.passwords);
		passes = (EditText) findViewById(R.id.passess);
		final String username = user.getText().toString();
		final String password = pass.getText().toString();
		final String passwords = passes.getText().toString();
		new Thread(new Runnable() {
			public void run() {
				String dd = LoginAndReg.Reg(username, password);
				Message msg = hand.obtainMessage();
				msg.obj = dd;
				msg.what = 1;
				hand.sendMessage(msg);
			}
		}).start();

	}

	Handler hand=new Handler(){
	public void handleMessage(Message msg) {
		String x=(String)msg.obj;
		if(x!=null){
			Toast.makeText(RegActivity.this, "×¢²á³É¹¦£¡", 0).show();
		
		super.handleMessage(msg);
	}}};
	
	public void returns(View v){
		
		this.finish();
	}
}