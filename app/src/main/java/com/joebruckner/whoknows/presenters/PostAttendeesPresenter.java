package com.joebruckner.whoknows.presenters;

import com.joebruckner.whoknows.ui.Post.PostAttendeesView;

public interface PostAttendeesPresenter {
	void attachView(PostAttendeesView view);
	void detachView();
	void fetchAttendees(String id);
}
