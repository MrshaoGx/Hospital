package com.sgx.hospital;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import func.Book;
import func.ViewMsg;
import tools.Doc;

public class MainPage extends ActionBarActivity {


	NotificationManager manager;
	FrameLayout fm,fm2;
	ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_page);
	  manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
	  fm2=(FrameLayout) findViewById(R.id.fm2);
	  fm=(FrameLayout) findViewById(R.id.fm1);
	  lv=(ListView) findViewById(R.id.listView1);
	}
	@SuppressLint("HandlerLeak")
	Handler hand=new Handler(){
		@SuppressWarnings("deprecation")
		public void handleMessage(Message msg) {
		switch (msg.what) {
		case 1:
			String mess=(String)msg.obj;
			PendingIntent pendingIntent =PendingIntent.getActivity(MainPage.this, 0, new Intent(MainPage.this,MainPage.class), 0);
			int ico=R.drawable.ic_launcher;
			String ticker="激活验证码";
			long when = System.currentTimeMillis();
			
			@SuppressWarnings("deprecation")
			Notification notifi = new Notification(ico,ticker,when);
			
			notifi.setLatestEventInfo(MainPage.this, "Hospital", "您的激活码为："+mess+"，[国鑫科技]",pendingIntent);
			notifi.number=1;
			notifi.flags |=Notification.FLAG_AUTO_CANCEL;
			
			manager.notify(0, notifi);
			
			break;
		case 2:
			Toast.makeText(MainPage.this, "book successful", 0).show();
			
			break;
		case 3:
			Toast.makeText(MainPage.this, "数据获取成功", 0).show();
			List<Doc> lists=(List<Doc>)msg.obj;
			//建立listview
			List<HashMap<String,Object>> data=new ArrayList<HashMap<String,Object>>();
			for(int i=0;i<lists.size();i++){
				HashMap<String,Object> map=new HashMap<String,Object>();
				map.put("1",lists.get(i).getName());
				map.put("2",lists.get(i).getCode());
				map.put("3",lists.get(i).getPart());
				data.add(map);
			}
			SimpleAdapter adapter=new SimpleAdapter(MainPage.this,data,R.layout.lists,new String[]{"1","2","3"},new int[]{R.id.name,R.id.part,R.id.code});
			lv.setAdapter(adapter);
			break;
		default:
			break;
		}
		
		
	};};
	public void books(View v){
		 
		fm.setVisibility(View.VISIBLE);
		fm2.setVisibility(View.INVISIBLE);
	}
	public void viewdoc(View v){
		fm.setVisibility(View.INVISIBLE);
		fm2.setVisibility(View.VISIBLE);
		new Thread(new Runnable() {
			public void run() {
				List<Doc> lists = ViewMsg.ViewDocs("view");
				
				Message msg = hand.obtainMessage();
				msg.obj = lists;
				msg.what = 3;
				hand.sendMessage(msg);
			}
		}).start();
		
	}
	
	public void submits(View v){
		new Thread(new Runnable() {
			public void run() {
				boolean dd = Book.doBook("11");
				Message msg = hand.obtainMessage();
				msg.obj = dd;
				msg.what = 2;
				hand.sendMessage(msg);
			}
		}).start();
		
	}
	public void viewbook(View v){
		new Thread(new Runnable() {
			public void run() {
				String dd = Book.bookView("book");
				Message msg = hand.obtainMessage();
				msg.obj = dd;
				msg.what = 1;
				hand.sendMessage(msg);
			}
		}).start();
		
	}
}
