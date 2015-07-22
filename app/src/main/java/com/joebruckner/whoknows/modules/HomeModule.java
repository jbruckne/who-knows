package com.joebruckner.whoknows.modules;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.joebruckner.whoknows.managers.AccountManager;
import com.joebruckner.whoknows.managers.DatabaseManager;
import com.joebruckner.whoknows.models.Post;
import com.joebruckner.whoknows.presenters.Impl.PostListPresenter;
import com.joebruckner.whoknows.presenters.Impl.ProfilePresenterImpl;
import com.joebruckner.whoknows.presenters.ProfilePresenter;
import com.joebruckner.whoknows.presenters.SimplePresenter;
import com.joebruckner.whoknows.ui.Home.HomeActivity;
import com.joebruckner.whoknows.ui.Home.PostListAdapter;
import com.joebruckner.whoknows.ui.Home.PostListFragment;
import com.joebruckner.whoknows.ui.Home.ProfileFragment;
import com.joebruckner.whoknows.ui.NewPost.NewPostActivity;
import com.joebruckner.whoknows.ui.views.PostListView;
import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module (
		injects = {
				HomeActivity.class,
				NewPostActivity.class,
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

	@Provides @Singleton Activity providesActivity() {
		return activity;
	}

	@Provides SimplePresenter<List<Post>, PostListView> providesPostListPresenter
			(Bus bus, DatabaseManager database) {
		return new PostListPresenter(bus, database);
	}

	@Provides ProfilePresenter providesProfilePresenter(AccountManager manager, Bus bus) {
		return new ProfilePresenterImpl(manager, bus);
	}

	@Provides PostListAdapter providesPostListAdapter() {
		return new PostListAdapter();
	}

	@Provides RecyclerView.LayoutManager providesLayoutManager() {
		return new LinearLayoutManager(activity);
	}
}
