package com.joebruckner.whoknows.ui.NewPost;

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
import com.joebruckner.whoknows.network.AppApi;
import com.joebruckner.whoknows.network.LocationApi;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Bind;

public class NewPostActivity extends BaseActivity {
	@Bind(R.id.toolbar) Toolbar toolbar;
	@Bind(R.id.title) EditText editTitle;
	@Bind(R.id.description) EditText editDescription;
	@Bind(R.id.contact_info) EditText editContactInfo;
	@Bind(R.id.location) CheckBox showLocation;
	@Inject LocationApi locationApi;
	@Inject AppApi appApi;
	@Inject Bus bus;

	String title;
	String description;
	String contactInfo;
	LatLng location = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_post);
		ButterKnife.bind(this);
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
		appApi.put(title, description, contactInfo);
		finish();
	}
}
