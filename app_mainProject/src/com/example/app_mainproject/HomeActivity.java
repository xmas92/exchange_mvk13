package com.example.app_mainproject;



import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.ImageView;

public class HomeActivity extends Activity {

	private static final int FIVE_SECONDS = 5 * 1000; // 5s * 1000 ms/s
	private long fiveFingerDownTime = -1;

	// User profiles and id:s
	String profile1 = "";
	String profile2 = "";
	String profile3 = "";
	String profile4 = "";
	String profile5 = "";
	String profile6 = "";

	@Override
	protected void onDestroy() {
		super.onDestroy();
		DataStorage.Close();
	}
	

	@Override
	protected void onStart() {
		super.onStart();
		Integer color = DataStorage.GetInt("themeColor");
		if (color == null) color = 0xffffffff;
	    getWindow().getDecorView().setBackgroundColor(color);
	    
		List<Contact> cl = DataStorage.GetAllContacts();
		Collections.sort(cl);
		int i = 0;
		for (Contact c : cl) {
			if (i >= 6) break;
			ImageView v = GetListView(i);
			SetString(i, c.getContactId());
			v.setImageBitmap(c.getImage());
			v.setVisibility(View.VISIBLE);
			i++;
		}
		for (;i< 6;i++)
			GetListView(i).setVisibility(View.INVISIBLE);
	}
	
	private void SetString(int i, String s) {
		switch (i) {
		case 0:
			profile1 = s;
		case 1:
			profile2 = s;
		case 2:
			profile3 = s;
		case 3:
			profile4 = s;
		case 4:
			profile5 = s;
		case 5:
			profile6 = s;
		}
	}
	
	private ImageView GetListView(int i) {
		switch (i) {
		case 0:
			return (ImageView)findViewById(R.id.uid1);
		case 1:
			return (ImageView)findViewById(R.id.uid2);
		case 2:
			return (ImageView)findViewById(R.id.uid3);
		case 3:
			return (ImageView)findViewById(R.id.uid4);
		case 4:
			return (ImageView)findViewById(R.id.uid5);
		case 5:
			return (ImageView)findViewById(R.id.uid6);
		}
		return null;
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		// Init SQLite Storage
		DataStorage.init(getApplicationContext());
		// TODO REMOVE BELOW!
//		DataStorage.Clear();
		// TODO REMOVE ABOVE!
		

		//
		findViewById(R.id.layout).setOnTouchListener(new OnTouchListener() {        

			@Override
			public boolean onTouch(View v, MotionEvent ev) {
				final int action = ev.getAction();


				switch (action & MotionEvent.ACTION_MASK) {
				case MotionEvent.ACTION_POINTER_DOWN:

					if (ev.getPointerCount() == 5) {
						// We have five fingers touching, so start the timer

						fiveFingerDownTime = System.currentTimeMillis();

					}
					break;

				case MotionEvent.ACTION_POINTER_UP:
					if (ev.getPointerCount() < 5) {
						// Fewer than five fingers, so reset the timer
						fiveFingerDownTime = -1;
					}
					break;
				case MotionEvent.ACTION_MOVE:
					final long now = System.currentTimeMillis();
					if (fiveFingerDownTime != -1 && now - fiveFingerDownTime > FIVE_SECONDS) {
						// Five fingers have been down for 5 seconds!
						// TODO Do something
						fiveFingerDownTime = -1;
						System.out.println("Five seconds has passed, an eternity awaits");
						Intent intent = new Intent(HomeActivity.this, OptionsMenuActivity.class);
						startActivity(intent);
						System.out.println();
					}

					break;
				}

				return true;
			}
		});






	}
	//
	//	@Override
	//	public boolean onCreateOptionsMenu(Menu menu) {
	//		// Inflate the menu; this adds items to the action bar if it is present.
	//		getMenuInflater().inflate(R.menu.home, menu);
	//		return true;
	//	}


	//Called when button is pressed
	public void callSkypeView1 (View view){
		if(!isSkypeClientInstalled(this)){
			goToMarket(this);
		}
		else{
			Intent skypeUriIntent = new Intent("android.intent.action.VIEW");
			skypeUriIntent.setClassName("com.skype.raider", "com.skype.raider.Main");

			//Hard-coded, change to the skype username you want to call.
			skypeUriIntent.setData(Uri.parse("skype:" + profile1 + "?call&video=true"));
			startActivity(skypeUriIntent);
		}

	}

	public void callSkypeView2 (View view){
		if(!isSkypeClientInstalled(this)){
			goToMarket(this);
		}
		else{
			Intent skypeUriIntent = new Intent("android.intent.action.VIEW");
			skypeUriIntent.setClassName("com.skype.raider", "com.skype.raider.Main");

			//Hard-coded, change to the skype username you want to call.
			skypeUriIntent.setData(Uri.parse("skype:" + profile2 + "?call&video=true"));
			startActivity(skypeUriIntent);
		}

	}

	public void callSkypeView3 (View view){
		if(!isSkypeClientInstalled(this)){
			goToMarket(this);
		}
		else{
			Intent skypeUriIntent = new Intent("android.intent.action.VIEW");
			skypeUriIntent.setClassName("com.skype.raider", "com.skype.raider.Main");

			//Hard-coded, change to the skype username you want to call.
			skypeUriIntent.setData(Uri.parse("skype:" + profile3 + "?call&video=true"));
			startActivity(skypeUriIntent);
		}

	}

	public void callSkypeView4 (View view){
		if(!isSkypeClientInstalled(this)){
			goToMarket(this);
		}
		else{
			Intent skypeUriIntent = new Intent("android.intent.action.VIEW");
			skypeUriIntent.setClassName("com.skype.raider", "com.skype.raider.Main");

			//Hard-coded, change to the skype username you want to call.
			skypeUriIntent.setData(Uri.parse("skype:" + profile4 + "?call&video=true"));
			startActivity(skypeUriIntent);
		}

	}

	public void callSkypeView5 (View view){
		if(!isSkypeClientInstalled(this)){
			goToMarket(this);
		}
		else{
			Intent skypeUriIntent = new Intent("android.intent.action.VIEW");
			skypeUriIntent.setClassName("com.skype.raider", "com.skype.raider.Main");

			//Hard-coded, change to the skype username you want to call.
			skypeUriIntent.setData(Uri.parse("skype:" + profile5 + "?call&video=true"));
			startActivity(skypeUriIntent);
		}

	}

	public void callSkypeView6 (View view){
		if(!isSkypeClientInstalled(this)){
			goToMarket(this);
		}
		else{
			Intent skypeUriIntent = new Intent("android.intent.action.VIEW");
			skypeUriIntent.setClassName("com.skype.raider", "com.skype.raider.Main");

			//Hard-coded, change to the skype username you want to call.
			skypeUriIntent.setData(Uri.parse("skype:" + profile6 + "?call&video=true"));
			startActivity(skypeUriIntent);
		}

	}


	//Install the Skype client through the market: URI scheme.
	public void goToMarket(Context myContext) {
		Uri marketUri = Uri.parse("market://details?id=com.skype.raider");
		Intent myIntent = new Intent(Intent.ACTION_VIEW, marketUri);
		myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		myContext.startActivity(myIntent);

		return;
	}

	//Determine whether the Skype for Android client is installed on this device.
	public boolean isSkypeClientInstalled(Context myContext) {
		PackageManager myPackageMgr = myContext.getPackageManager();
		try {
			myPackageMgr.getPackageInfo("com.skype.raider", PackageManager.GET_ACTIVITIES);
		}
		catch (PackageManager.NameNotFoundException e) {
			return (false);
		}
		return (true);
	}



}



