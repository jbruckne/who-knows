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
import com.joebruckner.whoknows.managers.AccountManager;
import com.joebruckner.whoknows.managers.DatabaseManager;
import com.joebruckner.whoknows.models.Offer;
import com.joebruckner.whoknows.models.Post;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class FirebaseDatabaseManager implements DatabaseManager {
	Firebase baseRef;
	Context context;
	AccountManager accountManager;
	Bus bus;

	private boolean isDoingRequest = false;

	public FirebaseDatabaseManager(Context context, AccountManager accountManager, Bus bus) {
		this.baseRef = new Firebase("https://sizzling-torch-124.firebaseio.com");
		this.context = context;
		this.accountManager = accountManager;
		this.bus = bus;
	}

	@Override public void getPosts() {
		if (isDoingRequest) return;
		isDoingRequest = true;
		final Firebase postsRef = baseRef.child("posts");
		postsRef.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override public void onDataChange(DataSnapshot dataSnapshot) {
				if (dataSnapshot.getValue() == null) {
					Log.d("Firebase", "No Posts");
					bus.post(new PostsFetchedEvent(Event.FAILED, Event.DOES_NOT_EXIST));
				} else {
					GenericTypeIndicator<Map<String, Post>> type = new
							GenericTypeIndicator<Map<String, Post>>() {
							};
					Map<String, Post> data = dataSnapshot.getValue(type);
					ArrayList<Post> posts = new ArrayList<>(data.values());
					Log.d("Value Event", "Posting new Value Event. " + UUID.randomUUID().toString());
					bus.post(new PostsFetchedEvent(Event.SUCCESS, posts));
				}
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

	@Override public void getOffers() {
		Firebase offersRef = baseRef.child("offers");
		offersRef.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override public void onDataChange(DataSnapshot dataSnapshot) {
				
			}

			@Override public void onCancelled(FirebaseError firebaseError) {
				Log.e("Firebase", firebaseError.toString());
			}
		});
	}

	@Override public void getPostedPosts() {

	}

	@Override public void putPost(String title, String description, String contactInfo) {
		Firebase newPostRef = baseRef.child("posts").push();
		Post post = new Post.Builder()
				.id(newPostRef.getKey())
				.userId(accountManager.getCachedProfile().getId())
				.title(title)
				.name(accountManager.getCachedProfile().getName())
				.date(new Date().getTime())
				.contactInfo(contactInfo)
				.description(description)
				.build();
		newPostRef.setValue(post);
		Toast.makeText(context, newPostRef.getKey(), Toast.LENGTH_SHORT).show();
	}

	@Override public void getPost(String id) {
		Firebase postRef = baseRef.child("posts").child(id);
		postRef.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override public void onDataChange(DataSnapshot dataSnapshot) {
				if (dataSnapshot.getValue() == null) {
					Log.d("Firebase", "Post doesn't exist");
					bus.post(new PostFetchedEvent(Event.DOES_NOT_EXIST));
				} else {
					Log.d("Firebase", dataSnapshot.toString());
					GenericTypeIndicator<Post> type = new GenericTypeIndicator<Post>() {};
					Post post = dataSnapshot.getValue(type);
					bus.post(new PostFetchedEvent(post));
				}
			}

			@Override public void onCancelled(FirebaseError firebaseError) {
				Log.e("Firebase", firebaseError.toString());
				bus.post(new PostFetchedEvent(Event.REQUEST_ERROR));
			}
		});

	}

	@Override public void putOffer(String post, String recipient) {
		Firebase newOfferRef = baseRef.child("offers").push();
		Offer offer = new Offer.Builder()
				.id(newOfferRef.getKey())
				.postId(post)
				.recipientId(recipient)
				.userId(accountManager.getCachedProfile().getId())
				.userName(accountManager.getCachedProfile().getName())
				.build();
		newOfferRef.setValue(offer);
		Log.d("New Offer", newOfferRef.getKey());
	}

	@Override public void getOffer(String id) {
		Firebase offerRef = baseRef.child("offers").child(id);
		offerRef.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override public void onDataChange(DataSnapshot dataSnapshot) {
				if (dataSnapshot.getValue() == null)
					Log.d("Get Offer", "No Offers");
				else
					Log.d("Get Offer", dataSnapshot.toString());
			}

			@Override public void onCancelled(FirebaseError firebaseError) {
				Log.e("Get Offer", firebaseError.toString());
			}
		});
	}
}
