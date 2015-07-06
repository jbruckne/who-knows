package com.joebruckner.whoknows.modules;

import android.app.Activity;

import com.joebruckner.whoknows.network.AppApi;
import com.joebruckner.whoknows.presenters.Impl.PostAttendeesPresenterImpl;
import com.joebruckner.whoknows.presenters.Impl.PostSummaryPresenterImpl;
import com.joebruckner.whoknows.presenters.PostAttendeesPresenter;
import com.joebruckner.whoknows.presenters.PostSummaryPresenter;
import com.joebruckner.whoknows.ui.Post.PostAttendeesAdapter;
import com.joebruckner.whoknows.ui.Post.PostAttendeesFragment;
import com.joebruckner.whoknows.ui.Post.PostDetailActivity;
import com.joebruckner.whoknows.ui.Post.PostSummaryFragment;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module (
		injects = {
				PostDetailActivity.class,
				PostSummaryFragment.class,
				PostAttendeesFragment.class
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

	@Provides @Singleton Activity providesActivity() {
		return activity;
	}

	@Provides PostSummaryPresenter providesPostSummaryPresenter(AppApi api, Bus bus) {
		return new PostSummaryPresenterImpl(api, bus);
	}

	@Provides PostAttendeesPresenter providesPostAttendeesPresenter(AppApi api, Bus bus) {
		return new PostAttendeesPresenterImpl(api, bus);
	}

	@Provides PostAttendeesAdapter providesPostAttendeesAdapter() {
		return new PostAttendeesAdapter();
	}
}
