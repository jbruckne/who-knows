package com.joebruckner.whoknows.ui.Home;


import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseFragment;
import com.joebruckner.whoknows.models.Profile;
import com.joebruckner.whoknows.presenters.ProfilePresenter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class ProfileFragment extends BaseFragment implements ProfileView {
	@Bind(R.id.name) TextView name;
	@Bind(R.id.email) TextView email;
	@Bind(R.id.phone) TextView phone;
	@Bind(R.id.post_history) Button postHistory;
	@Inject ProfilePresenter presenter;

	Profile profile;

	public static ProfileFragment newInstance() {
		return new ProfileFragment();
	}

	public ProfileFragment() {
		// Required empty public constructor
	}

	@Override public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (presenter == null) Log.e("ProfileFragment", "Presenter is null!!!");
		else Log.d("ProfileFragment", presenter + "");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		Log.d("ProfileFragment", "creating view");
		presenter.attachView(this);
		presenter.loadProfile();
		return inflater.inflate(R.layout.fragment_profile, container, false);
	}

	@OnClick(R.id.post_history) public void postHistory() {
		Snackbar.make(getView(), "Post History Clicked", Snackbar.LENGTH_SHORT).show();
	}

	@Override public void showLoading() {
		// TODO
	}

	@Override public void showContent() {
		if (profile == null) return;
		name.setText(profile.getName());
		email.setText(profile.getEmail());
		phone.setText(profile.getPhone());
	}

	@Override public void showError() {
		Snackbar.make(getView(), "Error", Snackbar.LENGTH_SHORT).show();
	}

	@Override public void setData(Profile profile) {
		this.profile = profile;
		showContent();
	}

	@Override public void onDestroyView() {
		presenter.detachView();
		super.onDestroyView();
	}
}
