package com.joebruckner.whoknows.models.logic;

import android.location.Location;

public class Beacon {
	String title;
	String name;
	String date;
	String contactInfo;
	String description;
	Location location;

	public Beacon(String title) {
		this(title, "Anonymous", "10/11/12", "(123) 456-7890", "None", null);
	}

	public Beacon(String title, String name, String date, String contactInfo, String description,
	 Location location) {
		this.title = title;
		this.name = name;
		this.date = date;
		this.contactInfo = contactInfo;
		this.description = description;
		this.location = location;
	}

	public String getTitle() {
		return title;
	}

	public String getName() {
		return name;
	}

	public String getDate() {
		return date;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public String getDescription() {
		return description;
	}

	public Location getLocation() {
		return location;
	}
}
