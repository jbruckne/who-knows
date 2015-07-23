package com.joebruckner.whoknows.presenters;

import android.util.Log;

import com.joebruckner.whoknows.events.AuthorizedEvent;
import com.joebruckner.whoknows.events.Event;
import com.joebruckner.whoknows.events.ProfileFetchedEvent;
import com.joebruckner.whoknows.managers.AccountManager;
import com.joebruckner.whoknows.models.Profile;
import com.joebruckner.whoknows.common.SimplePresenter;
import com.joebruckner.whoknows.ui.views.RegisterView;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

public class RegisterPresenter extends SimplePresenter<Profile, RegisterView> {
	AccountManager account;

	public RegisterPresenter(Bus bus, AccountManager account) {
		super(bus);
		this.account = account;
	}

	@Override public void execute(int tag) {
		if (getView() == null) return;

		String name = getView().getName();
		String email = getView().getEmail();
		String password = getView().getPassword();
		String check = getView().getPasswordCheck();

		if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
			getView().showInvalid("Invalid information: All forms must be filled out");
		} else if (!password.equals(check)) {
			getView().showInvalid("Invalid information: Passwords must match");
		} else {
			account.register(name, email, password);
		}
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
