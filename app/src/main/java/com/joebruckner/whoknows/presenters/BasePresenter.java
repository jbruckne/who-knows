package com.joebruckner.whoknows.presenters;

public abstract class BasePresenter<T> {
	BaseView<T> view;

	public static final String ID = "ID";
	public static final String TYPE = "TYPE";

	public void attachView(BaseView<T> view) {
		this.view = view;
	}
	public void detachView() {
		this.view = null;
	}
}
