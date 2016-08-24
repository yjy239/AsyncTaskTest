package com.example.practiseforasynctask;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService{
	private static String TAG = "IntentService";
	
	public MyIntentService() {
		super(TAG);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		String action = intent.getStringExtra("task_action");
		Log.e(TAG, "Receive task :"+action);
//		SystemClock.sleep(3000);
		if("Task1".equals(action)){
			Log.e(TAG, "Handle task:"+action);
		}
	}
	
	@Override
	public void onDestroy(){
		Log.e(TAG, "IntentService Destroy");
		super.onDestroy();
	}
}
