package com.joebruckner.whoknows.managers;

import com.joebruckner.whoknows.models.Profile;

public interface AccountManager {
	void register(String name, String email, String password);
	void login(String email, String password);
	void logout();
	void getProfile();
	Profile getCachedProfile();
	boolean isLoggedIn();


}
