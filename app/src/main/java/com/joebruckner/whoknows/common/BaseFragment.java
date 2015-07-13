package com.joebruckner.whoknows.common;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;

public class BaseFragment extends Fragment {

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((BaseActivity) activity).inject(this);
	}

	@Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		ButterKnife.bind(this, view);
		super.onViewCreated(view, savedInstanceState);
	}
}
