package com.joebruckner.whoknows.presenters.Impl;

import android.util.Log;

import com.joebruckner.whoknows.events.PostsFetchedEvent;
import com.joebruckner.whoknows.managers.DatabaseManager;
import com.joebruckner.whoknows.presenters.PostListPresenter;
import com.joebruckner.whoknows.ui.Home.PostListFragment;
import com.joebruckner.whoknows.ui.Home.PostListView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

public class PostListPresenterImpl implements PostListPresenter {
	PostListView view;
	DatabaseManager api;
	Bus bus;

	public PostListPresenterImpl(DatabaseManager api, Bus bus) {
		this.api = api;
		this.bus = bus;
	}

	@Override public void attachView(PostListView view) {
		bus.register(this);
		this.view = view;
	}

	@Override public void detachView() {
		bus.unregister(this);
		view = null;
	}

	@Override public void fetchPosts(int filter) {
		switch (filter) {
			case PostListFragment.NEARBY:
				api.getNearbyPosts();
				break;
			case PostListFragment.JOINED:
				api.getJoinedPosts();
				break;
			case PostListFragment.POSTED:
				api.getPostedPosts();
				break;
		}
	}

	@Subscribe public void postsLoaded(PostsFetchedEvent e) {
		Log.d("Presenter", "PostsFetchedEvent recieved.");
		view.setData(e.getPosts());
	}
}
