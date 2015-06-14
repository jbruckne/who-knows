package com.joebruckner.whoknows.ui.Beacon;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.models.Beacon;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class BeaconDetailActivity extends AppCompatActivity {
	@InjectView(R.id.toolbar) Toolbar toolbar;
	@InjectView(R.id.frame) FrameLayout frame;
	@InjectView(R.id.fab) FloatingActionButton fab;

	Beacon beacon;

	public static String BEACON_EXTRA = "Beacon";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beacon_detail);
		ButterKnife.inject(this);

		beacon = getIntent().getParcelableExtra("Beacon");
		Log.d("Detail", beacon.getTitle());

		setupToolbar();
		if (savedInstanceState == null) setupFrame();
		setupFab();
	}

	private void setupToolbar() {
		toolbar.setTitle(beacon.getTitle());
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setHomeButtonEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setDefaultDisplayHomeAsUpEnabled(true);
		}
	}

	private void setupFrame() {
		OverviewFragment overviewFragment = OverviewFragment.newInstant(beacon);
		getSupportFragmentManager()
				.beginTransaction().add(R.id.frame, overviewFragment).commit();
	}

	private void setupFab() {

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_beacon_detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			default: return super.onOptionsItemSelected(item);
		}
	}
}
