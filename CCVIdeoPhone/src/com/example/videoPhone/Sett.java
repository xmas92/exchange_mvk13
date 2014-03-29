package com.example.videoPhone;

/**
 * The settings class for the main menu
 */
import com.example.videoPhone.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Sett extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity2);
	
	    //Button for going back
	    final Button click=(Button)findViewById(R.id.button1);       
        click.setOnClickListener(new View.OnClickListener() {                                            
                     public void onClick(View v) {
                    	 
                    	 //Button going back to previous intent
                    	 Intent launchactivity= new Intent(Sett.this,AndroidGridLayoutActivity.class);                               
                    	 startActivity(launchactivity);                          
                         }
                          });
         	
        }
	

}
