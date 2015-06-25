package com.joebruckner.whoknows.utilities;

import android.content.Context;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.joebruckner.whoknows.models.Post;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FirebaseApi implements AppApi {
	Firebase baseRef;
	Context context;
	AccountApi api;

	private static final String TITLE = "title";
	private static final String DESCRIPTION = "description";
	private static final String CONTACT_INFO = "contact_info";
	private static final String NAME = "name";
	private static final String DATE = "date";

	public FirebaseApi(Context context, AccountApi api) {
		Firebase.setAndroidContext(context);
		this.baseRef = new Firebase("https://sizzling-torch-124.firebaseio.com");
		this.context = context;
		this.api = api;
	}

	@Override public List<Post> getNearbyPosts() {
		return new ArrayList<>();
	}

	@Override public List<Post> getJoinedPosts() {
		return new ArrayList<>();
	}

	@Override public List<Post> getPostedPosts() {
		return new ArrayList<>();
	}

	@Override public void put(String title, String description, String contactInfo) {
		Firebase newPostRef = baseRef.child("posts").push();
		Post post = new Post(newPostRef.getKey(), title, api.getName(), new Date().getTime
				(), contactInfo, description, 0, 0);
		newPostRef.setValue(post);
		Toast.makeText(context, newPostRef.getKey(), Toast.LENGTH_SHORT).show();
	}

	@Override public Post get(String id) {
		return new Post();
	}

	@Override public List<String> getAttendees(String id) {
		return new ArrayList<>();
	}
}
