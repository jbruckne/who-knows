package com.joebruckner.whoknows.modules;

import android.app.Activity;

import com.joebruckner.whoknows.network.AccountApi;
import com.joebruckner.whoknows.presenters.Impl.LoginPresenterImpl;
import com.joebruckner.whoknows.presenters.Impl.RegisterPresenterImpl;
import com.joebruckner.whoknows.presenters.LoginPresenter;
import com.joebruckner.whoknows.presenters.RegisterPresenter;
import com.joebruckner.whoknows.ui.StartUp.LoginActivity;
import com.joebruckner.whoknows.ui.StartUp.RegisterActivity;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module (
		injects = {
				LoginActivity.class,
				RegisterActivity.class
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

	@Provides LoginPresenter providesLoginPresenter(Bus bus) {
		return new LoginPresenterImpl(bus);
	}

	@Provides RegisterPresenter providesRegisterPresenter(Bus bus, AccountApi api) {
		return new RegisterPresenterImpl(bus, api);
	}
}
