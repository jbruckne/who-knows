package com.joebruckner.whoknows.presenters;

import com.joebruckner.whoknows.ui.Post.PostSummaryView;

public interface PostSummaryPresenter {
	void attachView(PostSummaryView view);
	void detachView();
	void getPosts(String id);
	void offerHelp(String id, String recipient);
}
