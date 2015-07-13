package com.joebruckner.whoknows.events;

import com.joebruckner.whoknows.models.Profile;

public class LoggedInEvent extends Event {
	Profile profile;

	public LoggedInEvent(int status, Profile profile) {
		this.status = Event.SUCCESS;
		this.error = 0;
		this.profile = profile;
	}

	public LoggedInEvent(int status, int error) {
		this.status = status;
		this.error = error;
		this.profile = null;
	}

	public Profile getProfile() {
		return profile;
	}
}
