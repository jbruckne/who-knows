package com.joebruckner.whoknows.ui.StartUp;

public interface AuthView {
	void showLoading();
	void showSuccess();
	void showError(int code);
	void advanceToHome();
}
