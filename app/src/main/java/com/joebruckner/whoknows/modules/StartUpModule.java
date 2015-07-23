package com.joebruckner.whoknows.modules;

import android.app.Activity;

import com.joebruckner.whoknows.managers.AccountManager;
import com.joebruckner.whoknows.models.Profile;
import com.joebruckner.whoknows.presenters.LoginPresenter;
import com.joebruckner.whoknows.presenters.RegisterPresenter;
import com.joebruckner.whoknows.common.SimplePresenter;
import com.joebruckner.whoknows.ui.startUp.AuthActivity;
import com.joebruckner.whoknows.ui.startUp.LoginFragment;
import com.joebruckner.whoknows.ui.startUp.RegisterFragment;
import com.joebruckner.whoknows.ui.views.LoginView;
import com.joebruckner.whoknows.ui.views.RegisterView;
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

	@Provides @Singleton
	Activity activity() {
		return activity;
	}

	@Provides
	SimplePresenter<Profile, LoginView> loginPresenter(Bus bus, AccountManager account) {
		return new LoginPresenter(bus, account);
	}

	@Provides
	SimplePresenter<Profile, RegisterView> registerPresenter(Bus bus, AccountManager account) {
		return new RegisterPresenter(bus, account);
	}
}
