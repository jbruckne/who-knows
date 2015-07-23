package com.joebruckner.whoknows.ui.home;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.widget.TextView;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseFragment;
import com.joebruckner.whoknows.models.Profile;
import com.joebruckner.whoknows.common.SimplePresenter;
import com.joebruckner.whoknows.ui.views.ProfileView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class ProfileFragment extends BaseFragment implements ProfileView {
	@Bind(R.id.name) TextView name;
	@Bind(R.id.email) TextView email;
	@Bind(R.id.phone) TextView phone;
	@Inject SimplePresenter<Profile, ProfileView> presenter;

	Profile profile;

	@Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		presenter.attachView(this);
		presenter.execute();
	}

	@Override public int getLayout() {
		return R.layout.fragment_profile;
	}

	@OnClick(R.id.post_history) public void postHistory() {
		Snackbar.make(getView(), "Post History Clicked", Snackbar.LENGTH_SHORT).show();
	}

	@Override public void showLoading() {
		// TODO
	}

	@Override public void showContent() {
		// TODO
	}

	@Override public void showError() {
		Snackbar.make(getView(), "Error", Snackbar.LENGTH_SHORT).show();
	}

	@Override public void setData(@NonNull Profile profile) {
		this.profile = profile;
		name.setText(profile.getName());
		email.setText(profile.getEmail());
		phone.setText(profile.getPhone());
	}

	@Override public void onDestroyView() {
		presenter.detachView();
		super.onDestroyView();
	}

	public static ProfileFragment newInstance() {
		return new ProfileFragment();
	}
}
