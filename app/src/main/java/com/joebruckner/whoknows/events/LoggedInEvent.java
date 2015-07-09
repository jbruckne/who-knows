package com.joebruckner.whoknows.events;

import com.joebruckner.whoknows.models.Profile;

public class LoggedInEvent extends Event {
	Profile user;

	public LoggedInEvent(int status, Profile user) {
		this.status = Event.SUCCESS;
		this.error = 0;
		this.user = user;
	}

	public LoggedInEvent(int status, int error) {
		this.status = status;
		this.error = error;
		this.user = null;
	}
}
