package com.joebruckner.whoknows.ui.Post;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseActivity;
import com.joebruckner.whoknows.modules.PostModule;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PostActivity extends BaseActivity {
	@Bind(R.id.coordinator) CoordinatorLayout coordinator;
	@Bind(R.id.toolbar) Toolbar toolbar;
	@Bind(R.id.frame) FrameLayout frame;

	public static String BEACON_ID = "ID";
	public static String BEACON_TITLE = "TITLE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_post);
		ButterKnife.bind(this);

		setupToolbar();
		if (savedInstanceState == null) setupFrame();
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
		getSupportFragmentManager().beginTransaction()
				.add(R.id.frame, PostSummaryFragment.newInstance())
				.commit();
	}

	@Override protected Object[] getModules() {
		return new Object[] { new PostModule(this) };
	}
}
