package com.joebruckner.whoknows.modules;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;

import com.firebase.client.Firebase;
import com.joebruckner.whoknows.WhoKnowsApp;
import com.joebruckner.whoknows.managers.AccountManager;
import com.joebruckner.whoknows.managers.DatabaseManager;
import com.joebruckner.whoknows.managers.Impl.FirebaseAccountManager;
import com.joebruckner.whoknows.managers.Impl.FirebaseDatabaseManager;
import com.joebruckner.whoknows.managers.LocationApi;
import com.joebruckner.whoknows.managers.Test.TestLocationApi;
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

	@Provides @Singleton AccountManager providesAccountManager(Firebase ref, Bus bus) {
		return new FirebaseAccountManager(ref, bus);
	}

	@Provides @Singleton DatabaseManager providesDatabaseManager(Application app,
	                                                             AccountManager api, Bus bus) {
		return new FirebaseDatabaseManager(app, api, bus);
	}

	@Provides @Singleton LocationApi providesLocationApi(LocationManager manager, Bus bus) {
		return new TestLocationApi(manager, bus, app);
	}

	@Provides @Singleton LocationManager providesLocationManager() {
		return (LocationManager) app.getSystemService(Context.LOCATION_SERVICE);
	}
}
