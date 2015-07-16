package com.joebruckner.whoknows.presenters;

import com.joebruckner.whoknows.ui.SimpleView;

public interface NewPostPresenter {
	void attachView(SimpleView view);
	void detachView();
	void loadProfile();
	void sendPost(String title, String descripton, boolean location);
}
