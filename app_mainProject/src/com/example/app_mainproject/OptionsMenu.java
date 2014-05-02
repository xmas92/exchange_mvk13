package com.example.app_mainproject;


import yuku.ambilwarna.AmbilWarnaDialog;
import yuku.ambilwarna.AmbilWarnaKotak;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;


public class OptionsMenu extends Activity {

	// Buttons


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_optionsmenu);
		// TODO REMOVE ONLY FOR TESTING BELOW
		DataStorage.init(getApplicationContext());
		// TODO REMOVE ONLY FOR TESTING ABOVE


	}

	public void goToColorsMenu(View view){
		//TODO
		Integer color = DataStorage.GetInt("themeColor");
		if (color == null) color = 0xff0000ff;
		 AmbilWarnaDialog dialog = new AmbilWarnaDialog(this, color,
		            new AmbilWarnaDialog.OnAmbilWarnaListener() {
		        @Override
		        public void onOk(AmbilWarnaDialog dialog, int color) {
		                // color is the color selected by the user.
		        	DataStorage.PutInt("themeColor", color);
		        }
		                
		        @Override
		        public void onCancel(AmbilWarnaDialog dialog) {
		                // cancel was selected by the user
		        }
		    });
		    dialog.show();
	}

	public void goToContactsMenu(View view){
		//TODO
		Intent intent = new Intent(OptionsMenu.this, TestActivity.class);
		startActivity(intent);

	}

	public void goToGeneralMenu(View view){
		//TODO
		Intent intent = new Intent(OptionsMenu.this, Home.class);
		startActivity(intent);

	}





	public void goBackButton(View view){

		finish(); // end activity

	}

	public void goConfirmButton(View view){

		//TODO 
		//Save settings

		Intent intent = new Intent(OptionsMenu.this, Home.class);
		startActivity(intent);


	}



	//	@Override
	//	public boolean onCreateOptionsMenu(Menu menu) {
	//		// Inflate the menu; this adds items to the action bar if it is present.
	//		getMenuInflater().inflate(R.menu.options_menu, menu);
	//		return true;
	//	}

}
