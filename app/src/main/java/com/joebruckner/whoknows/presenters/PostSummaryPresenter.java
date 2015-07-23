package com.joebruckner.whoknows.presenters;

import android.support.annotation.Nullable;
import android.util.Log;

import com.joebruckner.whoknows.common.SimplePresenter;
import com.joebruckner.whoknows.events.PostFetchedEvent;
import com.joebruckner.whoknows.managers.DatabaseManager;
import com.joebruckner.whoknows.models.Post;
import com.joebruckner.whoknows.ui.views.PostSummaryView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

public class PostSummaryPresenter extends SimplePresenter<Post, PostSummaryView> {
	DatabaseManager database;
	@Nullable Post post;

	public PostSummaryPresenter(Bus bus, DatabaseManager database) {
		super(bus);
		this.database = database;
	}

	@Override public void execute(int tag) {
		if (tag == ACTION_OFFER_HELP)
			offerHelp();
		else
			getPosts();
	}

	public void getPosts() {
		if (getView() == null) return;
		database.getPost(getView().getPostId());
		getView().showLoading();
	}

	public void offerHelp() {
		if (getView() == null) return;

		if (post != null) {
			database.putOffer(post.getId(), post.getUserId());
			getView().showSent();
		}
	}

	@Subscribe public void getPost(PostFetchedEvent e) {
		if (getView() == null) return;
		Log.d(TAG, "getPost: " + e);
		this.post = e.getPost();
		getView().setData(e.getPost());
	}
}
