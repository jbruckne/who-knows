package com.joebruckner.whoknows.ui.Home;

import com.joebruckner.whoknows.models.Profile;

public interface ProfileView {
	void showLoading();
	void showContent();
	void showError();
	void setData(Profile profile);
}
