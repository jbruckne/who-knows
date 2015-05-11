package com.joebruckner.whoknows.ui;

import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseActivity;
import com.joebruckner.whoknows.models.logic.Beacon;
import com.joebruckner.whoknows.utilities.LocationApi;
import com.joebruckner.whoknows.utilities.WhoKnowsApi;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.Date;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class NewBeaconActivity extends BaseActivity {
	@InjectView(R.id.title) EditText editTitle;
	@InjectView(R.id.description) EditText editDescription;
	@InjectView(R.id.contact_info) EditText editContactInfo;
	@InjectView(R.id.show_location_radio) RadioButton showLocation;
	@InjectView(R.id.post_as_anonymous_radio) RadioButton postAnonymous;
	@InjectView(R.id.post) Button post;
	@InjectView(R.id.cancel) Button cancel;
	@Inject LocationApi locationApi;
	@Inject WhoKnowsApi whoKnowsApi;
	@Inject Bus bus;

	String title;
	String description;
	String contactInfo;
	String name;
	Location location = null;

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
		this.location = location;
		newBeacon();
	}

	private void newBeacon() {
		title = editTitle.getText().toString();
		description = editDescription.getText().toString();
		contactInfo = editContactInfo.getText().toString();
		name = postAnonymous.isChecked() ? "Anon" : "Joe";
		whoKnowsApi.put( new Beacon(
				title, name,
				new Date().toString(), contactInfo,
				description, location));
		finish();
	}
}
