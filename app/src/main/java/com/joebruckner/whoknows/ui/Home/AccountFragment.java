package com.joebruckner.whoknows.ui.Home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joebruckner.whoknows.R;

public class AccountFragment extends android.support.v4.app.Fragment {

	public static AccountFragment newInstance() {
		return new AccountFragment();
	}

	public AccountFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_account, container, false);
	}


}
