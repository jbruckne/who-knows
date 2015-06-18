package com.joebruckner.whoknows.presenters;

public interface BaseView<T> {
	void showLoading();
	void showContent();
	void showError();
	void setData(T data);
}
