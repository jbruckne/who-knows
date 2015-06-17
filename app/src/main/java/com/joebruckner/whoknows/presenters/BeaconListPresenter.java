package com.joebruckner.whoknows.presenters;

import com.joebruckner.whoknows.models.Beacon;
import com.joebruckner.whoknows.ui.Home.BeaconListFragment;
import com.joebruckner.whoknows.utilities.AppApi;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;

public class BeaconListPresenter extends BasePresenter<List<Beacon>> {
	AppApi api;
	Bus bus;

	public BeaconListPresenter(AppApi api, Bus bus) {
		this.api = api;
		this.bus = bus;
		bus.register(this);
	}

	public void loadData(int filter) {
		List<Beacon> beacons;
		switch (filter) {
			case BeaconListFragment.NEARBY:
				beacons = api.getNearbyBeacons();
				break;
			case BeaconListFragment.JOINED:
				beacons = api.getJoinedBeacons();
				break;
			case BeaconListFragment.POSTED:
				beacons = api.getPostedBeacons();
				break;
			default:
				beacons = new ArrayList<>();
		}
		view.setData(beacons);
		view.showContent();
	}

}
