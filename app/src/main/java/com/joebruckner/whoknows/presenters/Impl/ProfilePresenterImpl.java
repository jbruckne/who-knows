package com.joebruckner.whoknows.presenters.Impl;

import android.util.Log;

import com.joebruckner.whoknows.events.Event;
import com.joebruckner.whoknows.events.ProfileFetchedEvent;
import com.joebruckner.whoknows.managers.AccountManager;
import com.joebruckner.whoknows.presenters.ProfilePresenter;
import com.joebruckner.whoknows.ui.Home.ProfileView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

public class ProfilePresenterImpl implements ProfilePresenter {
	ProfileView view;
	AccountManager manager;
	Bus bus;

	public ProfilePresenterImpl(AccountManager manager, Bus bus) {
		this.manager = manager;
		this.bus = bus;
	}

	@Override public void attachView(ProfileView view) {
		bus.register(this);
		this.view = view;
	}

	@Override public void detachView() {
		bus.unregister(this);
		view = null;
	}

	@Override public void loadProfile() {
		manager.getProfile();
		view.showLoading();
	}

	@Subscribe public void getProfile(ProfileFetchedEvent e) {
		Log.d("Profile", "Event Recieved");
		if (e.getStatus() == Event.SUCCESS)
			view.setData(e.getProfile());
		else
			view.showError();
	}
}
