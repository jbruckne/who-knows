package com.joebruckner.whoknows.presenters.Impl;

import android.util.Log;

import com.joebruckner.whoknows.events.Event;
import com.joebruckner.whoknows.events.LoggedInEvent;
import com.joebruckner.whoknows.events.RegisteredEvent;
import com.joebruckner.whoknows.network.AccountApi;
import com.joebruckner.whoknows.presenters.RegisterPresenter;
import com.joebruckner.whoknows.ui.StartUp.AuthView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

public class RegisterPresenterImpl implements RegisterPresenter {
	AuthView view;
	AccountApi api;
	Bus bus;

	private String email;
	private String password;

	public RegisterPresenterImpl(Bus bus, AccountApi api) {
		this.api = api;
		this.bus = bus;
		bus.register(this);
	}

	@Override public void attachView(AuthView view) {
		this.view = view;
	}

	@Override public void detachView() {
		view = null;
	}

	@Override public void registerAndAuth(String email, String password, String confirmPassword) {
		Log.d("Register", "registering...");
		this.email = email;
		this.password = password;
		api.register(email, password);
	}

	@Subscribe public void getRegisteredEvent(RegisteredEvent e) {
		Log.d("Register", "Event received");
		if (e.getStatus() == Event.SUCCESS) {
			view.showSuccess();
			api.login(email, password);
		}
	}

	@Subscribe public void getLoggedInEvent(LoggedInEvent e) {
		Log.d("Login", "Event recieved");
		if (e.getStatus() == Event.SUCCESS)
			view.advanceToHome();
	}
}
