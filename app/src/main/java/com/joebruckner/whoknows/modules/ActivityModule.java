package com.joebruckner.whoknows.modules;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.joebruckner.whoknows.presenters.BeaconListAdapter;
import com.joebruckner.whoknows.presenters.BeaconListPresenter;
import com.joebruckner.whoknows.ui.Beacon.NewBeaconActivity;
import com.joebruckner.whoknows.ui.Home.BeaconListFragment;
import com.joebruckner.whoknows.ui.Home.HomeActivity;
import com.joebruckner.whoknows.utilities.AppApi;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module (
		injects = {
				HomeActivity.class,
				NewBeaconActivity.class,
				BeaconListFragment.class,
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

	@Provides @Singleton Activity providesActivity() {
		return activity;
	}

	@Provides BeaconListPresenter providesBeaconListPresenter(AppApi api, Bus bus) {
		return new BeaconListPresenter(api, bus);
	}

	@Provides BeaconListAdapter providesBeaconListAdapter() {
		return new BeaconListAdapter();
	}

	@Provides RecyclerView.LayoutManager providesLayoutManager() {
		return new LinearLayoutManager(activity);
	}
}
