package com.joebruckner.whoknows.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joebruckner.whoknows.R;

public class SettingsFragment extends Fragment {

	public static SettingsFragment newInstance() {
		return new SettingsFragment();
	}

	public SettingsFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_settings, container, false);
		//view.setFitsSystemWindows(true);
		return view;
	}


}
