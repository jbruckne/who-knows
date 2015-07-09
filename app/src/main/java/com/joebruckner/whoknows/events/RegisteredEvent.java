package com.joebruckner.whoknows.events;

public class RegisteredEvent extends Event {

	public RegisteredEvent(int status) {
		this(status, Event.SUCCESS);
	}

	public RegisteredEvent(int status, int error) {
		this.status = status;
		this.error = error;
	}
}
