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

	protected final String TAG = this.getClass().getSimpleName();

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		setHasOptionsMenu(true);
		((BaseActivity) activity).inject(this);
	}

	@Nullable @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		return inflater.inflate(getLayout(), container, false);
	}

	public void setupLayout() {
		ButterKnife.bind(this, getView());
	}

	@Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		setupLayout();
		super.onViewCreated(view, savedInstanceState);
	}

	public abstract int getLayout();
	public abstract void sendEvent(int tag);
}
