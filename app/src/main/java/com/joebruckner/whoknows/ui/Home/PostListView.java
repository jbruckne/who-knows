package com.joebruckner.whoknows.ui.Home;

import com.joebruckner.whoknows.models.Post;

import java.util.List;

public interface PostListView {
	void showLoading();
	void showContent();
	void showEmpty();
	void showError();
	void setData(List<Post> posts);
}
