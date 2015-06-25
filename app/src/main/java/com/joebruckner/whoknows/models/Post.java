package com.joebruckner.whoknows.models;

public class Post {
	String id;
	String title;
	String name;
	long date;
	String contactInfo;
	String description;
	double lat;
	double lng;

	public Post() {
	}

	public Post(String id, String title, String name, long date, String contactInfo, String
			description, double lat, double lng) {
		this.id = id;
		this.title = title;
		this.name = name;
		this.date = date;
		this.contactInfo = contactInfo;
		this.description = description;
		this.lat = lat;
		this.lng = lng;
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getName() {
		return name;
	}

	public long getDate() {
		return date;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public String getDescription() {
		return description;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}
}
