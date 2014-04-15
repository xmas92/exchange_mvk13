package com.example.vp;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.Menu;
import android.view.View;

public class Home extends Activity {
	
	// User profiles and id:s
	String profile1 = "joni.baitar";
	String profile2 = "";
	String profile3 = "";
	String profile4 = "";
	String profile5 = "";
	String profile6 = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
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
			skypeUriIntent.setData(Uri.parse("skype:" + "joni.baitar" + "?call&video=true"));
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
