package com.joebruckner.whoknows.presenters;

import android.os.Bundle;

import com.joebruckner.whoknows.models.Beacon;
import com.joebruckner.whoknows.utilities.AppApi;
import com.squareup.otto.Bus;

public class BeaconOverviewPresenter extends BasePresenter<Beacon> {
	AppApi api;
	Bus bus;

	public BeaconOverviewPresenter(AppApi api, Bus bus) {
		this.api = api;
		this.bus = bus;
		bus.register(this);
	}

	public void loadData(Bundle args) {
		long id = args.getLong(ID, 0);
		Beacon beacon = api.get(id);
		view.setData(beacon);
		view.showContent();
	}
}
