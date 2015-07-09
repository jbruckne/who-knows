package com.joebruckner.whoknows.ui.Home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseActivity;
import com.joebruckner.whoknows.modules.HomeModule;
import com.joebruckner.whoknows.ui.NewPost.NewPostActivity;
import com.joebruckner.whoknows.ui.StartUp.LoginActivity;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		ButterKnife.bind(this);
		setupToolbar();
		setupTabs();
		setupFab();
	}

	protected void setupToolbar() {
		setSupportActionBar(toolbar);
	}

	protected void setupTabs() {
		final ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), this);
		pager.setAdapter(adapter);

		for (int i = 0; i < adapter.getCount(); i++) {
			tabLayout.addTab(tabLayout.newTab()
					.setText(adapter.getPageTitle(i)));
		}
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

		pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
		tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override public void onTabSelected(TabLayout.Tab tab) {
				pager.setCurrentItem(tab.getPosition());
			}

			@Override public void onTabUnselected(TabLayout.Tab tab) {

			}

			@Override public void onTabReselected(TabLayout.Tab tab) {

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
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
		finish();
	}
}
