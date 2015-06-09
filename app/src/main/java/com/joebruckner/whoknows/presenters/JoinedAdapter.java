package com.joebruckner.whoknows.presenters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.models.Beacon;
import com.joebruckner.whoknows.ui.Beacon.BeaconViewHolder;
import com.joebruckner.whoknows.utilities.AppApi;
import com.squareup.otto.Bus;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class JoinedAdapter extends RecyclerView.Adapter<BeaconViewHolder> {
	List<Beacon> beacons;
	AppApi api;
	Bus bus;

	public JoinedAdapter(AppApi api, Bus bus) {
		this.beacons = api.getActiveBeacons();
		this.api = api;
		this.bus = bus;
		bus.register(this);
	}

	public void updateItems() {
		beacons = api.getActiveBeacons();
		notifyDataSetChanged();
	}

	public Beacon getItem(int position) {
		return beacons.get(position);
	}

	@Override public BeaconViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater
				.from(parent.getContext()).inflate(R.layout.item_beacon, parent, false);
		return new BeaconViewHolder(view);
	}

	@Override public void onBindViewHolder(BeaconViewHolder holder, int position) {
		Beacon beacon = beacons.get(position);
		holder.getTitleView().setText(beacon.getTitle());
		holder.getNameView().setText(beacon.getName());
		holder.getDescriptionView().setText(beacon.getDescription());
		SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
		holder.getDateView().setText(format.format(beacon.getDate()));
	}

	@Override public int getItemCount() {
		return beacons.size();
	}
}
