package com.joebruckner.whoknows.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.models.logic.Beacon;
import com.joebruckner.whoknows.models.view.BeaconViewHolder;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class BeaconDetailActivity extends ActionBarActivity {
	@InjectView(R.id.toolbar) Toolbar toolbar;

	Beacon beacon;
	BeaconViewHolder holder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_beacon_detail);
		ButterKnife.inject(this);
		initToolbar();

		beacon = getIntent().getParcelableExtra("Beacon");
		Log.d("Detail", beacon.getTitle());

		initDetails();
	}

	private void initToolbar() {
		toolbar.setTitle("");
		toolbar.setAlpha(0f);
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setHomeButtonEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setDefaultDisplayHomeAsUpEnabled(true);
		}
	}

	private void initDetails() {
		holder = new BeaconViewHolder(getWindow().getDecorView().getRootView());
		holder.getTitleView().setText(beacon.getTitle());
		holder.getNameView().setText(beacon.getName());
		SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
		holder.getDateView().setText(format.format(beacon.getDate()));
		holder.getContactInfo().setText(beacon.getContactInfo());
		holder.getDescriptionView().setText(beacon.getDescription());
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
