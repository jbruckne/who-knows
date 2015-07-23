package com.joebruckner.whoknows.ui.home;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

	int numPages = 3;
	Activity activity;

	Fragment nearby = PostListFragment.newInstance(0);
	Fragment joined = PostListFragment.newInstance(0);
	Fragment account = ProfileFragment.newInstance();

	public ViewPagerAdapter(FragmentManager manager, Activity activity) {
		super(manager);
		this.activity = activity;
	}

	@Override public Fragment getItem(int position) {
		switch(position) {
			case 0:
				return nearby;
			case 1:
				return joined;
			case 2:
				return account;
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
