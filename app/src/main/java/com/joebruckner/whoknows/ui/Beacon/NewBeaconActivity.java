package com.joebruckner.whoknows.ui.Beacon;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;
import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseActivity;
import com.joebruckner.whoknows.models.Beacon;
import com.joebruckner.whoknows.utilities.AccountApi;
import com.joebruckner.whoknows.utilities.AppApi;
import com.joebruckner.whoknows.utilities.LocationApi;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.Date;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class NewBeaconActivity extends BaseActivity {
	@InjectView(R.id.toolbar) Toolbar toolbar;
	@InjectView(R.id.title) EditText editTitle;
	@InjectView(R.id.description) EditText editDescription;
	@InjectView(R.id.contact_info) EditText editContactInfo;
	@InjectView(R.id.location) CheckBox showLocation;
	@InjectView(R.id.name) CheckBox postAnonymous;
	@Inject LocationApi locationApi;
	@Inject AccountApi accountApi;
	@Inject AppApi appApi;
	@Inject Bus bus;

	String title;
	String description;
	String contactInfo;
	Date date;
	String name;
	LatLng location = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_beacon);
		ButterKnife.inject(this);
		bus.register(this);
		toolbar.setTitle("New Beacon");
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setHomeButtonEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setDefaultDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_new_beacon, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case R.id.action_post:
				if (showLocation.isChecked()) locationApi.getLocation();
				else newBeacon();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Subscribe public void getLocation(Location location) {
		this.location = new LatLng(location.getLatitude(), location.getLongitude());
		newBeacon();
	}

	private void newBeacon() {
		title = editTitle.getText().toString();
		description = editDescription.getText().toString();
		contactInfo = editContactInfo.getText().toString();
		date = new Date();
		name = postAnonymous.isChecked() ? "Anon" : accountApi.getName();
		appApi.put(new Beacon(title, name, date, contactInfo, description, location));
		finish();
	}
}
