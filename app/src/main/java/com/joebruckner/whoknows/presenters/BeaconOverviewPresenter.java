package com.joebruckner.whoknows.presenters;

import android.os.Bundle;

import com.joebruckner.whoknows.models.Post;
import com.joebruckner.whoknows.utilities.AppApi;
import com.squareup.otto.Bus;

public class BeaconOverviewPresenter extends BasePresenter<Post> {
	AppApi api;
	Bus bus;

	public BeaconOverviewPresenter(AppApi api, Bus bus) {
		this.api = api;
		this.bus = bus;
		bus.register(this);
	}

	public void loadData(Bundle args) {
		String id = args.getString(ID, null);
		Post post = api.get(id);
		view.setData(post);
		view.showContent();
	}
}
