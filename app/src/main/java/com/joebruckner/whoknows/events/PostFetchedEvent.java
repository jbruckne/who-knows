package com.joebruckner.whoknows.events;

import com.joebruckner.whoknows.models.Post;

public class PostFetchedEvent extends Event {
	Post post;

	public PostFetchedEvent(Post post) {
		this.status = Event.SUCCESS;
		this.error = 0;
		this.post = post;
	}

	public PostFetchedEvent(int error) {
		this.status = Event.FAILED;
		this.error = error;
		this.post = null;
	}

	public Post getPost() {
		return post;
	}
}
