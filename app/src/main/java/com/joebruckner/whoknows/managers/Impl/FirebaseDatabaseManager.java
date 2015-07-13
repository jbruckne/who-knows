package com.joebruckner.whoknows.managers.Impl;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.GenericTypeIndicator;
import com.firebase.client.ValueEventListener;
import com.joebruckner.whoknows.events.Event;
import com.joebruckner.whoknows.events.PostFetchedEvent;
import com.joebruckner.whoknows.events.PostsFetchedEvent;
import com.joebruckner.whoknows.models.Post;
import com.joebruckner.whoknows.managers.AccountManager;
import com.joebruckner.whoknows.managers.DatabaseManager;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class FirebaseDatabaseManager implements DatabaseManager {
	Firebase baseRef;
	Context context;
	AccountManager api;
	Bus bus;

	private boolean isDoingRequest = false;

	public FirebaseDatabaseManager(Context context, AccountManager api, Bus bus) {
		this.baseRef = new Firebase("https://sizzling-torch-124.firebaseio.com");
		this.context = context;
		this.api = api;
		this.bus = bus;
	}

	@Override public void getNearbyPosts() {
		if (isDoingRequest) return;
		isDoingRequest = true;
		final Firebase postsRef = baseRef.child("posts");
		postsRef.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override public void onDataChange(DataSnapshot dataSnapshot) {
				GenericTypeIndicator<Map<String, Post>> type = new
						GenericTypeIndicator<Map<String, Post>>() {};
				Map<String, Post> data = dataSnapshot.getValue(type);
				ArrayList<Post> posts = new ArrayList<>(data.values());
				Log.d("Value Event", "Posting new Value Event. " + UUID.randomUUID().toString());
				bus.post(new PostsFetchedEvent(Event.SUCCESS, posts));
				postsRef.removeEventListener(this);
				isDoingRequest = false;
			}

			@Override public void onCancelled(FirebaseError firebaseError) {
				Log.e("Firebase", firebaseError.toString());
				postsRef.removeEventListener(this);
				isDoingRequest = false;
			}
		});
	}

	@Override public void getJoinedPosts() {

	}

	@Override public void getPostedPosts() {

	}

	@Override public void put(String title, String description, String contactInfo) {
		Firebase newPostRef = baseRef.child("posts").push();
		Post post = new Post.Builder()
				.id(newPostRef.getKey())
				.title(title)
				.name(api.getUser().getName())
				.date(new Date().getTime())
				.contactInfo(contactInfo)
				.description(description)
				.build();
		newPostRef.setValue(post);
		Toast.makeText(context, newPostRef.getKey(), Toast.LENGTH_SHORT).show();
	}

	@Override public void get(String id) {
		Firebase postRef = baseRef.child("posts").child(id);
		postRef.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override public void onDataChange(DataSnapshot dataSnapshot) {
				Log.d("Firebase", dataSnapshot.toString());
				GenericTypeIndicator<Post> type = new GenericTypeIndicator<Post>() {};
				Post post = dataSnapshot.getValue(type);
				bus.post(new PostFetchedEvent(post));
			}

			@Override public void onCancelled(FirebaseError firebaseError) {
				Log.e("Firebase", firebaseError.toString());
				bus.post(new PostFetchedEvent(0));
			}
		});

	}

	@Override public void getAttendees(String id) {

	}
}
