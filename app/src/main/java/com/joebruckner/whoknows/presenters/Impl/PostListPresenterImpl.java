package com.joebruckner.whoknows.presenters.Impl;

import android.util.Log;

import com.joebruckner.whoknows.models.Post;
import com.joebruckner.whoknows.network.AppApi;
import com.joebruckner.whoknows.presenters.PostListPresenter;
import com.joebruckner.whoknows.ui.Home.PostListFragment;
import com.joebruckner.whoknows.ui.Home.PostListView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

public class PostListPresenterImpl implements PostListPresenter {
	PostListView view;
	AppApi api;
	Bus bus;

	public PostListPresenterImpl(AppApi api, Bus bus) {
		this.api = api;
		this.bus = bus;
		bus.register(this);
	}

	@Override public void attachView(PostListView view) {
		this.view = view;
	}

	@Override public void detachView() {
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
		view.showContent();
	}

	@Subscribe public void postsLoaded(ArrayList<Post> posts) {
		Log.d("Presenter", "posts recieved");
		view.setData(posts);
	}
}
