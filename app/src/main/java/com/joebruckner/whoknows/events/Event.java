package com.joebruckner.whoknows.events;

import java.util.UUID;

public class Event {

	public static final int SUCCESS = 0;
	public static final int FAILED = 1;

	public static final int REQUEST_ERROR = 401;
	public static final int DOES_NOT_EXIST = 402;

	protected long id;
	protected int status;
	protected int error;

	public Event() {
		this.id = UUID.randomUUID().getMostSignificantBits();
	}

	public int getStatus() {
		return this.status;
	}
	public int getError() {
		return this.error;
	}
	public long getId() {
		return id;
	}
}
