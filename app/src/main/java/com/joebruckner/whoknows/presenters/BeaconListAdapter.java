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

import java.util.List;

public class BeaconListAdapter extends RecyclerView.Adapter<BeaconViewHolder> {
	List<Beacon> beacons;
	AppApi api;
	Bus bus;
	int filter = 0;

	public BeaconListAdapter(AppApi api, Bus bus) {
		this.api = api;
		this.bus = bus;
		setItems();
		bus.register(this);
	}

	public void setFilter(int filter) {
		this.filter = filter;
	}

	public void setItems() {
		switch (filter) {
			case 1:
				beacons = api.getActiveBeacons();
				break;
			case 2:
				beacons = api.getPostedBeacons();
				break;
			default:
				beacons = api.getNearbyBeacons();
		}
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
		holder.getDateView().setText(BeaconViewHolder.formatDate(beacon.getDate()));
	}

	@Override public int getItemCount() {
		return beacons.size();
	}
}
