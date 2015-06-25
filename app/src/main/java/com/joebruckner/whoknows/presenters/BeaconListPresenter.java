package com.joebruckner.whoknows.presenters;

import android.os.Bundle;

import com.joebruckner.whoknows.models.Post;
import com.joebruckner.whoknows.ui.Home.BeaconListFragment;
import com.joebruckner.whoknows.utilities.AppApi;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;

public class BeaconListPresenter extends BasePresenter<List<Post>> {
	AppApi api;
	Bus bus;

	public BeaconListPresenter(AppApi api, Bus bus) {
		this.api = api;
		this.bus = bus;
		bus.register(this);
	}

	public void loadData(Bundle args) {
		int filter = args.getInt(TYPE);
		List<Post> posts;
		switch (filter) {
			case BeaconListFragment.NEARBY:
				posts = api.getNearbyPosts();
				break;
			case BeaconListFragment.JOINED:
				posts = api.getJoinedPosts();
				break;
			case BeaconListFragment.POSTED:
				posts = api.getPostedPosts();
				break;
			default:
				posts = new ArrayList<>();
		}
		view.setData(posts);
		view.showContent();
	}

}
