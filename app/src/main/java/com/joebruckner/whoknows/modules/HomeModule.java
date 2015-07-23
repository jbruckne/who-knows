package com.joebruckner.whoknows.modules;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.joebruckner.whoknows.common.SimplePresenter;
import com.joebruckner.whoknows.managers.AccountManager;
import com.joebruckner.whoknows.managers.DatabaseManager;
import com.joebruckner.whoknows.models.Post;
import com.joebruckner.whoknows.models.Profile;
import com.joebruckner.whoknows.presenters.PostListPresenter;
import com.joebruckner.whoknows.presenters.ProfilePresenter;
import com.joebruckner.whoknows.ui.home.HomeActivity;
import com.joebruckner.whoknows.ui.home.PostListAdapter;
import com.joebruckner.whoknows.ui.home.PostListFragment;
import com.joebruckner.whoknows.ui.home.ProfileFragment;
import com.joebruckner.whoknows.ui.views.PostListView;
import com.joebruckner.whoknows.ui.views.ProfileView;
import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module (
		injects = {
				HomeActivity.class,
				PostListFragment.class,
				ProfileFragment.class
		},
		addsTo = AppModule.class,
		complete = false,
		library = true
)
public class HomeModule {
	private final Activity activity;

	public HomeModule(Activity activity) {
		this.activity = activity;
	}

	@Provides @Singleton
	Activity activity() {
		return activity;
	}

	@Provides
	SimplePresenter<List<Post>, PostListView> postListPresenter(Bus bus, DatabaseManager database) {
		return new PostListPresenter(bus, database);
	}

	@Provides
	SimplePresenter<Profile, ProfileView> profilePresenter(Bus bus, AccountManager manager) {
		return new ProfilePresenter(bus, manager);
	}

	@Provides
	PostListAdapter postListAdapter() {
		return new PostListAdapter();
	}

	@Provides
	RecyclerView.LayoutManager layoutManager() {
		return new LinearLayoutManager(activity);
	}
}
