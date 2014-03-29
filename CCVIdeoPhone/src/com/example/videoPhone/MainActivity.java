package com.example.videoPhone;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.SkyLink.MESSAGE";
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/
	
	
	//Called when button is pressed
			public void callSkype (View view){
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
