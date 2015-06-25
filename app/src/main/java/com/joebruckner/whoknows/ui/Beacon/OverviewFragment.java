package com.joebruckner.whoknows.ui.Beacon;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseFragment;
import com.joebruckner.whoknows.models.Post;
import com.joebruckner.whoknows.presenters.BasePresenter;
import com.joebruckner.whoknows.presenters.BaseView;
import com.joebruckner.whoknows.presenters.BeaconOverviewPresenter;

import java.util.Date;

import javax.inject.Inject;

public class OverviewFragment extends BaseFragment implements BaseView<Post> {
	@Inject BeaconOverviewPresenter presenter;
	@Inject Activity activity;

	PostViewHolder holder;
	Post post;
	String id;

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
		id = activity.getIntent().getStringExtra(BeaconDetailActivity.BEACON_ID);
		Bundle args = new Bundle();
		args.putString(BasePresenter.ID, id);
		presenter.attachView(this);
		presenter.loadData(args);
	}

	@Override public void showLoading() {
		// TODO
	}

	@Override public void showContent() {
		holder = new PostViewHolder(getView());
		holder.getNameView().setText(post.getName());
		Date date = new Date();
		date.setTime(post.getDate());
		holder.getDateView().setText(PostViewHolder.formatDate(date));
		holder.getContactInfo().setText(post.getContactInfo());
		holder.getDescriptionView().setText(post.getDescription());
	}

	@Override public void showError() {
		// TODO
	}

	@Override public void setData(Post data) {
		this.post = data;
	}
}
