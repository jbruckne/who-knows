package com.joebruckner.whoknows.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseActivity;
import com.joebruckner.whoknows.managers.AccountManager;
import com.joebruckner.whoknows.managers.DatabaseManager;
import com.joebruckner.whoknows.modules.HomeModule;
import com.joebruckner.whoknows.ui.newPost.NewPostActivity;
import com.joebruckner.whoknows.ui.startUp.AuthActivity;
import com.joebruckner.whoknows.ui.widgets.SimpleOnTabSelectedListener;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity {
	@Bind(R.id.coordinator) CoordinatorLayout coordinator;
	@Bind(R.id.toolbar) Toolbar toolbar;
	@Bind(R.id.tab_layout) TabLayout tabLayout;
	@Bind(R.id.fab) FloatingActionButton fab;
	@Bind(R.id.pager) ViewPager pager;
	@Inject Activity activity;
	@Inject AccountManager accountManager;
	@Inject DatabaseManager databaseManager;

	ViewPagerAdapter pagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		Log.d("Home", "" + this);
		checkAuth();
		ButterKnife.bind(this);
		setupToolbar();
		setupTabs();
		setupFab();
	}

	protected void checkAuth() {
		if (!accountManager.isLoggedIn()) {
			startActivity(new Intent(this, AuthActivity.class));
			finish();
		}
	}

	protected void setupToolbar() {
		setSupportActionBar(toolbar);
	}

	protected void setupTabs() {
		pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
		pager.setAdapter(pagerAdapter);
		for (int i = 0; i < pagerAdapter.getCount(); i++)
			tabLayout.addTab(tabLayout.newTab().setText(pagerAdapter.getPageTitle(i)));
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
		pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
		tabLayout.setOnTabSelectedListener(new SimpleOnTabSelectedListener() {
			@Override public void onTabSelected(TabLayout.Tab tab) {
				pager.setCurrentItem(tab.getPosition());
			}
		});
	}

	protected void setupFab() {
		fab.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				startActivity(new Intent(activity, NewPostActivity.class));
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
			case R.id.action_refresh:
				refresh(item);
				return true;
			case R.id.action_logout:
				logout();
				return true;
			case R.id.action_test:
				test();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override protected Object[] getModules() {
		return new Object[] { new HomeModule(this) };
	}

	private void refresh(MenuItem item) {
		pagerAdapter.getItem(pager.getCurrentItem()).onOptionsItemSelected(item);
	}

	private void logout() {
		accountManager.logout();
		checkAuth();
	}

	private void test() {
		databaseManager.getOffer("lol");
	}
}
