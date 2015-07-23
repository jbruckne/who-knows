package com.joebruckner.whoknows.presenters;

import android.util.Log;

import com.joebruckner.whoknows.events.AuthorizedEvent;
import com.joebruckner.whoknows.events.Event;
import com.joebruckner.whoknows.events.ProfileFetchedEvent;
import com.joebruckner.whoknows.managers.AccountManager;
import com.joebruckner.whoknows.models.Profile;
import com.joebruckner.whoknows.common.SimplePresenter;
import com.joebruckner.whoknows.ui.views.LoginView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

public class LoginPresenter extends SimplePresenter<Profile, LoginView> {
	AccountManager account;

	public LoginPresenter(Bus bus, AccountManager account) {
		super(bus);
		this.account = account;
	}

	@Override public void execute(int tag) {
		if (getView() == null) return;

		String email = getView().getEmail();
		String password = getView().getPassword();

		if (email.isEmpty() || password.isEmpty())
			account.login("jmb@gmail.com", "jmb");
		else
			account.login(email, password);
	}

	@Subscribe public void getLoggedInEvent(AuthorizedEvent e) {
		Log.d(TAG, "getLoggedInEvent: " + e);
		if (e.getStatus() == Event.SUCCESS)
			account.getProfile();
	}

	@Subscribe public void getProfileEvent(ProfileFetchedEvent e) {
		if (getView() == null) return;

		if (e.getStatus() == Event.SUCCESS) {
			getView().setData(e.getProfile());
			getView().showSuccess();
		}
	}
}
