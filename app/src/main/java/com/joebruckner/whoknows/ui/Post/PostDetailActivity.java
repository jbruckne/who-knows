package com.joebruckner.whoknows.ui.Post;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseActivity;
import com.joebruckner.whoknows.modules.PostModule;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostDetailActivity extends BaseActivity {
	@Bind(R.id.toolbar) Toolbar toolbar;
	@Bind(R.id.frame) FrameLayout frame;
	@Bind(R.id.fab) FloatingActionButton fab;

	public static String BEACON_ID = "ID";
	public static String BEACON_TITLE = "TITLE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post_detail);
		ButterKnife.bind(this);

		setupToolbar();
		if (savedInstanceState == null) setupFrame();
		setupFab();
	}

	private void setupToolbar() {
		toolbar.setTitle(getIntent().getStringExtra(BEACON_TITLE));
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setHomeButtonEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setDefaultDisplayHomeAsUpEnabled(true);
		}
	}

	private void setupFrame() {
		PostSummaryFragment postSummaryFragment = PostSummaryFragment.newInstant();
		getSupportFragmentManager()
				.beginTransaction()
				.add(R.id.frame, postSummaryFragment)
				.commit();
	}

	private void setupFab() {
		// TODO
	}

	@OnClick(R.id.view_attendees)
	public void onClick() {
		PostAttendeesFragment fragment = PostAttendeesFragment.newInstance();
		getSupportFragmentManager()
				.beginTransaction()
				.replace(R.id.frame, fragment)
				.addToBackStack(null)
				.commit();
	}

	@Override protected Object[] getModules() {
		return new Object[] { new PostModule(this) };
	}
}
