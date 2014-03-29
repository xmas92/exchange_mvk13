package com.example.videoPhone;

import com.example.videoPhone.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;


/**
 * Main window/activity
 */
public class AndroidGridLayoutActivity extends Activity {
 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_grid_layout);
 
        GridView gridView = (GridView) findViewById(R.id.grid_view);
 
        // Instance of ImageAdapter Class
        gridView.setAdapter(new ImageAdapter(this));
 
        /**
         * On Click event for Single Gridview Item
         * */
        
        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                    int position, long id) {
          	  
                // Send intent to SingleViewActivity
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                // Pass image index
                i.putExtra("id", position);
                startActivity(i);
            }
        });
        
        
    }
}