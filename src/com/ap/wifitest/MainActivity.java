package com.ap.wifitest;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	private static IntentFilter mIntentFilter;
	private static WifiDataReceiver receiver;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mIntentFilter = new IntentFilter();
		mIntentFilter
				.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
		receiver = new WifiDataReceiver();
		registerReceiver(receiver,
				mIntentFilter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	class WifiDataReceiver extends BroadcastReceiver {
	    public void onReceive(Context c, Intent intent) {
	        Log.v("msg", "onReceive WifiDataReceiver");
	        ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
	        NetworkInfo.State mWifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
	        Log.v("msg", "mWifi : " + mWifi);
	        if (mWifi != NetworkInfo.State.CONNECTED){
	        	try {
	                Thread.sleep(10000);
	            } catch (InterruptedException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	            Log.v("msg", "Disconnect  WifiDataReceiver");
	        } else {
	            Log.v("msg", "connected");
	        }
	    }
	}
}
