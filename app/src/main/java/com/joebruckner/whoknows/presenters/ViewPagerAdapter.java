package com.joebruckner.whoknows.presenters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.joebruckner.whoknows.ui.Home.BeaconListFragment;
import com.joebruckner.whoknows.ui.Home.ChatFragment;
import com.joebruckner.whoknows.ui.Home.MapViewFragment;
import com.joebruckner.whoknows.ui.Home.ProfileFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

	int numPages = 4;

	public ViewPagerAdapter(FragmentManager manager) {
		super(manager);
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
