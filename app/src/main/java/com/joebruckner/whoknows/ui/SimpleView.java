package com.joebruckner.whoknows.ui;

public interface SimpleView<M> {
	void showContent();
	void showLoading();
	void showError();
	void setData(M data);
}
