package com.joebruckner.whoknows.ui.Beacon;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joebruckner.whoknows.R;

public class AttendersListFragment extends Fragment {


	public AttendersListFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_attenders_list, container, false);
	}


}
