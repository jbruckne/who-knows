package com.joebruckner.whoknows.models;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;
import java.util.UUID;

public class Beacon {
	long id;
	String title;
	String name;
	Date date;
	String contactInfo;
	String description;
	LatLng location;

	public Beacon(String title) {
		this(title, "Anonymous", new Date(), "(123) 456-7890", "None", null);
	}

	public Beacon(String title, String name, Date date, String contactInfo, String description,
	 LatLng location) {
		this.id = Math.abs(UUID.randomUUID().getLeastSignificantBits());
		this.title = title;
		this.name = name;
		this.date = date;
		this.contactInfo = contactInfo;
		this.description = description;
		this.location = location;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getName() {
		return name;
	}

	public Date getDate() {
		return date;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public String getDescription() {
		return description;
	}

	public LatLng getLocation() {
		return location;
	}
}
