package com.joebruckner.whoknows.presenters.Impl;

import com.joebruckner.whoknows.models.Post;
import com.joebruckner.whoknows.network.AppApi;
import com.joebruckner.whoknows.presenters.PostSummaryPresenter;
import com.joebruckner.whoknows.ui.Post.PostSummaryView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

public class PostSummaryPresenterImpl implements PostSummaryPresenter {
	PostSummaryView view;
	AppApi api;
	Bus bus;

	public PostSummaryPresenterImpl(AppApi api, Bus bus) {
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

	@Subscribe public void getPost(Post post) {
		if (view == null) return;
		view.setData(post);
		view.showContent();
	}
}
