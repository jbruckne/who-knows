package com.joebruckner.whoknows.utilities;

import com.joebruckner.whoknows.models.Post;

import java.util.ArrayList;
import java.util.List;

public class TestAppApi implements AppApi {
	List<Post> posts;
	AccountApi api;

	public TestAppApi(AccountApi api) {
		this.api = api;
		this.posts = new ArrayList<>();
	}

	@Override public List<Post> getNearbyPosts() {
		if (posts.isEmpty()) for (int i = 0; i < 15; i++)
			posts.add(new Post());
		return posts;
	}

	@Override public List<Post> getJoinedPosts() {
		if (posts.isEmpty()) for (int i = 0; i < 15; i++)
			posts.add(new Post());
		return posts;
	}

	@Override public List<Post> getPostedPosts() {
		List<Post> matched = new ArrayList<>();
		for (Post post : posts) {
			if (post.getName().equals(api.getName()))
				matched.add(post);
		}
		return matched;
	}

	@Override public void put(String title, String description, String contactInfo) {
		Post post = new Post();
		posts.add(post);
	}

	@Override public Post get(String id) {
		for (Post post : posts)
			if (post.getId().equals(id)) return post;
		return null;
	}

	@Override public List<String> getAttendees(String id) {
		List<String> attendees = new ArrayList<>();
		for (Post post : posts)
			if (!attendees.contains(post.getName())) attendees.add(post.getName());
		return attendees;
	}
}
