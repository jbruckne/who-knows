package com.joebruckner.whoknows.modules;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.joebruckner.whoknows.modules.qualifiers.ForActivity;
import com.joebruckner.whoknows.presenters.BeaconListAdapter;
import com.joebruckner.whoknows.presenters.LocalMapPresenter;
import com.joebruckner.whoknows.presenters.MapPresenter;
import com.joebruckner.whoknows.ui.BeaconListFragment;
import com.joebruckner.whoknows.ui.LocalMapFragment;
import com.joebruckner.whoknows.ui.MainActivity;
import com.joebruckner.whoknows.utilities.WhoknowsApi;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module (
		injects = {
				MainActivity.class,
				LocalMapFragment.class,
				BeaconListFragment.class
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

	@Provides @Singleton MapPresenter providesMapPresenter(LocationManager locationManager, Bus bus) {
		return new LocalMapPresenter(locationManager, activity, bus);
	}

	@Provides BeaconListAdapter providesBeaconListAdapter(WhoknowsApi api, Bus bus) {
		return new BeaconListAdapter(api.getBeacons(), bus, activity);
	}

	@Provides RecyclerView.LayoutManager provideslayoutManager() {
		return new LinearLayoutManager(activity);
	}
}
