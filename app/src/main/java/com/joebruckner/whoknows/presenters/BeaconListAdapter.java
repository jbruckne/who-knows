package com.joebruckner.whoknows.presenters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.models.Beacon;
import com.joebruckner.whoknows.ui.Beacon.BeaconViewHolder;

import java.util.List;

public class BeaconListAdapter extends RecyclerView.Adapter<BeaconViewHolder> {
	List<Beacon> beacons;

	public void setItems(List<Beacon> beacons) {
		this.beacons = beacons;
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
