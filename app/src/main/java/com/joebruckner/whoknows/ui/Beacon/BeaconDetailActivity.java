package com.joebruckner.whoknows.ui.Beacon;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseActivity;
import com.joebruckner.whoknows.models.Beacon;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class BeaconDetailActivity extends BaseActivity {
	@InjectView(R.id.toolbar) Toolbar toolbar;
	@InjectView(R.id.frame) FrameLayout frame;
	@InjectView(R.id.fab) FloatingActionButton fab;

	Beacon beacon;

	public static String BEACON_ID = "ID";
	public static String BEACON_TITLE = "TITLE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beacon_detail);
		ButterKnife.inject(this);

		setupToolbar();
		if (savedInstanceState == null) setupFrame();
		setupFab();
	}

	private void setupToolbar() {
		toolbar.setTitle(getIntent().getStringExtra(BEACON_TITLE));
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setHomeButtonEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setDefaultDisplayHomeAsUpEnabled(true);
		}
	}

	private void setupFrame() {
		OverviewFragment overviewFragment = OverviewFragment.newInstant();
		getSupportFragmentManager()
				.beginTransaction().add(R.id.frame, overviewFragment).commit();
	}

	private void setupFab() {
		// TODO
	}
}
