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
	DatabaseManager api;
	Bus bus;

	public PostSummaryPresenterImpl(DatabaseManager api, Bus bus) {
		this.api = api;
		this.bus = bus;
		bus.register(this);
	}

	@Override public void attachView(PostSummaryView view) {
		this.view = view;
	}

	@Override public void detachView() {
		view = null;
	}

	@Override public void fetchPost(String id) {
		api.get(id);
		view.showContent();
	}

	@Subscribe public void getPost(PostFetchedEvent e) {
		if (view == null) return;
		Log.d("PostFetchedEvent", "New event received. " + e.getId());
		view.setData(e.getPost());
		view.showContent();
	}
}
