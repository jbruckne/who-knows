package com.joebruckner.whoknows.managers;

public interface DatabaseManager {
	void getNearbyPosts();
	void getJoinedPosts();
	void getPostedPosts();
	void put(String title, String description, String contactInfo);
	void get(String id);
	void getAttendees(String id);
}
