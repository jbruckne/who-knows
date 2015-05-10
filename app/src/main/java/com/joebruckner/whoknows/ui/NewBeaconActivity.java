package com.joebruckner.whoknows.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseActivity;
import com.joebruckner.whoknows.models.logic.Beacon;
import com.joebruckner.whoknows.utilities.WhoKnowsApi;
import com.melnykov.fab.FloatingActionButton;
import com.squareup.otto.Bus;

import java.util.Date;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class NewBeaconActivity extends BaseActivity {
	@InjectView(R.id.toolbar) Toolbar toolbar;
	@InjectView(R.id.fab) FloatingActionButton fab;
	@InjectView(R.id.title) EditText title;
	@InjectView(R.id.description) EditText description;
	@Inject WhoKnowsApi api;
	@Inject Bus bus;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_beacon);
		ButterKnife.inject(this);
		initToolbar();
		initFab();
	}

	protected void initToolbar() {
		setSupportActionBar(toolbar);
		if (getSupportActionBar() != null) {
			getSupportActionBar().setTitle("");
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	protected void initFab() {
		fab.setImageResource(R.drawable.ic_action_done);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				newBeacon();
			}
		});
	}

	private void newBeacon() {
		Beacon beacon = new Beacon(
				title.getText().toString(), "Joe", new Date().toString(),
				"(555) 123-4567", description.toString());
		api.put(beacon);
		finish();
	}
}
