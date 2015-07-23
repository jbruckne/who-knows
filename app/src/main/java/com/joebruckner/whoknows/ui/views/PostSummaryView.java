package com.joebruckner.whoknows.ui.views;

import com.joebruckner.whoknows.common.SimpleView;
import com.joebruckner.whoknows.models.Post;

public interface PostSummaryView extends SimpleView<Post> {
	String getPostId();
	void showSent();
}
