package com.joebruckner.whoknows.presenters.Impl;

import android.util.Log;

import com.joebruckner.whoknows.events.PostFetchedEvent;
import com.joebruckner.whoknows.managers.DatabaseManager;
import com.joebruckner.whoknows.presenters.PostSummaryPresenter;
import com.joebruckner.whoknows.ui.Post.PostSummaryView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

public class PostSummaryPresenterImpl implements PostSummaryPresenter {
	PostSummaryView view;
	DatabaseManager databaseManager;
	Bus bus;

	public PostSummaryPresenterImpl(DatabaseManager databaseManager, Bus bus) {
		this.databaseManager = databaseManager;
		this.bus = bus;
		bus.register(this);
	}

	@Override public void attachView(PostSummaryView view) {
		this.view = view;
	}

	@Override public void detachView() {
		view = null;
	}

	@Override public void getPosts(String id) {
		databaseManager.getPost(id);
		view.showLoading();
	}

	@Override public void offerHelp(String id, String recipient) {
		Log.d("Presenter", "offering help");
		databaseManager.putOffer(id, recipient);
	}

	@Subscribe public void getPost(PostFetchedEvent e) {
		if (view == null) return;
		Log.d("PostFetchedEvent", "New event received. " + e.getId());
		view.setData(e.getPost());
	}
}
