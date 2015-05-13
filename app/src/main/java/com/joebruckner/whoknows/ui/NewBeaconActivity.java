package com.joebruckner.whoknows.ui;

import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;
import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseActivity;
import com.joebruckner.whoknows.models.logic.Beacon;
import com.joebruckner.whoknows.utilities.AccountApi;
import com.joebruckner.whoknows.utilities.AppApi;
import com.joebruckner.whoknows.utilities.LocationApi;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class NewBeaconActivity extends BaseActivity {
	@InjectView(R.id.title) EditText editTitle;
	@InjectView(R.id.description) EditText editDescription;
	@InjectView(R.id.contact_info) EditText editContactInfo;
	@InjectView(R.id.show_location_radio) CheckBox showLocation;
	@InjectView(R.id.post_as_anonymous_radio) CheckBox postAnonymous;
	@InjectView(R.id.post) Button post;
	@InjectView(R.id.cancel) Button cancel;
	@Inject LocationApi locationApi;
	@Inject AccountApi accountApi;
	@Inject AppApi appApi;
	@Inject Bus bus;

	String title;
	String description;
	String contactInfo;
	String date;
	String name;
	LatLng location = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_beacon);
		ButterKnife.inject(this);
		bus.register(this);
		post.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				if (showLocation.isChecked()) locationApi.getLocation();
				else newBeacon();
			}
		});
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				finish();
			}
		});
	}

	@Subscribe public void getLocation(Location location) {
		this.location = new LatLng(location.getLatitude(), location.getLongitude());
		newBeacon();
	}

	private void newBeacon() {
		title = editTitle.getText().toString();
		description = editDescription.getText().toString();
		contactInfo = editContactInfo.getText().toString();
		SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
		date = format.format(new Date());
		name = postAnonymous.isChecked() ? "Anon" : accountApi.getName();
		appApi.put(new Beacon(title, name, date, contactInfo, description, location));
		finish();
	}
}
