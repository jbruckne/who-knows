package com.joebruckner.whoknows.ui.Home;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseFragment;
import com.joebruckner.whoknows.modules.qualifiers.ForActivity;
import com.joebruckner.whoknows.presenters.BeaconListAdapter;
import com.joebruckner.whoknows.ui.Beacon.BeaconDetailActivity;
import com.joebruckner.whoknows.ui.Widgets.DividerItemDecoration;
import com.joebruckner.whoknows.ui.Widgets.OnItemClickListener;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class BeaconListFragment extends BaseFragment {
	@InjectView(R.id.list_view) RecyclerView listView;
	@Inject @ForActivity Context context;
	@Inject BeaconListAdapter adapter;

	public static final int NEARBY = 0;
	public static final int JOINED = 1;
	public static final int POSTED = 2;

	public static String LIST_TYPE = "TYPE";
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
		setupList();
		return view;
	}

	private void setupList() {
		listView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
		listView.setLayoutManager(new LinearLayoutManager(context));
		adapter.setFilter(type);
		listView.setAdapter(adapter);
		listView.addOnItemTouchListener(new OnItemClickListener(context) {
			@Override public void onItemClick(View view, int position) {
				Intent intent = new Intent(context, BeaconDetailActivity.class);
				intent.putExtra(BeaconDetailActivity.BEACON_EXTRA, adapter.getItem(position));
				startActivity(intent);
			}
		});
		listView.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
	}

	@Override public void onResume() {
		super.onResume();
		adapter.setItems();
	}
}