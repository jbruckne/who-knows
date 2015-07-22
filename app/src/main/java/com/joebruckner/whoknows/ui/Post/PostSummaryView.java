package com.joebruckner.whoknows.ui.Post;

import com.joebruckner.whoknows.models.Post;

public interface PostSummaryView {
	void showLoading();
	void showSummary();
	void showError();
	void setData(Post post);
}
