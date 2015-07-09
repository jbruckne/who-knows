package com.joebruckner.whoknows.network;

import com.joebruckner.whoknows.models.Profile;

public interface AccountApi {
	void register(String email, String password);
	void login(String email, String password);
	void logout();
	Profile getUser();
	boolean isLoggedIn();


}
