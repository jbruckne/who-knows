package com.joebruckner.whoknows.ui.StartUp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseActivity;
import com.joebruckner.whoknows.modules.StartUpModule;

import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements AuthView {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		ButterKnife.bind(this);
	}

	@OnClick(R.id.register_button) public void register() {
		startActivity(new Intent(this, RegisterActivity.class));
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

	}

	@Override protected List<Object> getModules() {
		return Arrays.<Object>asList(new StartUpModule(this));
	}
}
