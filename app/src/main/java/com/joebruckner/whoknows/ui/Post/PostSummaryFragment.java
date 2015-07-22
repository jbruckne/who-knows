package com.joebruckner.whoknows.ui.Post;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseFragment;
import com.joebruckner.whoknows.models.Post;
import com.joebruckner.whoknows.models.PostViewHolder;
import com.joebruckner.whoknows.presenters.PostSummaryPresenter;

import java.util.Date;

import javax.inject.Inject;

import butterknife.Bind;

public class PostSummaryFragment extends BaseFragment
		implements PostSummaryView, View.OnClickListener {
	@Bind(R.id.name) TextView name;
	@Bind(R.id.date) TextView date;
	@Bind(R.id.contact_info) TextView contactInfo;
	@Bind(R.id.description) TextView description;
	@Bind(R.id.fab) FloatingActionButton fab;
	@Inject PostSummaryPresenter presenter;
	@Inject Activity activity;

	@Nullable Post post;

	public static PostSummaryFragment newInstance() {
		return new PostSummaryFragment();
	}

	public PostSummaryFragment() {
		// Required empty public constructor
	}

	@Override public int getLayout() {
		return R.layout.fragment_summary;
	}

	@Override public void sendEvent(int tag) {

	}

	@Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		fab.setOnClickListener(this);
		String id = activity.getIntent().getStringExtra(PostActivity.BEACON_ID);
		presenter.attachView(this);
		presenter.getPosts(id);
	}

	@Override public void showLoading() {
		// TODO
	}

	@Override public void showSummary() {
		if (post == null) return;
		Date timestamp = new Date();
		timestamp.setTime(post.getDate());
		name.setText(post.getName());
		date.setText(PostViewHolder.formatDate(timestamp));
		contactInfo.setText(post.getContactInfo());
		description.setText(post.getDescription());
	}

	@Override public void showError() {
		Log.e(TAG, "Error");
	}

	@Override public void setData(Post post) {
		this.post = post;
		showSummary();
	}

	@Override public void onDestroyView() {
		presenter.detachView();
		super.onDestroyView();
	}

	@Override public void onClick(View v) {
		if (post != null)
			presenter.offerHelp(post.getId(), post.getUserId());
	}
}
