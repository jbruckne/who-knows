package com.joebruckner.whoknows.ui.startUp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.TextView;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseFragment;
import com.joebruckner.whoknows.models.Profile;
import com.joebruckner.whoknows.common.SimplePresenter;
import com.joebruckner.whoknows.ui.home.HomeActivity;
import com.joebruckner.whoknows.ui.views.RegisterView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class RegisterFragment extends BaseFragment implements RegisterView {
	@Bind(R.id.name) TextView name;
	@Bind(R.id.email) TextView email;
	@Bind(R.id.password) TextView password;
	@Bind(R.id.confirm_password) TextView passwordCheck;
	@Inject SimplePresenter<Profile, RegisterView> presenter;
	@Inject Activity activity;

	Profile profile;

	public RegisterFragment() {
		// Required empty public constructor
	}

	@Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		presenter.attachView(this);
	}

	@Override public int getLayout() {
		return R.layout.fragment_register;
	}

	@OnClick(R.id.register_button) public void register() {
		Log.d(TAG, "register");
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

	@Override public void setData(@NonNull Profile profile) {
		this.profile = profile;
	}

	@Override public String getEmail() {
		return email.getText().toString();
	}

	@Override public String getPassword() {
		return password.getText().toString();
	}

	@Override public String getName() {
		return name.getText().toString();
	}

	@Override public String getPasswordCheck() {
		return passwordCheck.getText().toString();
	}

	@Override public void showSuccess() {
		Snackbar.make(getView(), "Welcome, " + profile.getName(), Snackbar.LENGTH_SHORT).show();
		startActivity(new Intent(activity, HomeActivity.class));
		activity.finish();
	}

	@Override public void showInvalid(String error) {
		Snackbar.make(getView(), error, Snackbar.LENGTH_LONG).show();
	}

	@Override public void onDestroyView() {
		Log.d("RegisterView", "Detached");
		presenter.detachView();
		super.onDestroyView();
	}

	public static RegisterFragment newInstance() {
		return new RegisterFragment();
	}
}
