package com.joebruckner.whoknows.ui.Beacon;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.models.Beacon;

public class OverviewFragment extends android.support.v4.app.Fragment {

	BeaconViewHolder holder;
	Beacon beacon;

	public static OverviewFragment newInstant(Beacon beacon) {
		OverviewFragment fragment = new OverviewFragment();
		Bundle bundle = new Bundle();
		bundle.putParcelable(BeaconDetailActivity.BEACON_EXTRA, beacon);
		fragment.setArguments(bundle);
		return fragment;
	}

	public OverviewFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_overview, container, false);
		beacon = getArguments().getParcelable(BeaconDetailActivity.BEACON_EXTRA);
		holder = new BeaconViewHolder(view);
		setupView();
		return view;
	}

	private void setupView() {
		holder.getNameView().setText(beacon.getName());
		holder.getDateView().setText(BeaconViewHolder.formatDate(beacon.getDate()));
		holder.getContactInfo().setText(beacon.getContactInfo());
		holder.getDescriptionView().setText(beacon.getDescription());
	}
}
