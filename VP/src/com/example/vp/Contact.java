package com.example.vp;


import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class Contact implements Comparable<Contact> {
	private String imgPath;		// Path to the image
	private String name;		// Name displayed to the user
	private String contactId;   // ContactId, this is the ID that is used internally
	private int position; 		// Position on the contacts field
	
	private ImageView image;
	
	// Default constructor
	public Contact(String imgPath, String name, String contactId, int position) {
		init(imgPath, name, contactId, position);
	}
	
	// Construct from a string
	public Contact(String input) {
		String[] parts = input.split(";;");
		init(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]));
	}
	
	// Dummy constructor since we need 2 different ones and they can't effectively call each other
	private void init(String imgPath, String name, String contactId, int position) {
		this.imgPath = imgPath;
		this.name = name;
		this.contactId = contactId;
		this.position = position;
		
		// This block is very untested, unlikely to work like this
		//image = new ImageView(null);
		//Drawable  drawable  = Drawable.createFromPath(imgPath);
		//image.setImageDrawable(drawable);
	}
	
	// Converts the object to a string that we can save in a file
	public String getSaveableString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(imgPath);
		sb.append(";;");
		sb.append(name);
		sb.append(";;");
		sb.append(contactId);
		sb.append(";;");
		sb.append(position);
		
		return sb.toString();
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

	public ImageView getImage() {
		return image;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
		Drawable  drawable  = Drawable.createFromPath(imgPath);
		image.setImageDrawable(drawable);
	}
}
