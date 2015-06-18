package com.joebruckner.whoknows.ui.Beacon;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseFragment;
import com.joebruckner.whoknows.models.Beacon;
import com.joebruckner.whoknows.presenters.BasePresenter;
import com.joebruckner.whoknows.presenters.BaseView;
import com.joebruckner.whoknows.presenters.BeaconOverviewPresenter;

import javax.inject.Inject;

public class OverviewFragment extends BaseFragment implements BaseView<Beacon> {
	@Inject BeaconOverviewPresenter presenter;
	@Inject Activity activity;

	BeaconViewHolder holder;
	Beacon beacon;
	long id;

	public static OverviewFragment newInstant() {
		return new OverviewFragment();
	}

	public OverviewFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_overview, container, false);
	}

	@Override public void onResume() {
		super.onResume();
		id = activity.getIntent().getLongExtra(BeaconDetailActivity.BEACON_ID, 0);
		Bundle args = new Bundle();
		args.putLong(BasePresenter.ID, id);
		presenter.attachView(this);
		presenter.loadData(args);
	}

	@Override public void showLoading() {
		// TODO
	}

	@Override public void showContent() {
		holder = new BeaconViewHolder(getView());
		holder.getNameView().setText(beacon.getName());
		holder.getDateView().setText(BeaconViewHolder.formatDate(beacon.getDate()));
		holder.getContactInfo().setText(beacon.getContactInfo());
		holder.getDescriptionView().setText(beacon.getDescription());
	}

	@Override public void showError() {
		// TODO
	}

	@Override public void setData(Beacon data) {
		this.beacon = data;
	}
}
