package com.joebruckner.whoknows.managers;

public interface DatabaseManager {
	String EMAIL = "Email";
	String PHONE = "Phone";

	void getPosts();
	void getOffers();
	void getPostedPosts();
	void putPost(String title, String description, String contactInfo);
	void getPost(String id);
	void putOffer(String post, String recipient);
	void getOffer(String id);

}
