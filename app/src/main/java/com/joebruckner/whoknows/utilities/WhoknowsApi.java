package com.joebruckner.whoknows.utilities;

import com.joebruckner.whoknows.models.logic.Beacon;

import java.util.List;

public interface WhoKnowsApi {
	List<Beacon> getAllBeacons();
	List<Beacon> getActiveBeacons();
	List<Beacon> getPostedBeacons();
	void put(Beacon beacon);
}
