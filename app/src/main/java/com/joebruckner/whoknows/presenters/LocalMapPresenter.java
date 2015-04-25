package com.joebruckner.whoknows.presenters;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class LocalMapPresenter implements MapPresenter, OnMapReadyCallback, LocationListener {
	private final LocationManager locationManager;
	private final Activity activity;
	public Location location;
	public GoogleMap map;

	public LocalMapPresenter(LocationManager locationManager, Activity activity) {
		this.locationManager = locationManager;
		this.activity = activity;
	}

	@Override
	public void setMap(MapView mapView) {
		mapView.getMapAsync(this);
	}

	@Override
	public void onMapReady(GoogleMap map) {
		this.map = map;
		MapsInitializer.initialize(activity);
		initlocation();
	}

	private void initlocation() {
		boolean networkEnabled = locationManager
				.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		if (networkEnabled) {
			location = locationManager
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		}
		if (location == null)
			locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
		else setMapLocation();
	}

	@Override
	public void onLocationChanged(Location location) {
		this.location = location;
		locationManager.removeUpdates(this);
		setMapLocation();
	}

	private void setMapLocation() {
		LatLng local = new LatLng(location.getLatitude(), location.getLongitude());
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(local, 15));
		map.addMarker(new MarkerOptions()
				.title("Current Location")
				.position(local));
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	@Override
	public void onProviderEnabled(String provider) {
	}

	@Override
	public void onProviderDisabled(String provider) {
	}
}
