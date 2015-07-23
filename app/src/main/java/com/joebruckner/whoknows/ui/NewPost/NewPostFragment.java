package com.joebruckner.whoknows.ui.newPost;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.widget.CheckBox;
import android.widget.EditText;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseFragment;
import com.joebruckner.whoknows.common.SimplePresenter;
import com.joebruckner.whoknows.models.Post;
import com.joebruckner.whoknows.ui.views.NewPostView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;


public class NewPostFragment extends BaseFragment implements NewPostView {
	@Bind(R.id.title) EditText title;
	@Bind(R.id.description) EditText description;
	@Bind(R.id.location) CheckBox location;
	@Inject SimplePresenter<Post, NewPostView> presenter;
	@Inject Activity activity;

	@Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		presenter.attachView(this);
	}

	@Override public int getLayout() {
		return R.layout.fragment_new_post;
	}

	@OnClick(R.id.button_post) public void onClick() {
		presenter.execute();
	}

	@Override public void showContent() {
		// TODO
	}

	@Override public void showLoading() {
		// TODO
	}

	@Override public void showError() {
		// TODO
	}

	@Override public void setData(@NonNull Post post) {
		// TODO
	}

	@Override public String getTitle() {
		return title.getText().toString();
	}

	@Override public String getDescription() {
		return description.getText().toString();
	}

	@Override public boolean getLocation() {
		return location.isChecked();
	}

	@Override public void showSuccess() {
		Snackbar.make(getView(), "Successfully posted", Snackbar.LENGTH_SHORT).show();
		activity.finish();
	}

	@Override public void onDestroyView() {
		presenter.detachView();
		super.onDestroyView();
	}

	public static NewPostFragment newInstance() {
		return new NewPostFragment();
	}
}
