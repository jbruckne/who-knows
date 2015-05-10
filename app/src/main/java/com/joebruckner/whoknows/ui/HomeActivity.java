package com.joebruckner.whoknows.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseActivity;
import com.joebruckner.whoknows.modules.qualifiers.ForActivity;
import com.joebruckner.whoknows.presenters.ViewPagerAdapter;
import com.melnykov.fab.FloatingActionButton;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class HomeActivity extends BaseActivity implements MaterialTabListener{
	@InjectView(R.id.toolbar) Toolbar toolbar;
	@InjectView(R.id.tabHost) MaterialTabHost tabHost;
	@InjectView(R.id.fab) FloatingActionButton fab;
	@InjectView(R.id.pager) ViewPager pager;
	@Inject @ForActivity Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		ButterKnife.inject(this);
		initToolbar();
		initPager();
		initFab();
	}

	protected void initToolbar() {
		setSupportActionBar(toolbar);
	}

	protected void initPager() {
		PagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
		pager.setAdapter(adapter);
		pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override public void onPageSelected(int position) {
				tabHost.setSelectedNavigationItem(position);
			}
		});



		for (int i = 0; i < adapter.getCount(); i++) {
			tabHost.addTab(tabHost.newTab()
					.setText(adapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	protected void initFab() {
		fab.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				startActivity(new Intent(context, NewBeaconActivity.class));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
			case R.id.action_settings:
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override public void onTabSelected(MaterialTab tab) {
		pager.setCurrentItem(tab.getPosition());
	}

	@Override public void onTabReselected(MaterialTab tab) {

	}

	@Override public void onTabUnselected(MaterialTab tab) {

	}
}
