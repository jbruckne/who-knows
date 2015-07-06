package com.joebruckner.whoknows.presenters.Impl;

import com.joebruckner.whoknows.network.AppApi;
import com.joebruckner.whoknows.presenters.PostAttendeesPresenter;
import com.joebruckner.whoknows.ui.Post.PostAttendeesView;
import com.squareup.otto.Bus;

public class PostAttendeesPresenterImpl implements PostAttendeesPresenter {
	PostAttendeesView view;
	AppApi api;
	Bus bus;

	public PostAttendeesPresenterImpl(AppApi api, Bus bus) {
		this.api = api;
		this.bus = bus;
		bus.register(this);
	}

	@Override public void attachView(PostAttendeesView view) {
		this.view = view;
	}

	@Override public void detachView() {
		view = null;
	}

	@Override public void fetchAttendees(String id) {
		view.showContent();
	}
}
