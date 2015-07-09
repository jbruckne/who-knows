package com.joebruckner.whoknows.modules;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;

import com.firebase.client.Firebase;
import com.joebruckner.whoknows.WhoKnowsApp;
import com.joebruckner.whoknows.network.AccountApi;
import com.joebruckner.whoknows.network.AppApi;
import com.joebruckner.whoknows.network.Impl.FirebaseAccountApi;
import com.joebruckner.whoknows.network.Impl.FirebaseAppApi;
import com.joebruckner.whoknows.network.LocationApi;
import com.joebruckner.whoknows.network.Test.TestLocationApi;
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

	@Provides @Singleton Firebase providesFirebase() {
		return new Firebase("https://sizzling-torch-124.firebaseio.com");
	}

	@Provides @Singleton AccountApi providesAccountApi(Firebase ref, Bus bus) {
		return new FirebaseAccountApi(ref, bus);
	}

	@Provides @Singleton AppApi providesWhoKnowsApi(Application app, AccountApi api, Bus bus) {
		return new FirebaseAppApi(app, api, bus);
	}

	@Provides @Singleton LocationApi providesLocationApi(LocationManager manager, Bus bus) {
		return new TestLocationApi(manager, bus, app);
	}

	@Provides @Singleton LocationManager providesLocationManager() {
		return (LocationManager) app.getSystemService(Context.LOCATION_SERVICE);
	}
}
