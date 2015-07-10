package com.joebruckner.whoknows.ui.Home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseActivity;
import com.joebruckner.whoknows.modules.HomeModule;
import com.joebruckner.whoknows.network.AccountApi;
import com.joebruckner.whoknows.ui.NewPost.NewPostActivity;
import com.joebruckner.whoknows.ui.StartUp.LoginActivity;
import com.joebruckner.whoknows.ui.Widgets.SimpleOnTabSelectedListener;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity {
	@Bind(R.id.toolbar) Toolbar toolbar;
	@Bind(R.id.tab_layout) TabLayout tabLayout;
	@Bind(R.id.fab) FloatingActionButton fab;
	@Bind(R.id.pager) ViewPager pager;
	@Inject Activity activity;
	@Inject AccountApi accountApi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		ButterKnife.bind(this);
		checkAuth();
		setupToolbar();
		setupTabs();
		setupFab();
	}

	protected void checkAuth() {
		if (!accountApi.isLoggedIn()) {
			startActivity(new Intent(this, LoginActivity.class));
			finish();
		}
	}

	protected void setupToolbar() {
		setSupportActionBar(toolbar);
	}

	protected void setupTabs() {
		final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
		pager.setAdapter(adapter);
		for (int i = 0; i < adapter.getCount(); i++)
			tabLayout.addTab(tabLayout.newTab().setText(adapter.getPageTitle(i)));
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
			case R.id.action_test:
				test();
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override protected List<Object> getModules() {
		return Arrays.<Object>asList(new HomeModule(this));
	}

	private void test() {
		Log.d("Home", "No Current Test");
		Toast.makeText(this, "No Current Test", Toast.LENGTH_SHORT).show();
	}
}
