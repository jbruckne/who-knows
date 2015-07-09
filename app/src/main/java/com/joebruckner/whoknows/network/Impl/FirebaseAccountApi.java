package com.joebruckner.whoknows.network.Impl;

import android.util.Log;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.joebruckner.whoknows.events.Event;
import com.joebruckner.whoknows.events.LoggedInEvent;
import com.joebruckner.whoknows.events.RegisteredEvent;
import com.joebruckner.whoknows.models.Profile;
import com.joebruckner.whoknows.network.AccountApi;
import com.squareup.otto.Bus;

public class FirebaseAccountApi implements AccountApi {
	private Firebase ref;
	private Bus bus;

	private static final String TAG = "FirebaseAccountApi";
	private boolean authStatus = false;

	public FirebaseAccountApi(Firebase ref, Bus bus) {
		this.ref = ref;
		this.bus = bus;
	}

	@Override public void register(final String email, final String password) {
		Log.d(TAG, "registering...");
		ref.createUser(email, password, new Firebase.ResultHandler() {
			@Override public void onSuccess() {
				Log.d(TAG, "registration complete");
				bus.post(new RegisteredEvent(Event.SUCCESS));
			}

			@Override public void onError(FirebaseError firebaseError) {
				Log.e(TAG, firebaseError.toString());
				bus.post(new RegisteredEvent(Event.FAILED, 0));
			}
		});
	}

	@Override public void login(String email, String password) {
		Log.d(TAG, "logged in");
		ref.authWithPassword(email, password, new Firebase.AuthResultHandler() {
			@Override public void onAuthenticated(AuthData authData) {
				authStatus = true;
				Log.d(TAG, authData.getUid());
				bus.post(new LoggedInEvent(Event.SUCCESS, new Profile(authData.getUid())));
			}

			@Override public void onAuthenticationError(FirebaseError firebaseError) {
				Log.e(TAG, firebaseError.toString());
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
		return new Profile("Joe");
	}

	@Override public boolean isLoggedIn() {
		return authStatus;
	}

}
