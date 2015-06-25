package com.joebruckner.whoknows.utilities;

import com.joebruckner.whoknows.models.Post;

import java.util.List;

public interface AppApi {
	List<Post> getNearbyPosts();
	List<Post> getJoinedPosts();
	List<Post> getPostedPosts();
	void put(String title, String description, String contactInfo);
	Post get(String id);
	List<String> getAttendees(String id);
}
