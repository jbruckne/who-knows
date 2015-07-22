package com.joebruckner.whoknows.presenters.Impl;

import android.util.Log;

import com.joebruckner.whoknows.managers.AccountManager;
import com.joebruckner.whoknows.managers.DatabaseManager;
import com.joebruckner.whoknows.presenters.NewPostPresenter;
import com.joebruckner.whoknows.ui.SimpleView;
import com.squareup.otto.Bus;

public class NewPostPresenterImpl implements NewPostPresenter {
	SimpleView view;
	DatabaseManager databaseManager;
	AccountManager accountManager;
	Bus bus;

	public NewPostPresenterImpl(DatabaseManager databaseManager,
	                            AccountManager accountManager, Bus bus) {
		this.databaseManager = databaseManager;
		this.accountManager = accountManager;
		this.bus = bus;
	}

	@Override public void attachView(SimpleView view) {
		this.view = view;
		bus.register(this);
	}

	@Override public void detachView() {
		this.view = null;
		bus.unregister(this);
	}

	@Override public void loadProfile() {

	}

	@Override public void sendPost(String title, String descripton, boolean location) {
		Log.d("New Post", "Title: " + title + ", Description: " + descripton);
		databaseManager.putPost(title, descripton, DatabaseManager.PHONE);
	}
}
