package com.joebruckner.whoknows.ui;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.MapView;
import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseFragment;
import com.joebruckner.whoknows.presenters.MapPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LocalMapFragment extends BaseFragment {
	@InjectView ( R.id.map_view ) MapView mapView;
	@Inject MapPresenter presenter;

	public LocalMapFragment() {
	}

	public static LocalMapFragment newInstance() {
		return new LocalMapFragment();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_local_map, container, false);
		ButterKnife.inject(this, view);
		mapView.onCreate(savedInstanceState);
		presenter.setLocalMap(mapView);
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		mapView.onResume();
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mapView.onDestroy();
	}
}
