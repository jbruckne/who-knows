package com.joebruckner.whoknows.utilities;

import com.joebruckner.whoknows.models.Beacon;

import java.util.List;

public interface AppApi {
	List<Beacon> getNearbyBeacons();
	List<Beacon> getActiveBeacons();
	List<Beacon> getPostedBeacons();
	void put(Beacon beacon);
	Beacon get(long id);
}
