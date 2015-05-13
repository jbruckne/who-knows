package com.joebruckner.whoknows.presenters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.models.logic.Beacon;
import com.joebruckner.whoknows.models.view.BeaconViewHolder;
import com.joebruckner.whoknows.utilities.AppApi;
import com.squareup.otto.Bus;

import java.util.List;

public class NearbyAdapter extends RecyclerView.Adapter<BeaconViewHolder> {
	List<Beacon> beacons;
	AppApi api;
	Bus bus;

	public NearbyAdapter(AppApi api, Bus bus) {
		this.beacons = api.getAllBeacons();
		this.api = api;
		this.bus = bus;
		bus.register(this);
	}

	public void updateItems() {
		beacons = api.getAllBeacons();
		notifyDataSetChanged();
	}

	public Beacon getItem(int position) {
		return beacons.get(position);
	}

	@Override public BeaconViewHolder onCreateViewHolder(ViewGroup parent, int type) {
		View view = LayoutInflater
				.from(parent.getContext()).inflate(R.layout.item_beacon, parent, false);
		return new BeaconViewHolder(view);
	}

	@Override public void onBindViewHolder(BeaconViewHolder holder, int position) {
		Beacon beacon = beacons.get(position);
		holder.getTitleView().setText(beacon.getTitle());
		holder.getNameView().setText(beacon.getName());
		holder.getDescriptionView().setText(beacon.getDescription());
		holder.getDateView().setText(beacon.getDate());
	}

	@Override public int getItemCount() {
		return beacons.size();
	}
}
