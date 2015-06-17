package com.joebruckner.whoknows.ui.Home;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseFragment;
import com.joebruckner.whoknows.models.Beacon;
import com.joebruckner.whoknows.presenters.BaseView;
import com.joebruckner.whoknows.presenters.BeaconListAdapter;
import com.joebruckner.whoknows.presenters.BeaconListPresenter;
import com.joebruckner.whoknows.ui.Beacon.BeaconDetailActivity;
import com.joebruckner.whoknows.ui.Widgets.DividerItemDecoration;
import com.joebruckner.whoknows.ui.Widgets.OnItemClickListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class BeaconListFragment extends BaseFragment implements BaseView<List<Beacon>> {
	@InjectView(R.id.list_view) RecyclerView listView;
	@Inject Activity activity;
	@Inject BeaconListPresenter presenter;
	@Inject BeaconListAdapter adapter;

	public static final int NEARBY = 0;
	public static final int JOINED = 1;
	public static final int POSTED = 2;
	public static String LIST_TYPE = "TYPE";

	// Filter type for the list of beacons
	public int type;

	public static BeaconListFragment newInstance(int type) {
		BeaconListFragment fragment = new BeaconListFragment();
		Bundle bundle = new Bundle();
		bundle.putInt(LIST_TYPE, type);
		fragment.setArguments(bundle);
		return fragment;
	}

	public BeaconListFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		type = getArguments().getInt(LIST_TYPE);
		View view = inflater.inflate(R.layout.fragment_beacon_list, container, false);
		ButterKnife.inject(this, view);
		presenter.attachView(this);
		presenter.loadData(type);
		return view;
	}

	@Override public void onResume() {
		super.onResume();
		presenter.loadData(type);
	}

	@Override public void showLoading() {
		// TODO
	}

	@Override public void showContent() {
		listView.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL_LIST));
		listView.setLayoutManager(new LinearLayoutManager(activity));
		listView.setAdapter(adapter);
		listView.addOnItemTouchListener(new OnItemClickListener(activity) {
			@Override public void onItemClick(View view, int position) {
				Intent intent = new Intent(activity, BeaconDetailActivity.class);
				intent.putExtra(BeaconDetailActivity.BEACON_EXTRA, adapter.getItem(position));
				startActivity(intent);
			}
		});
	}

	@Override public void showError() {
		// TODO
	}

	@Override public void setData(List<Beacon> data) {
		adapter.setItems(data);
	}
}