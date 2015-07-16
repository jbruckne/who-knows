package com.joebruckner.whoknows.common;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((BaseActivity) activity).inject(this);
	}

	@Nullable @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(getLayout(), container, false);
		ButterKnife.bind(this, view);
		return view;
	}

	@Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		ButterKnife.bind(this, view);
		super.onViewCreated(view, savedInstanceState);
	}

	public abstract int getLayout();
}
