package com.joebruckner.whoknows.presenters;

import com.joebruckner.whoknows.ui.StartUp.AuthView;

public interface RegisterPresenter {
	void attachView(AuthView view);
	void detachView();
	void registerAndAuth(String email, String password, String confirm_pass);
}
