package com.joebruckner.whoknows.presenters;

import com.joebruckner.whoknows.utilities.AppApi;
import com.squareup.otto.Bus;

import java.util.List;

public class AttendeesListPresenter extends BasePresenter<List<String>> {
	AppApi api;
	Bus bus;

	public AttendeesListPresenter(AppApi api, Bus bus) {
		this.api = api;
		this.bus = bus;
		bus.register(this);
	}

	public void loadData(long id) {
		view.setData(api.getAttendees(id));
		view.showContent();
	}
}
