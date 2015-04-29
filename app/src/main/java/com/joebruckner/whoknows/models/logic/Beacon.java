package com.joebruckner.whoknows.models.logic;

public class Beacon {
	String title;
	String name;
	String date;
	String contactInfo;
	String description;

	public Beacon(String title) {
		this(title, "Anonymous", "10/11/12", "(123) 456-7890", "None");
	}

	public Beacon(String title, String name, String date, String contactInfo, String description) {
		this.title = title;
		this.name = name;
		this.date = date;
		this.contactInfo = contactInfo;
		this.description = description;
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
}
