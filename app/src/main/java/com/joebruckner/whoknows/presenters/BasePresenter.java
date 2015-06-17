package com.joebruckner.whoknows.presenters;

public abstract class BasePresenter<T> {
	BaseView<T> view;

	public void attachView(BaseView<T> view) {
		this.view = view;
	}
	public void detachView() {
		this.view = null;
	}
}
