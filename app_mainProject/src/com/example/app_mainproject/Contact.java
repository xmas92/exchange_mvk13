package com.example.app_mainproject;


import android.graphics.Bitmap;

public class Contact implements Comparable<Contact> {
	public int uid = -1;
	private String name;		// Name displayed to the user
	private String contactId;   // ContactId, this is the ID that is used internally
	private int position; 		// Position on the contacts field

	private Bitmap image;

	// Default constructor
	public Contact() {
	}

	@Override
	// Implementing comparable in order to be able to sort using Java's default functions
	public int compareTo(Contact other) {
		if (this.position > other.position) 
			return 1;
		if (this.position < other.position) 
			return -1;
		return 0;
	}

	// Getters and setters.
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = Bitmap.createBitmap(image);
	}
}