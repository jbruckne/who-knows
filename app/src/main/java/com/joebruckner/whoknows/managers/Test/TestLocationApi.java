package com.joebruckner.whoknows.managers.Test;

import android.app.Application;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.joebruckner.whoknows.managers.LocationApi;
import com.squareup.otto.Bus;

public class TestLocationApi implements LocationApi, LocationListener {
	LocationManager locationManager;
	Location location;
	Application app;
	Bus bus;

	public TestLocationApi(LocationManager locationManager, Bus bus, Application app) {
		this.locationManager = locationManager;
		this.app = app;
		this.bus = bus;
	}

	private void initLocation() {
		boolean networkEnabled = locationManager
				.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		if (networkEnabled) {
			location = locationManager
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		}
		if (location == null)
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		else bus.post(location);
	}

	public void getLocation() {
		if (location == null) initLocation();
		else bus.post(location);
	}

	@Override public void onLocationChanged(Location location) {
		this.location = location;
		locationManager.removeUpdates(this);
		bus.post(location);
	}

	@Override public void onStatusChanged(String provider, int status, Bundle extras) {

	}

	@Override public void onProviderEnabled(String provider) {

	}

	@Override public void onProviderDisabled(String provider) {

	}
}
