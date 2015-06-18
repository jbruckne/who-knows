package com.joebruckner.whoknows.modules;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.joebruckner.whoknows.presenters.AttendeesListPresenter;
import com.joebruckner.whoknows.presenters.BeaconOverviewPresenter;
import com.joebruckner.whoknows.ui.Beacon.AttendeesListAdapter;
import com.joebruckner.whoknows.ui.Beacon.AttendeesListFragment;
import com.joebruckner.whoknows.ui.Home.BeaconListAdapter;
import com.joebruckner.whoknows.presenters.BeaconListPresenter;
import com.joebruckner.whoknows.ui.Beacon.BeaconDetailActivity;
import com.joebruckner.whoknows.ui.Beacon.NewBeaconActivity;
import com.joebruckner.whoknows.ui.Beacon.OverviewFragment;
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
				BeaconDetailActivity.class,
				BeaconListFragment.class,
				OverviewFragment.class,
				AttendeesListFragment.class
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

	@Provides BeaconOverviewPresenter providesOverviewPresenter(AppApi api, Bus bus) {
		return new BeaconOverviewPresenter(api, bus);
	}

	@Provides AttendeesListPresenter providesAttendeesListPresenter(AppApi api, Bus bus) {
		return new AttendeesListPresenter(api, bus);
	}

	@Provides AttendeesListAdapter providesAttendeesListAdapter() {
		return new AttendeesListAdapter();
	}

	@Provides RecyclerView.LayoutManager providesLayoutManager() {
		return new LinearLayoutManager(activity);
	}
}
