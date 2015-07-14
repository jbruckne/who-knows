package com.joebruckner.whoknows.presenters;

import com.joebruckner.whoknows.ui.Home.ProfileView;

public interface ProfilePresenter {
	void attachView(ProfileView view);
	void detachView();
	void loadProfile();
}
