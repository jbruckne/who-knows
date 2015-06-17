package com.joebruckner.whoknows.utilities;

import com.joebruckner.whoknows.models.Beacon;

import java.util.List;

public interface AppApi {
	List<Beacon> getNearbyBeacons();
	List<Beacon> getJoinedBeacons();
	List<Beacon> getPostedBeacons();
	void put(Beacon beacon);
	Beacon get(long id);
}
