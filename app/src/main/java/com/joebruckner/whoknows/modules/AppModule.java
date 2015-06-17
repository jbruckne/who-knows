package com.joebruckner.whoknows.modules;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;

import com.joebruckner.whoknows.WhoKnowsApp;
import com.joebruckner.whoknows.utilities.AccountApi;
import com.joebruckner.whoknows.utilities.AppApi;
import com.joebruckner.whoknows.utilities.LocationApi;
import com.joebruckner.whoknows.utilities.TestAccountApi;
import com.joebruckner.whoknows.utilities.TestAppApi;
import com.joebruckner.whoknows.utilities.TestLocationApi;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module (
		injects = {
				WhoKnowsApp.class
		},
		library = true
)
public class AppModule {
	private final Application app;

	public AppModule(Application app) {
		this.app = app;
	}

	@Provides @Singleton Application providesApplication() {
		return app;
	}

	@Provides @Singleton Bus providesEventBus() {
		return new Bus(ThreadEnforcer.MAIN);
	}

	@Provides @Singleton AccountApi providesAccountApi() {
		return new TestAccountApi();
	}

	@Provides @Singleton AppApi providesWhoKnowsApi(AccountApi api) {
		return new TestAppApi(api);
	}

	@Provides @Singleton LocationApi providesLocationApi(LocationManager manager, Bus bus) {
		return new TestLocationApi(manager, bus, app);
	}

	@Provides @Singleton LocationManager providesLocationManager() {
		return (LocationManager) app.getSystemService(Context.LOCATION_SERVICE);
	}
}
