package com.joebruckner.whoknows.modules;

import android.app.Activity;

import com.joebruckner.whoknows.managers.AccountManager;
import com.joebruckner.whoknows.presenters.Impl.LoginPresenterImpl;
import com.joebruckner.whoknows.presenters.Impl.RegisterPresenterImpl;
import com.joebruckner.whoknows.presenters.LoginPresenter;
import com.joebruckner.whoknows.presenters.RegisterPresenter;
import com.joebruckner.whoknows.ui.StartUp.AuthActivity;
import com.joebruckner.whoknows.ui.StartUp.LoginFragment;
import com.joebruckner.whoknows.ui.StartUp.RegisterFragment;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module (
		injects = {
				AuthActivity.class,
				LoginFragment.class,
				RegisterFragment.class
		},
		addsTo = AppModule.class,
		complete = false,
		library = true
)
public class StartUpModule {
	private final Activity activity;

	public StartUpModule(Activity activity) {
		this.activity = activity;
	}

	@Provides @Singleton Activity providesActivity() {
		return activity;
	}

	@Provides LoginPresenter providesLoginPresenter(Bus bus, AccountManager api) {
		return new LoginPresenterImpl(bus, api);
	}

	@Provides RegisterPresenter providesRegisterPresenter(Bus bus, AccountManager api) {
		return new RegisterPresenterImpl(bus, api);
	}
}
