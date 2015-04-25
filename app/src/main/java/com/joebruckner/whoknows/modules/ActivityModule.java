package com.joebruckner.whoknows.modules;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;

import com.joebruckner.whoknows.modules.qualifiers.ForActivity;
import com.joebruckner.whoknows.presenters.MapPresenter;
import com.joebruckner.whoknows.presenters.LocalMapPresenter;
import com.joebruckner.whoknows.ui.LocalMapFragment;
import com.joebruckner.whoknows.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module (
		injects = {
				MainActivity.class,
				LocalMapFragment.class
		},
		addsTo = AppModule.class,
		complete = false,
		library = true
)
public class ActivityModule {
	private final Activity activity;

	public ActivityModule(Activity activity) {
		this.activity = activity;
	}

	@Provides @Singleton @ForActivity Context provideActivity() {
		return activity;
	}

	@Provides @Singleton MapPresenter providesMapPresenter(LocationManager locationManager) {
		return new LocalMapPresenter(locationManager, activity);
	}
}
