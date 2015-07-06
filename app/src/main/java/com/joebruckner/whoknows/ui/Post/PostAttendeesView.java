package com.joebruckner.whoknows.ui.Post;

import java.util.List;

public interface PostAttendeesView {
	void showLoading();
	void showContent();
	void showError();
	void setAttendees(List<String> attendees);
}
