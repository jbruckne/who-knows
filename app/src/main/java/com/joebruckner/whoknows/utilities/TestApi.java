package com.joebruckner.whoknows.utilities;

import android.util.Log;

import com.joebruckner.whoknows.models.logic.Beacon;

import java.util.ArrayList;
import java.util.List;

public class TestApi implements WhoknowsApi{
	List<Beacon> beacons;

	public TestApi() {
		this.beacons = new ArrayList<>();
	}

	@Override public List<Beacon> getBeacons() {
		if (beacons.isEmpty()) for (int i = 0; i < 15; i++)
			beacons.add(new Beacon("Beacon " + i));
		return beacons;
	}

	@Override public void put(Beacon beacon) {
		Log.d("TestApi", "Adding " + beacon.toString());
	}
}
