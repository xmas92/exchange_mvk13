package com.example.videoPhone;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

import android.content.Context;

public class ContactList {
	private ArrayList<Contact> list;
	private Context context;
	
	public ContactList(Context context) {
		list = new ArrayList<Contact>();
		this.context = context;
	}
	
	// Add a new contact with an auto-generated position
	public void addContact(String name, String contactId, String imgPath) {
		addContact(name, contactId, imgPath, getNextPosition());
	}
	
	// Add a new contact
	public void addContact(String name, String contactId, String imgPath, int position) {
		Contact contact = new Contact(imgPath, name, contactId, position);
		addContact(contact);
	}
	
	// Add a contact object
	public void addContact(Contact contact) {
		list.add(contact);
	}
	
	// Returns the full contact list, sorted by position.
	public ArrayList<Contact> getList() {
		Collections.sort(list);
		return list;
	}
	
	// Goes through all positions, and returns the next one after the highest
	public int getNextPosition() {
		int highestPos = 0;
		
		for (Contact c :  list) {
			if (c.getPosition() > highestPos) {
				highestPos = c.getPosition();
			}
		}
		
		return highestPos+1;
	}
	
	// Move all positions one step higher after (and including) a certain number.
	// Use if you are adding a contact between two others.
	public void movePositions(int after) {
		for (Contact c :  list) {
			if (c.getPosition() >= after) { // OBS!!!   >=
				c.setPosition(c.getPosition()+1);
			}
		}
	}
	
	public int getNumContacs() {
		return list.size();
	}
	
	// Saves the contact list to a file
	public void saveToFile(String fileName) {
		context.deleteFile(fileName); // delete the old file
		
		FileOutputStream outputStream;
		StringBuilder str = new StringBuilder();

		// Get all contacts and save them in a format. Each Variable is separated by ;; 
		// and each contact is separated by a line break.
		for (Contact c :  list) {
			str.append(c.getSaveableString());
			str.append("\n");
		}
		
		try {
			outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			outputStream.write(str.toString().getBytes());
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Imports contact list from a file
	public void importContactsFromFile(String fileName) {
		FileInputStream inputStream;
	
		try {
			inputStream = context.openFileInput(fileName);
 
			StringBuilder str = new StringBuilder();
			int content;
			while ((content = inputStream.read()) != -1) {
				str.append((char)content);
			}
			
			String[] parts = str.toString().split("\n");
			
			for (String part : parts) {
				addContact(new Contact(part));
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
}
