package com.joebruckner.whoknows.ui.StartUp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.TextView;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseActivity;
import com.joebruckner.whoknows.modules.StartUpModule;
import com.joebruckner.whoknows.presenters.RegisterPresenter;
import com.joebruckner.whoknows.ui.Home.HomeActivity;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements AuthView {
	@Bind(R.id.email) TextView email;
	@Bind(R.id.password) TextView password;
	@Bind(R.id.confirm_password) TextView confirmPassword;

	@Inject RegisterPresenter presenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		ButterKnife.bind(this);
		presenter.attachView(this);
	}

	@OnClick(R.id.register_button) public void register() {
		Log.d("Register", "Register clicked");
		presenter.registerAndAuth(
				email.getText().toString(),
				password.getText().toString(),
				confirmPassword.getText().toString());
	}

	@Override public void showLoading() {

	}

	@Override public void showSuccess() {
		Snackbar.make(getWindow().getDecorView().getRootView(), "Successfully Logged In", Snackbar
				.LENGTH_SHORT)
				.setAction("Action", null)
				.show();
	}

	@Override public void showError(int code) {

	}

	@Override public void advanceToHome() {
		startActivity(new Intent(this, HomeActivity.class));
		finish();
	}

	@Override protected List<Object> getModules() {
		return Arrays.<Object>asList(new StartUpModule(this));
	}
}
