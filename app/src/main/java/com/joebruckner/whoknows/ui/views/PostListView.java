package com.joebruckner.whoknows.ui.views;

import com.joebruckner.whoknows.models.Post;
import com.joebruckner.whoknows.common.SimpleView;

import java.util.List;


public interface PostListView extends SimpleView<List<Post>> {
	int getFilter();
	void showEmpty();
	void refresh();
}
