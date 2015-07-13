package com.joebruckner.whoknows.ui.StartUp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseFragment;
import com.joebruckner.whoknows.presenters.RegisterPresenter;
import com.joebruckner.whoknows.ui.Home.HomeActivity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class RegisterFragment extends BaseFragment implements AuthView {
	@Bind(R.id.name) TextView name;
	@Bind(R.id.email) TextView email;
	@Bind(R.id.password) TextView password;
	@Bind(R.id.confirm_password) TextView confirmPassword;
	@Inject RegisterPresenter presenter;
	@Inject Activity activity;

	public static RegisterFragment newInstance() {
		return new RegisterFragment();
	}

	public RegisterFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_register, container, false);
		presenter.attachView(this);
		return view;
	}



	@OnClick(R.id.register_button) public void register() {
		Log.d("Register", "Register clicked");
		presenter.registerAndAuth(
				name.getText().toString(),
				email.getText().toString(),
				password.getText().toString(),
				confirmPassword.getText().toString());
	}

	@Override public void showLoading() {

	}

	@Override public void showSuccess() {
		startActivity(new Intent(activity, HomeActivity.class));
		activity.finish();
	}

	@Override public void showError(int code) {

	}

	@Override public void onDestroyView() {
		Log.d("RegisterView", "Detached");
		presenter.detachView();
		super.onDestroyView();
	}
}
