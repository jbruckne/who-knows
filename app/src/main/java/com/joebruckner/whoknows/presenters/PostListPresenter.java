package com.joebruckner.whoknows.presenters;

import com.joebruckner.whoknows.ui.Home.PostListView;

public interface PostListPresenter {
	void attachView(PostListView view);
	void detachView();
	void fetchPosts(int filter);
}
