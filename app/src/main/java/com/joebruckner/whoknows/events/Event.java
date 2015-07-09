package com.joebruckner.whoknows.events;

public class Event {

	public static final int SUCCESS = 0;
	public static final int FAILED = 1;

	protected int status;
	protected int error;

	public int getStatus() {
		return this.status;
	}
	public int getError() {
		return this.error;
	}
}
