package com.example.app_mainproject;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class Home extends Activity {

	private static final int FIVE_SECONDS = 5 * 1000; // 5s * 1000 ms/s
	private long fiveFingerDownTime = -1;

	// User profiles and id:s
	String profile1 = "joni.baitar";
	String profile2 = "rob.rob342";
	String profile3 = "";
	String profile4 = "";
	String profile5 = "";
	String profile6 = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		// Init SQLite Storage
		DataStorage.init(getApplicationContext());

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
					final long now = System.currentTimeMillis();
					if (now - fiveFingerDownTime > FIVE_SECONDS && fiveFingerDownTime != -1) {
						// Five fingers have been down for 5 seconds!
						// TODO Do something
						System.out.println("Five seconds has passed, an eternity awaits");
						Intent intent = new Intent(Home.this, OptionsMenu.class);
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
			skypeUriIntent.setData(Uri.parse("skype:" + "joni.baitar" + "?call&video=true"));
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
			skypeUriIntent.setData(Uri.parse("skype:" + "joni.baitar" + "?call&video=true"));
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
			skypeUriIntent.setData(Uri.parse("skype:" + "joni.baitar" + "?call&video=true"));
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
			skypeUriIntent.setData(Uri.parse("skype:" + "joni.baitar" + "?call&video=true"));
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



