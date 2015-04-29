package com.joebruckner.whoknows.presenters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.models.logic.Beacon;
import com.joebruckner.whoknows.models.view.BeaconViewHolder;
import com.joebruckner.whoknows.ui.BeaconListView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.List;

public class BeaconListAdapter extends RecyclerView.Adapter<BeaconViewHolder> {
	List<Beacon> beacons;
	BeaconListView view;
	Bus bus;

	public BeaconListAdapter(List<Beacon> beacons, Bus bus, Activity activity) {
		this.beacons = beacons;
		this.bus = bus;
		bus.register(this);
	}

	public void setView(BeaconListView view) {
		this.view = view;
	}

	@Subscribe public void getActiveBeacon(Beacon beacon) {
		view.setHeader(beacon.getTitle());
	}

	@Override public BeaconViewHolder onCreateViewHolder(ViewGroup parent, int type) {
		View view = LayoutInflater
				.from(parent.getContext()).inflate(R.layout.item_beacon, parent, false);
		return new BeaconViewHolder(view);
	}

	@Override public void onBindViewHolder(BeaconViewHolder beaconViewHolder, int position) {
		Beacon beacon = beacons.get(position);
		beaconViewHolder.getTitleView().setText(beacon.getTitle());
		beaconViewHolder.getNameView().setText(beacon.getName());
		beaconViewHolder.getDateView().setText(beacon.getDate());
	}

	@Override public int getItemCount() {
		return beacons.size();
	}
}
