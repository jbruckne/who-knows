package com.joebruckner.whoknows.presenters;

public interface BaseView<T extends Object> {
	void showLoading();
	void showContent();
	void showError();
	void setData(T data);
}
