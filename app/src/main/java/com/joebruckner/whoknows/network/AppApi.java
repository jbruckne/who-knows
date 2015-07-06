package com.joebruckner.whoknows.network;

public interface AppApi {
	void getNearbyPosts();
	void getJoinedPosts();
	void getPostedPosts();
	void put(String title, String description, String contactInfo);
	void get(String id);
	void getAttendees(String id);
}
