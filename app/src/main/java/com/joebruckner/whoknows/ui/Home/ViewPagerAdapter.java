package com.joebruckner.whoknows.ui.Home;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

	int numPages = 4;
	Activity activity;

	public ViewPagerAdapter(FragmentManager manager, Activity activity) {
		super(manager);
		this.activity = activity;
	}

	@Override public Fragment getItem(int position) {
		switch(position) {
			case 0:
				return BeaconListFragment.newInstance(0);
			case 1:
				return MapViewFragment.newInstance();
			case 2:
				return ChatFragment.newInstance();
			case 3:
				return ProfileFragment.newInstance();
			default:
				return null;
		}
	}

	@Override public CharSequence getPageTitle(int position) {
		switch(position) {
			case 0:
				return "Nearby";
			case 1:
				return "Map";
			case 2:
				return "Chat";
			case 3:
				return "Profile";
			default:
				return null;
		}
	}

	@Override public int getCount() {
		return numPages;
	}
}
