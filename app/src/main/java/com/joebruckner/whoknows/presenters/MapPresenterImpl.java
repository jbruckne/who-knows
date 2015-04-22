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

public class MapPresenterImpl implements MapPresenter, OnMapReadyCallback, LocationListener {
    private final LocationManager locationManager;
    private final Activity activity;
    public GoogleMap map;

    public MapPresenterImpl(LocationManager locationManager, Activity activity) {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        this.locationManager = locationManager;
        this.activity = activity;
    }

    @Override
    public void setLocalMap(MapView mapView) {
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;
        MapsInitializer.initialize(activity);
    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng local = new LatLng(location.getLatitude(), location.getLongitude());
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(local, 15));
        map.addMarker(new MarkerOptions()
                .title("Current Location")
                .position(local));
        locationManager.removeUpdates(this);
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
