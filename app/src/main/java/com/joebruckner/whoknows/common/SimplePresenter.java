package com.joebruckner.whoknows.common;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.squareup.otto.Bus;

public abstract class SimplePresenter<Data, V extends SimpleView<Data>> {
	private V view;
	private Bus bus;
	protected final String TAG = this.getClass().getSimpleName();

	public static final int ACTION_NONE = 0;
	public static final int ACTION_OFFER_HELP = 101;

	public SimplePresenter(@NonNull Bus bus) {
		this.bus = bus;
	}

	public void attachView(@NonNull V view) {
		this.view = view;
		bus.register(this);
	}

	public void detachView() {
		bus.unregister(this);
		this.view = null;
	}

	@Nullable protected V getView() {
		return view;
	}

	@NonNull protected Bus getBus() {
		return bus;
	}

	public void execute() {
		execute(ACTION_NONE);
	}

	public abstract void execute(int tag);
}
