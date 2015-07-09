package com.joebruckner.whoknows.ui.Post;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseFragment;
import com.joebruckner.whoknows.models.Post;
import com.joebruckner.whoknows.models.PostViewHolder;
import com.joebruckner.whoknows.presenters.PostSummaryPresenter;

import java.util.Date;

import javax.inject.Inject;

public class PostSummaryFragment extends BaseFragment implements PostSummaryView {
	@Inject PostSummaryPresenter presenter;
	@Inject Activity activity;

	PostViewHolder holder;
	Post post = new Post();

	public static PostSummaryFragment newInstant() {
		return new PostSummaryFragment();
	}

	public PostSummaryFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_summary, container, false);
	}

	@Override public void onResume() {
		super.onResume();
		String id = activity.getIntent().getStringExtra(PostDetailActivity.BEACON_ID);
		presenter.attachView(this);
		presenter.fetchPost(id);
	}

	@Override public void showLoading() {
		// TODO
	}

	@Override public void showContent() {
		Date date = new Date();
		date.setTime(post.getDate());
		holder = new PostViewHolder(getView());
		holder.getNameView().setText(post.getName());
		holder.getDateView().setText(PostViewHolder.formatDate(date));
		holder.getContactInfo().setText(post.getContactInfo());
		holder.getDescriptionView().setText(post.getDescription());
	}

	@Override public void showError() {
		// TODO
	}

	@Override public void setData(Post post) {
		this.post = post;
		showContent();
	}

	@Override public void onDestroyView() {
		presenter.detachView();
		super.onDestroyView();
	}
}
