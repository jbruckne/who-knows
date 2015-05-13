package com.joebruckner.whoknows.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseFragment;
import com.joebruckner.whoknows.modules.qualifiers.ForActivity;
import com.joebruckner.whoknows.presenters.PostedAdapter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PostedFragment extends BaseFragment {
	@InjectView( R.id.list_view) RecyclerView listView;
	@Inject @ForActivity Context context;
	@Inject PostedAdapter adapter;

	public static PostedFragment newInstance() {
		return new PostedFragment();
	}

	public PostedFragment() {
		// Required empty public constructor
	}

	@Nullable @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_posted, container, false);
		ButterKnife.inject(this, view);
		listView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
		listView.setLayoutManager(new LinearLayoutManager(context));
		listView.setAdapter(adapter);
		listView.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
		return view;
	}

	@Override public void onResume() {
		super.onResume();
		adapter.updateItems();
	}
}
