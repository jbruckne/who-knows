package com.joebruckner.whoknows.common;

import android.support.annotation.NonNull;

public interface SimpleView<Data> {
	void showContent();
	void showLoading();
	void showError();
	void setData(@NonNull Data data);
}
