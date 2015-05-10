package com.joebruckner.whoknows.presenters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.joebruckner.whoknows.ui.JoinedFragment;
import com.joebruckner.whoknows.ui.NearbyFragment;
import com.joebruckner.whoknows.ui.PostedFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

	int numPages = 3;

	public ViewPagerAdapter(FragmentManager manager) {
		super(manager);
	}

	@Override public Fragment getItem(int position) {
		switch(position) {
			case 0:
				return NearbyFragment.newInstance();
			case 1:
				return JoinedFragment.newInstance();
			case 2:
				return PostedFragment.newInstance();
			default:
				return null;
		}
	}

	@Override public CharSequence getPageTitle(int position) {
		switch(position) {
			case 0:
				return "Nearby";
			case 1:
				return "Joined";
			case 2:
				return "Posted";
			default:
				return null;
		}
	}

	@Override public int getCount() {
		return numPages;
	}
}
