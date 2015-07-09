package com.joebruckner.whoknows.presenters;

import com.joebruckner.whoknows.ui.StartUp.AuthView;

public interface LoginPresenter {
	void attachView(AuthView view);
	void detachView();
	void login(String email, String password);
}
