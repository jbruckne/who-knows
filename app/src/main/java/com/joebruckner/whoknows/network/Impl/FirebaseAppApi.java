package com.joebruckner.whoknows.network.Impl;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.GenericTypeIndicator;
import com.firebase.client.ValueEventListener;
import com.joebruckner.whoknows.models.Post;
import com.joebruckner.whoknows.network.AccountApi;
import com.joebruckner.whoknows.network.AppApi;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class FirebaseAppApi implements AppApi {
	Firebase baseRef;
	Context context;
	AccountApi api;
	Bus bus;

	private static final String TITLE = "title";
	private static final String DESCRIPTION = "description";
	private static final String CONTACT_INFO = "contact_info";
	private static final String NAME = "name";
	private static final String DATE = "date";

	public FirebaseAppApi(Context context, AccountApi api, Bus bus) {
		this.baseRef = new Firebase("https://sizzling-torch-124.firebaseio.com");
		this.context = context;
		this.api = api;
		this.bus = bus;
	}

	@Override public void getNearbyPosts() {
		Firebase postsRef = baseRef.child("posts");
		postsRef.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override public void onDataChange(DataSnapshot dataSnapshot) {
				GenericTypeIndicator<Map<String, Post>> type = new
						GenericTypeIndicator<Map<String, Post>>() {};
				Map<String, Post> data = dataSnapshot.getValue(type);
				ArrayList<Post> posts = new ArrayList<>(data.values());
				bus.post(posts);
			}

			@Override public void onCancelled(FirebaseError firebaseError) {
				Log.e("Firebase", firebaseError.toString());
			}
		});
	}

	@Override public void getJoinedPosts() {

	}

	@Override public void getPostedPosts() {

	}

	@Override public void put(String title, String description, String contactInfo) {
		Firebase newPostRef = baseRef.child("posts").push();
		Post post = new Post(newPostRef.getKey(), title, api.getUser().getName(), new Date().getTime
				(), contactInfo, description, 0, 0);
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
				bus.post(post);
			}

			@Override public void onCancelled(FirebaseError firebaseError) {
				Log.e("Firebase", firebaseError.toString());
			}
		});

	}

	@Override public void getAttendees(String id) {

	}
}
