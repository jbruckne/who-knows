package com.joebruckner.whoknows.ui.StartUp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.SignInButton;
import com.joebruckner.whoknows.R;

import butterknife.Bind;

public class AuthActivity extends AppCompatActivity {
	@Bind(R.id.login_with_google) SignInButton signInButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auth);

	}
}
