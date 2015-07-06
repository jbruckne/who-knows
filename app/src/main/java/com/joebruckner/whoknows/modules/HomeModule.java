package com.joebruckner.whoknows.modules;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.joebruckner.whoknows.network.AppApi;
import com.joebruckner.whoknows.presenters.Impl.PostListPresenterImpl;
import com.joebruckner.whoknows.presenters.PostListPresenter;
import com.joebruckner.whoknows.ui.Home.HomeActivity;
import com.joebruckner.whoknows.ui.Home.PostListAdapter;
import com.joebruckner.whoknows.ui.Home.PostListFragment;
import com.joebruckner.whoknows.ui.NewPost.NewPostActivity;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module (
		injects = {
				HomeActivity.class,
				NewPostActivity.class,
				PostListFragment.class,
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

	@Provides @Singleton Activity providesActivity() {
		return activity;
	}

	@Provides PostListPresenter providesPostListPresenter(AppApi api, Bus bus) {
		return new PostListPresenterImpl(api, bus);
	}

	@Provides PostListAdapter providesPostListAdapter() {
		return new PostListAdapter();
	}

	@Provides RecyclerView.LayoutManager providesLayoutManager() {
		return new LinearLayoutManager(activity);
	}
}
