package com.joebruckner.whoknows.presenters;

import com.joebruckner.whoknows.ui.Post.PostSummaryView;

public interface PostSummaryPresenter {
	void attachView(PostSummaryView view);
	void detachView();
	void fetchPost(String id);
}
