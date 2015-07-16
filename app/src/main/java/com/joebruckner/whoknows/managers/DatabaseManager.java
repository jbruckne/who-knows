package com.joebruckner.whoknows.managers;

public interface DatabaseManager {
	String EMAIL = "Email";
	String PHONE = "Phone";

	void getNearbyPosts();
	void getJoinedPosts();
	void getPostedPosts();
	void put(String title, String description, String contactInfo);
	void get(String id);
	void getAttendees(String id);
}
