package com.joebruckner.whoknows.ui.StartUp;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseFragment;
import com.joebruckner.whoknows.presenters.LoginPresenter;
import com.joebruckner.whoknows.ui.Home.HomeActivity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class LoginFragment extends BaseFragment implements AuthView {
	@Bind(R.id.email) TextView email;
	@Bind(R.id.password) TextView password;
	@Inject LoginPresenter presenter;
	@Inject Activity activity;

	public static LoginFragment newInstance() {
		return new LoginFragment();
	}

	public LoginFragment() {
		// Required empty public constructor
	}

	@Override public int getLayout() {
		return R.layout.fragment_login;
	}

	@Override public void sendEvent(int tag) {

	}

	@Override public void onResume() {
		super.onResume();
		presenter.attachView(this);
	}

	@OnClick(R.id.login_button) public void login() {
		presenter.login(email.getText().toString(), password.getText().toString());
	}

	@OnClick(R.id.register_button) public void register() {
		((AppCompatActivity) activity).getSupportFragmentManager().beginTransaction()
				.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
						android.R.anim.slide_in_left, android.R.anim.slide_out_right)
				.replace(R.id.container, RegisterFragment.newInstance())
				.addToBackStack(null)
				.commit();
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
		Log.d("LoginView", "Detached");
		presenter.detachView();
		super.onDestroyView();
	}
}
