package com.joebruckner.whoknows.ui.StartUp;

import android.os.Bundle;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseActivity;
import com.joebruckner.whoknows.modules.StartUpModule;

import java.util.Arrays;
import java.util.List;

public class AuthActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auth);
		if (savedInstanceState == null)
			setFragment();
	}

	public void setFragment() {
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.container, LoginFragment.newInstance())
				.commit();
	}

	@Override protected List<Object> getModules() {
		return Arrays.<Object>asList(new StartUpModule(this));
	}
}
