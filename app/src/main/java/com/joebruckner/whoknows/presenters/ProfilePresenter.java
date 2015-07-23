package com.joebruckner.whoknows.presenters;

import android.support.annotation.NonNull;
import android.util.Log;

import com.joebruckner.whoknows.events.Event;
import com.joebruckner.whoknows.events.ProfileFetchedEvent;
import com.joebruckner.whoknows.managers.AccountManager;
import com.joebruckner.whoknows.models.Profile;
import com.joebruckner.whoknows.common.SimplePresenter;
import com.joebruckner.whoknows.ui.views.ProfileView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

public class ProfilePresenter extends SimplePresenter<Profile, ProfileView> {
	AccountManager manager;

	public ProfilePresenter(@NonNull Bus bus, @NonNull AccountManager manager) {
		super(bus);
		this.manager = manager;
	}

	@Override public void execute(int tag) {
		if (getView() == null) return;
		manager.getProfile();
		getView().showLoading();
	}

	@Subscribe public void getProfile(ProfileFetchedEvent e) {
		if (getView() == null) return;
		Log.d(TAG, "getProfile: " + e);
		if (e.getStatus() == Event.SUCCESS)
			getView().setData(e.getProfile());
		else
			getView().showError();
	}
}
