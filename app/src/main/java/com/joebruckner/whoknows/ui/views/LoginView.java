package com.joebruckner.whoknows.ui.views;

import com.joebruckner.whoknows.models.Profile;
import com.joebruckner.whoknows.common.SimpleView;

public interface LoginView extends SimpleView<Profile> {
	String getEmail();
	String getPassword();
	void showSuccess();
	void showInvalid(String error);
}
