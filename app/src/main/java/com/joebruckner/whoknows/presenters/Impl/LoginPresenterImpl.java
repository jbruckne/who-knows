package com.joebruckner.whoknows.presenters.Impl;

import android.util.Log;

import com.joebruckner.whoknows.events.Event;
import com.joebruckner.whoknows.events.LoggedInEvent;
import com.joebruckner.whoknows.network.AccountApi;
import com.joebruckner.whoknows.presenters.LoginPresenter;
import com.joebruckner.whoknows.ui.StartUp.AuthView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

public class LoginPresenterImpl implements LoginPresenter {
	AuthView view;
	AccountApi api;
	Bus bus;

	public LoginPresenterImpl(Bus bus, AccountApi api) {
		this.bus = bus;
		this.api = api;
		bus.register(this);
	}

	@Override public void attachView(AuthView view) {
		this.view = view;
	}

	@Override public void detachView() {
		view = null;
	}

	@Override public void login(String email, String password) {
		Log.d("Login", "logging in...");
		api.login(email, password);
	}

	@Subscribe public void getLoggedInEvent(LoggedInEvent e) {
		Log.d("Login", "Event recieved");
		if (e.getStatus() == Event.SUCCESS)
			view.advanceToHome();
	}
}
