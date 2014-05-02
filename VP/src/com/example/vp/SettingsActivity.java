package com.example.vp;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class SettingsActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
        
        System.err.println("1");
        View v = findViewById(R.id.contact_list_group);
        LinearLayout group = (LinearLayout) v;
        System.err.println("2");
        ContactList cl = new ContactList(this);
        //cl.addContact("", "Mikael Andersson", "mikael.p.andersson");
        System.err.println("3");
        cl.addContact("Johan Storby", "jostor11", "");
        cl.addContact("Mikael Andersson", "mikael.p.andersson", "");
        ArrayList<Contact> contacts = cl.getList();
        
        for(Contact c : contacts) {
        	Button b = new Button(this);
        	b.setText(c.getName());
        	group.addView(b);
        	System.err.println("4");
        }
        System.err.println("5");
    }
}
