package com.joebruckner.whoknows.presenters.Impl;

import android.util.Log;

import com.joebruckner.whoknows.events.Event;
import com.joebruckner.whoknows.events.LoggedInEvent;
import com.joebruckner.whoknows.managers.AccountManager;
import com.joebruckner.whoknows.presenters.RegisterPresenter;
import com.joebruckner.whoknows.ui.StartUp.AuthView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

public class RegisterPresenterImpl implements RegisterPresenter {
	AuthView view;
	AccountManager accountManager;
	Bus bus;

	public RegisterPresenterImpl(Bus bus, AccountManager accountManager) {
		this.accountManager = accountManager;
		this.bus = bus;
	}

	@Override public void attachView(AuthView view) {
		bus.register(this);
		this.view = view;
	}

	@Override public void detachView() {
		bus.unregister(this);
		view = null;
	}

	@Override public void registerAndAuth(String name, String email, String password,
	                                      String confirmPassword) {
		Log.d("Register", "registering...");
		accountManager.register(name, email, password);
	}

	@Subscribe public void getLoggedInEvent(LoggedInEvent e) {
		Log.d("Login", "Event recieved");
		if (e.getStatus() == Event.SUCCESS)
			view.showSuccess();
	}
}
