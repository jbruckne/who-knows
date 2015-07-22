package com.joebruckner.whoknows.presenters.Impl;

import android.support.annotation.NonNull;
import android.util.Log;

import com.joebruckner.whoknows.events.PostsFetchedEvent;
import com.joebruckner.whoknows.managers.DatabaseManager;
import com.joebruckner.whoknows.models.Post;
import com.joebruckner.whoknows.presenters.SimplePresenter;
import com.joebruckner.whoknows.ui.Home.PostListFragment;
import com.joebruckner.whoknows.ui.views.PostListView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.List;

public class PostListPresenter extends SimplePresenter<List<Post>, PostListView> {
	DatabaseManager database;

	public PostListPresenter(@NonNull Bus bus, @NonNull DatabaseManager database) {
		super(bus);
		this.database = database;
	}

	@Override public void execute(int tag) {
		if (getView() == null) return;
		switch (getView().getFilter()) {
			case PostListFragment.NEARBY:
				database.getPosts();
				break;
			case PostListFragment.JOINED:
				database.getOffers();
				break;
			case PostListFragment.POSTED:
				database.getPostedPosts();
				break;
		}
	}

	@Subscribe public void postsLoaded(PostsFetchedEvent e) {
		Log.d(TAG, "postsLoaded");
		if (getView() != null) getView().setData(e.getPosts());
	}
}
