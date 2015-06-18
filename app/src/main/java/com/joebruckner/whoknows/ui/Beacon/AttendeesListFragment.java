package com.joebruckner.whoknows.ui.Beacon;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseFragment;
import com.joebruckner.whoknows.presenters.AttendeesListPresenter;
import com.joebruckner.whoknows.presenters.BaseView;
import com.joebruckner.whoknows.ui.Widgets.DividerItemDecoration;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class AttendeesListFragment extends BaseFragment implements BaseView<List<String>> {
	@InjectView (R.id.list) RecyclerView listView;
	@Inject AttendeesListPresenter presenter;
	@Inject AttendeesListAdapter adapter;
	@Inject Activity activity;

	public static AttendeesListFragment newInstance() {
		return new AttendeesListFragment();
	}

	public AttendeesListFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_attendees_list, container, false);
		ButterKnife.inject(this, view);
		return view;
	}

	@Override public void onResume() {
		super.onResume();
		long id = activity.getIntent().getLongExtra(BeaconDetailActivity.BEACON_ID, 0);
		presenter.attachView(this);
		presenter.loadData(id);
	}

	@Override public void showLoading() {
		// TODO
	}

	@Override public void showContent() {
		listView.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL_LIST));
		listView.setLayoutManager(new LinearLayoutManager(activity));
		listView.setAdapter(adapter);
	}

	@Override public void showError() {
		// TODO
	}

	@Override public void setData(List<String> data) {
		adapter.setItems(data);
	}
}
