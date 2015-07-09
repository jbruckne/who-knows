package com.joebruckner.whoknows.network.Test;

import android.util.Log;

import com.joebruckner.whoknows.models.Profile;
import com.joebruckner.whoknows.network.AccountApi;

public class TestAccountApi implements AccountApi {

	private static final String TAG = "TestAccountApi";
	private boolean authStatus = false;

	@Override public void register(String email, String password) {
		Log.d(TAG, "registered");
	}

	@Override public void login(String email, String password) {
		Log.d(TAG, "logged in");
		authStatus = true;
	}

	@Override public void logout() {
		Log.d(TAG, "logged out");
		authStatus = false;
	}

	@Override public Profile getUser() {
		return new Profile("Joe");
	}

	@Override public boolean isLoggedIn() {
		return authStatus;
	}
}
