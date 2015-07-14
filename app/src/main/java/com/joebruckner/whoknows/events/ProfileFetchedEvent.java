package com.joebruckner.whoknows.events;

import com.joebruckner.whoknows.models.Profile;

public class ProfileFetchedEvent extends Event {
	Profile profile;

	public ProfileFetchedEvent(Profile profile) {
		this.status = Event.SUCCESS;
		this.error = 0;
		this.profile = profile;
	}

	public ProfileFetchedEvent(int error) {
		this.status = Event.FAILED;
		this.error = error;
		this.profile = null;
	}

	public Profile getProfile() {
		return profile;
	}
}
