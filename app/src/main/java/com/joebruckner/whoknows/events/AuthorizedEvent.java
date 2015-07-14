package com.joebruckner.whoknows.events;

public class AuthorizedEvent extends Event {

	public AuthorizedEvent() {
		this.status = Event.SUCCESS;
		this.error = 0;
	}

	public AuthorizedEvent(int error) {
		this.status = Event.FAILED;
		this.error = error;
	}
}
