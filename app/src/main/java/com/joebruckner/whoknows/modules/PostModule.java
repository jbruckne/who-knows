package com.joebruckner.whoknows.modules;

import android.app.Activity;

import com.joebruckner.whoknows.common.SimplePresenter;
import com.joebruckner.whoknows.managers.DatabaseManager;
import com.joebruckner.whoknows.models.Post;
import com.joebruckner.whoknows.presenters.PostSummaryPresenter;
import com.joebruckner.whoknows.ui.post.PostActivity;
import com.joebruckner.whoknows.ui.post.PostSummaryFragment;
import com.joebruckner.whoknows.ui.views.PostSummaryView;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module (
		injects = {
				PostActivity.class,
				PostSummaryFragment.class
		},
		addsTo = AppModule.class,
		complete = false,
		library = true
)
public class PostModule {
	private final Activity activity;

	public PostModule(Activity activity) {
		this.activity = activity;
	}

	@Provides @Singleton
	Activity activity() {
		return activity;
	}

	@Provides
	SimplePresenter<Post, PostSummaryView> postSummaryPresenter(Bus bus, DatabaseManager database) {
		return new PostSummaryPresenter(bus, database);
	}
}
