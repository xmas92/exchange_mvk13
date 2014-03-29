package com.example.videoPhone;

import com.example.videoPhone.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
 
public class FullImageActivity extends Activity  {
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image);
 
        // get intent data
        Intent i = getIntent();
 
        // Selected image id
        int position = i.getExtras().getInt("id");
        ImageAdapter imageAdapter = new ImageAdapter(this);
 
        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
        imageView.setImageResource(imageAdapter.mThumbIds[position]);
        
        //Button for going to settings
        //Placed there for now.
        final Button click=(Button)findViewById(R.id.button1);       
        click.setOnClickListener(new View.OnClickListener() {                                            
                     public void onClick(View v) { 
                    	 //On click go to settings
                    	 Intent launchactivity= new Intent(FullImageActivity.this,Sett.class);                               
                    	 startActivity(launchactivity);                          
                         }
                          });       
    }
}