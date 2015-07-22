package com.joebruckner.whoknows.ui;

import android.support.annotation.NonNull;

public interface SimpleView<Data> {
	void showContent();
	void showLoading();
	void showError();
	void setData(@NonNull Data data);
}
