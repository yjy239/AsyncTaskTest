package com.example.practiseforasynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.app.IntentService;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ProgressBar bar;
	private Button button;
	private TextView text;
	private Button cancel;
	private DownLoadTask dtask;	
	private Button sIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bar = (ProgressBar)findViewById(R.id.progress);
        button = (Button)findViewById(R.id.start);
        text = (TextView)findViewById(R.id.text);
        cancel = (Button)findViewById(R.id.cancel);
        sIntent = (Button)findViewById(R.id.intentService);
        cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(dtask instanceof DownLoadTask){
					dtask.cancel(true);
				}
			}
		});
        
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dtask = new DownLoadTask(text, bar);
				dtask.execute(1000);
			}
		});
        
        sIntent.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent service = new Intent(MainActivity.this, MyIntentService.class);
				Log.e("service", "start");
				service.putExtra("task_action", "Task1");
				startService(service);
				service.putExtra("task_action", "Task2");
				startService(service);
				service.putExtra("task_action", "Task3");
				startService(service);
			}
		});
    }


    
    class network{
    	public void operate(){
    		try{
    			Thread.sleep(100);
    		}catch (InterruptedException e) {
				// TODO: handle exception
    			e.printStackTrace();
			}
    	}
    }
    
    class DownLoadTask extends AsyncTask<Integer, Integer, Integer>{
    	private TextView text;
    	private ProgressBar bar;
    	
    	public DownLoadTask(TextView text,ProgressBar bar){
    		super();
    		this.text = text;
    		this.bar = bar;
    	}
    	
    	@Override
    	protected void onPreExecute(){
    		bar.setProgress(0);
    		text.setText("Async start");
    	}

		@Override
		protected Integer doInBackground(Integer... params) {
			// TODO Auto-generated method stub
			network net = new network();
			int value = 0;
			for(int i = 0;i < 100;i++){
				net.operate();
				value = i;
				publishProgress(i);
			}
			
			return value;
		}
		
		@Override
		protected void onPostExecute(Integer result){
			text.setText("AsyncTask End");
		}
		
		@Override
		protected void onProgressUpdate(Integer... values){
			int value = values[0];
			Log.e("value", ""+value);
			bar.setProgress(value);
			text.setText(String.valueOf(value));
		}
    	
    }
    
}
