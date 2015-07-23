package com.joebruckner.whoknows.modules;

import android.app.Activity;

import com.joebruckner.whoknows.common.SimplePresenter;
import com.joebruckner.whoknows.managers.AccountManager;
import com.joebruckner.whoknows.managers.DatabaseManager;
import com.joebruckner.whoknows.models.Post;
import com.joebruckner.whoknows.presenters.NewPostPresenter;
import com.joebruckner.whoknows.ui.newPost.NewPostActivity;
import com.joebruckner.whoknows.ui.newPost.NewPostFragment;
import com.joebruckner.whoknows.ui.views.NewPostView;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
		injects = {
				NewPostActivity.class,
				NewPostFragment.class
		},
		addsTo = AppModule.class,
		complete = false,
		library = true
)
public class NewPostModule {
	private final Activity activity;

	public NewPostModule(Activity activity) {
		this.activity = activity;
	}

	@Provides @Singleton
	Activity providesActivity() {
		return activity;
	}

	@Provides SimplePresenter<Post, NewPostView>
	newPostPresenter(DatabaseManager database, AccountManager account, Bus bus) {
		return new NewPostPresenter(database, account, bus);
	}
}
