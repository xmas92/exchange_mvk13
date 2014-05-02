package com.example.app_mainproject;


import android.app.Activity;
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



	}

	public void goToColorsMenu(View view){
		//TODO
		Intent intent = new Intent(OptionsMenu.this, Home.class);
		startActivity(intent);

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
