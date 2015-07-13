package com.joebruckner.whoknows.managers.Impl;

import android.util.Log;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.GenericTypeIndicator;
import com.firebase.client.ValueEventListener;
import com.joebruckner.whoknows.events.Event;
import com.joebruckner.whoknows.events.LoggedInEvent;
import com.joebruckner.whoknows.events.RegisteredEvent;
import com.joebruckner.whoknows.managers.AccountManager;
import com.joebruckner.whoknows.models.Profile;
import com.squareup.otto.Bus;

import java.util.Map;

public class FirebaseAccountManager implements AccountManager {
	private Firebase ref;
	private Bus bus;

	private static final String TAG = "FirebaseAccountApi";
	private boolean authStatus = false;

	public FirebaseAccountManager(Firebase ref, Bus bus) {
		this.ref = ref;
		this.bus = bus;
	}

	@Override public void register(final String name, final String email, final String password) {
		Log.d(TAG, "registering...");
		ref.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
			@Override public void onSuccess(Map<String, Object> result) {
				String id = result.get("uid").toString();
				Log.d(TAG, "registration complete: " + id);
				Profile profile = new Profile(id, name);
				ref.child("users").child(id).setValue(profile);
				login(email, password);
			}

			@Override public void onError(FirebaseError firebaseError) {
				Log.e(TAG, firebaseError.toString());
				bus.post(new RegisteredEvent(Event.FAILED, 0));
			}
		});
	}

	@Override public void login(String email, String password) {
		ref.authWithPassword(email, password, new Firebase.AuthResultHandler() {
			@Override public void onAuthenticated(AuthData authData) {
				authStatus = true;
				Log.d(TAG, "logged in" + authData.getUid());
				Firebase userRef = ref.child("users").child(authData.getUid());
				userRef.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override public void onDataChange(DataSnapshot dataSnapshot) {
						Log.d("Value", dataSnapshot.toString());
						GenericTypeIndicator<Profile> type = new GenericTypeIndicator<Profile>() {};
						Profile profile = dataSnapshot.getValue(type);
						Log.d("Value", "Converted");
						bus.post(new LoggedInEvent(Event.SUCCESS, profile));
					}

					@Override public void onCancelled(FirebaseError firebaseError) {
						bus.post(new LoggedInEvent(Event.FAILED, 1));
					}
				});
				bus.post(new LoggedInEvent(Event.SUCCESS, new Profile(authData.getUid(), "Joe")));
			}

			@Override public void onAuthenticationError(FirebaseError firebaseError) {
				Log.e(TAG, "Error:" + firebaseError.getCode());
				bus.post(new LoggedInEvent(Event.FAILED, 0));
			}
		});
	}

	@Override public void logout() {
		Log.d(TAG, "logged out");
		ref.unauth();
		authStatus = false;
	}

	@Override public Profile getUser() {
		Log.d(TAG, "getting user");
		return new Profile("0", "Joe");
	}

	@Override public boolean isLoggedIn() {
		return authStatus;
	}

}
