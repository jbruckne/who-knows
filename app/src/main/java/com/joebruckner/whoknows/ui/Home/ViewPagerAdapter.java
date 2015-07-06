package com.joebruckner.whoknows.ui.Home;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

	int numPages = 3;
	Activity activity;


	public ViewPagerAdapter(FragmentManager manager, Activity activity) {
		super(manager);
		this.activity = activity;
	}

	@Override public Fragment getItem(int position) {
		switch(position) {
			case 0:
				return PostListFragment.newInstance(0);
			case 1:
				return PostListFragment.newInstance(0);
			case 2:
				return AccountFragment.newInstance();
			default:
				return new Fragment();
		}
	}

	@Override public CharSequence getPageTitle(int position) {
		switch(position) {
			case 0:
				return "Nearby";
			case 1:
				return "Joined";
			case 2:
				return "Account";
			default:
				return "";
		}
	}

	@Override public int getCount() {
		return numPages;
	}
}
