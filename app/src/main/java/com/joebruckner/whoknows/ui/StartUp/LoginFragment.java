package com.joebruckner.whoknows.ui.startUp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseFragment;
import com.joebruckner.whoknows.models.Profile;
import com.joebruckner.whoknows.common.SimplePresenter;
import com.joebruckner.whoknows.ui.home.HomeActivity;
import com.joebruckner.whoknows.ui.views.LoginView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment implements LoginView {
	@Bind(R.id.email) TextView email;
	@Bind(R.id.password) TextView password;
	@Inject SimplePresenter<Profile, LoginView> presenter;
	@Inject Activity activity;

	Profile profile;

	@Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		presenter.attachView(this);
	}

	@Override public int getLayout() {
		return R.layout.fragment_login;
	}

	@OnClick(R.id.login_button) public void login() {
		presenter.execute();
	}

	@OnClick(R.id.register_button) public void register() {
		((AppCompatActivity) activity).getSupportFragmentManager().beginTransaction()
				.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
						android.R.anim.slide_in_left, android.R.anim.slide_out_right)
				.replace(R.id.container, RegisterFragment.newInstance())
				.addToBackStack(null)
				.commit();
	}

	@Override public void showContent() {

	}

	@Override public void showLoading() {

	}

	@Override public void showError() {
		Snackbar.make(getView(), "Error", Snackbar.LENGTH_SHORT).show();
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

	@Override public void showSuccess() {
		if (profile == null) return;
		Snackbar.make(getView(), "Hello, " + profile.getName(), Snackbar.LENGTH_SHORT).show();
		startActivity(new Intent(activity, HomeActivity.class));
		activity.finish();
	}

	@Override public void showInvalid(String error) {
		Snackbar.make(getView(), error, Snackbar.LENGTH_LONG).show();
	}

	@Override public void onDestroyView() {
		Log.d(TAG, "onDestroyView");
		presenter.detachView();
		super.onDestroyView();
	}

	public static LoginFragment newInstance() {
		return new LoginFragment();
	}
}
