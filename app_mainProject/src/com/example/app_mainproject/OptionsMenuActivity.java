package com.example.app_mainproject;


import yuku.ambilwarna.AmbilWarnaDialog;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class OptionsMenuActivity extends Activity {

	// Buttons


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options_menu);

	}

	public void goToColorsMenu(View view){
		//TODO
		Integer color = DataStorage.GetInt("themeColor");
		if (color == null) color = 0x000000ff;
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
		Intent intent = new Intent(OptionsMenuActivity.this, ContactsActivity.class);
		startActivity(intent);

	}

	public void goToGeneralMenu(View view){
		//TODO

		finish(); // end activity
	}





	public void goBackButton(View view){

		finish(); // end activity

	}

	public void goConfirmButton(View view){

		//TODO 
		//Save settings

		finish(); // end activity


	}



	//	@Override
	//	public boolean onCreateOptionsMenu(Menu menu) {
	//		// Inflate the menu; this adds items to the action bar if it is present.
	//		getMenuInflater().inflate(R.menu.options_menu, menu);
	//		return true;
	//	}

}
