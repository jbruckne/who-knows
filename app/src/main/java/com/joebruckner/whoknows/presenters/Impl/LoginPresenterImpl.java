package com.joebruckner.whoknows.presenters.Impl;

import com.joebruckner.whoknows.presenters.LoginPresenter;
import com.joebruckner.whoknows.ui.StartUp.AuthView;
import com.squareup.otto.Bus;

public class LoginPresenterImpl implements LoginPresenter {
	AuthView view;
	Bus bus;

	public LoginPresenterImpl(Bus bus) {
		this.bus = bus;
		bus.register(this);
	}

	@Override public void attachView(AuthView view) {
		this.view = view;
	}

	@Override public void detachView() {
		view = null;
	}

	@Override public void login(String email, String password) {
		// TODO
	}
}
