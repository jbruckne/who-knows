package com.joebruckner.whoknows.utilities;

import com.joebruckner.whoknows.models.logic.Beacon;

import java.util.List;

public interface WhoknowsApi {
	List<Beacon> getBeacons();
	void put(Beacon beacon);
}
