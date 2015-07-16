package com.joebruckner.whoknows.ui.Home;


import android.support.design.widget.Snackbar;
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
	@Inject ProfilePresenter presenter;

	Profile profile;

	public static ProfileFragment newInstance() {
		return new ProfileFragment();
	}

	public ProfileFragment() {
		// Required empty public constructor
	}

	@Override public int getLayout() {
		return R.layout.fragment_profile;
	}

	@Override public void onResume() {
		super.onResume();
		presenter.attachView(this);
		presenter.loadProfile();
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
