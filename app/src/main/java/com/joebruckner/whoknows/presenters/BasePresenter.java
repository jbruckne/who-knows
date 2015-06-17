package com.joebruckner.whoknows.presenters;

public interface BasePresenter<T> {
	void attachView(BaseView<T> view);
	void detachView();
}
