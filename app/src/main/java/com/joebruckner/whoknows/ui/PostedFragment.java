package com.joebruckner.whoknows.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joebruckner.whoknows.common.BaseFragment;

public class PostedFragment extends BaseFragment {

	public static PostedFragment newInstance() {
		return new PostedFragment();
	}

	public PostedFragment() {
		// Required Empty Constructor
	}

	@Nullable @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}
}
