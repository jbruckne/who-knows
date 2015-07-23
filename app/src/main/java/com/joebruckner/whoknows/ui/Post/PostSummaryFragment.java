package com.joebruckner.whoknows.ui.post;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.TextView;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseFragment;
import com.joebruckner.whoknows.common.SimplePresenter;
import com.joebruckner.whoknows.models.Post;
import com.joebruckner.whoknows.models.PostViewHolder;
import com.joebruckner.whoknows.ui.views.PostSummaryView;

import java.util.Date;

import javax.inject.Inject;

import butterknife.Bind;

public class PostSummaryFragment extends BaseFragment implements PostSummaryView {
	@Bind(R.id.name) TextView name;
	@Bind(R.id.date) TextView date;
	@Bind(R.id.contact_info) TextView contactInfo;
	@Bind(R.id.description) TextView description;
	@Inject SimplePresenter<Post, PostSummaryView> presenter;
	@Inject Activity activity;

	String id;

	@Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		id = activity.getIntent().getStringExtra(PostActivity.BEACON_ID);
		presenter.attachView(this);
		presenter.execute();
	}

	@Override public int getLayout() {
		return R.layout.fragment_summary;
	}

	@Override public void sendAction(int tag) {
		presenter.execute(tag);
	}

	@Override public void showLoading() {
		// TODO
	}

	@Override public void showContent() {
		// TODO
	}

	@Override public void showError() {
		Log.e(TAG, "Error");
	}

	@Override public void setData(@NonNull Post post) {
		Date timestamp = new Date();
		timestamp.setTime(post.getDate());
		name.setText(post.getName());
		date.setText(PostViewHolder.formatDate(timestamp));
		contactInfo.setText(post.getContactInfo());
		description.setText(post.getDescription());
	}

	@Override public String getPostId() {
		return id;
	}

	@Override public void showSent() {
		Snackbar.make(getView(), "Thank you for offering", Snackbar.LENGTH_SHORT).show();
	}

	@Override public void onDestroyView() {
		presenter.detachView();
		super.onDestroyView();
	}

	public static PostSummaryFragment newInstance() {
		return new PostSummaryFragment();
	}
}
