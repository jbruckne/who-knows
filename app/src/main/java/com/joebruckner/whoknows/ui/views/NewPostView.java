package com.joebruckner.whoknows.ui.views;

import com.joebruckner.whoknows.common.SimpleView;
import com.joebruckner.whoknows.models.Post;

public interface NewPostView extends SimpleView<Post> {
	String getTitle();
	String getDescription();
	boolean getLocation();
	void showSuccess();
}
