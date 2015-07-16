package com.joebruckner.whoknows.modules;

import android.app.Activity;

import com.joebruckner.whoknows.managers.AccountManager;
import com.joebruckner.whoknows.managers.DatabaseManager;
import com.joebruckner.whoknows.presenters.Impl.NewPostPresenterImpl;
import com.joebruckner.whoknows.presenters.NewPostPresenter;
import com.joebruckner.whoknows.ui.NewPost.CreatePostFragment;
import com.joebruckner.whoknows.ui.NewPost.NewPostActivity;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
		injects = {
				NewPostActivity.class,
				CreatePostFragment.class
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

	@Provides @Singleton Activity providesActivity() {
		return activity;
	}

	@Provides NewPostPresenter providesNewPostPresenter(DatabaseManager databasemanager,
	                                                    AccountManager accountManager, Bus bus) {
		return new NewPostPresenterImpl(databasemanager, accountManager, bus);
	}
}
