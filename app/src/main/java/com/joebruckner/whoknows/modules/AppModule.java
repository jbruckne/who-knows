package com.joebruckner.whoknows.modules;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;
import android.os.Build;

import com.joebruckner.whoknows.WhoKnowsApp;
import com.joebruckner.whoknows.modules.qualifiers.ApiLevel;
import com.joebruckner.whoknows.utilities.TestApi;
import com.joebruckner.whoknows.utilities.WhoKnowsApi;
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

	@Provides @Singleton Application provideApplication() {
		return app;
	}

	@Provides @Singleton @ApiLevel int provideApiLevel() {
		return Build.VERSION.SDK_INT;
	}

	@Provides @Singleton Bus provideEventBus() {
		return new Bus(ThreadEnforcer.MAIN);
	}

	@Provides @Singleton WhoKnowsApi provideWhoKnowsApi() {
		return new TestApi();
	}

	@Provides @Singleton LocationManager provideLocationManager() {
		return (LocationManager) app.getSystemService(Context.LOCATION_SERVICE);
	}
}
