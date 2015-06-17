package com.joebruckner.whoknows.presenters;

import com.joebruckner.whoknows.models.Beacon;
import com.joebruckner.whoknows.utilities.AppApi;
import com.squareup.otto.Bus;

public class BeaconDetailPresenter extends BasePresenter<Beacon> {
	AppApi api;
	Bus bus;

	public BeaconDetailPresenter(AppApi api, Bus bus) {
		this.api = api;
		this.bus = bus;
		bus.register(this);
	}

	public void loadData(long id) {
		Beacon beacon = api.get(id);
		view.setData(beacon);
		view.showContent();
	}
}
