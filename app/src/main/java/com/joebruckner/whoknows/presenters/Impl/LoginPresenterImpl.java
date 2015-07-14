package com.joebruckner.whoknows.presenters.Impl;

import android.util.Log;

import com.joebruckner.whoknows.events.AuthorizedEvent;
import com.joebruckner.whoknows.events.Event;
import com.joebruckner.whoknows.managers.AccountManager;
import com.joebruckner.whoknows.presenters.LoginPresenter;
import com.joebruckner.whoknows.ui.StartUp.AuthView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

public class LoginPresenterImpl implements LoginPresenter {
	AuthView view;
	AccountManager accountManager;
	Bus bus;

	public LoginPresenterImpl(Bus bus, AccountManager accountManager) {
		this.bus = bus;
		this.accountManager = accountManager;
	}

	@Override public void attachView(AuthView view) {
		bus.register(this);
		this.view = view;
	}

	@Override public void detachView() {
		bus.unregister(this);
		view = null;
	}

	@Override public void login(String email, String password) {
		Log.d("Login", "logging in...");
		if (email.isEmpty() && password.isEmpty())
			accountManager.login("jmb@gmail.com", "jmb");
		else
			accountManager.login(email, password);
	}

	@Subscribe public void getLoggedInEvent(AuthorizedEvent e) {
		Log.d("Login", "Event recieved");
		if (e.getStatus() == Event.SUCCESS)
			view.showSuccess();
	}
}
