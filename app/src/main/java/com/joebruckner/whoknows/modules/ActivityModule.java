package com.joebruckner.whoknows.modules;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.joebruckner.whoknows.modules.qualifiers.ForActivity;
import com.joebruckner.whoknows.presenters.JoinedAdapter;
import com.joebruckner.whoknows.presenters.NearbyAdapter;
import com.joebruckner.whoknows.presenters.PostedAdapter;
import com.joebruckner.whoknows.ui.HomeActivity;
import com.joebruckner.whoknows.ui.JoinedFragment;
import com.joebruckner.whoknows.ui.NearbyFragment;
import com.joebruckner.whoknows.ui.NewBeaconActivity;
import com.joebruckner.whoknows.ui.PostedFragment;
import com.joebruckner.whoknows.utilities.AppApi;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module (
		injects = {
				HomeActivity.class,
				NewBeaconActivity.class,
				NearbyFragment.class,
				JoinedFragment.class,
				PostedFragment.class
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

	@Provides NearbyAdapter providesNearbyAdapter(AppApi api, Bus bus) {
		return new NearbyAdapter(api, bus);
	}

	@Provides JoinedAdapter providesJoinedAdapter(AppApi api, Bus bus) {
		return new JoinedAdapter(api, bus);
	}

	@Provides PostedAdapter providesPostedAdapter(AppApi api, Bus bus) {
		return new PostedAdapter(api, bus);
	}

	@Provides RecyclerView.LayoutManager providesLayoutManager() {
		return new LinearLayoutManager(activity);
	}
}
