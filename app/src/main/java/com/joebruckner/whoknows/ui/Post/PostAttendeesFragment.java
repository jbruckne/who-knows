package com.joebruckner.whoknows.ui.Post;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseFragment;
import com.joebruckner.whoknows.presenters.PostAttendeesPresenter;
import com.joebruckner.whoknows.ui.Widgets.DividerItemDecoration;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PostAttendeesFragment extends BaseFragment implements PostAttendeesView {
	@Bind(R.id.list) RecyclerView listView;
	@Inject PostAttendeesPresenter presenter;
	@Inject PostAttendeesAdapter adapter;
	@Inject Activity activity;

	public static PostAttendeesFragment newInstance() {
		return new PostAttendeesFragment();
	}

	public PostAttendeesFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_attendees_list, container, false);
		ButterKnife.bind(this, view);
		return view;
	}

	@Override public void onResume() {
		super.onResume();
		String id = activity.getIntent().getStringExtra(PostDetailActivity.BEACON_ID);
		presenter.attachView(this);
		presenter.fetchAttendees(id);
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

	@Override public void setAttendees(List<String> attendees) {
		adapter.setItems(attendees);
	}
}
