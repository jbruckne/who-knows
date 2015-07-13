package com.joebruckner.whoknows.events;

import com.joebruckner.whoknows.models.Post;

import java.util.List;

public class PostsFetchedEvent extends Event {
	List<Post> posts;

	public PostsFetchedEvent(int status, List<Post> posts) {
		this(status, Event.SUCCESS);
		this.posts = posts;
	}

	public PostsFetchedEvent(int status, int error) {
		this.status = status;
		this.error = error;
		this.posts = null;
	}

	public List<Post> getPosts() {
		return posts;
	}
}
