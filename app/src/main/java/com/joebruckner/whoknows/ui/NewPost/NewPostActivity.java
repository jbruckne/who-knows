package com.joebruckner.whoknows.ui.newPost;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseActivity;
import com.joebruckner.whoknows.modules.NewPostModule;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewPostActivity extends BaseActivity {
	@Bind(R.id.toolbar) Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_post);
		ButterKnife.bind(this);
		setToolbar();
		if (savedInstanceState == null)
			setLayout();
	}

	private void setToolbar() {
		toolbar.setTitle("New Post");
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		if (actionBar != null) {
			actionBar.setHomeButtonEnabled(true);
			actionBar.setDisplayHomeAsUpEnabled(true);
			actionBar.setDefaultDisplayHomeAsUpEnabled(true);
		}
	}

	private void setLayout() {
		getSupportFragmentManager().beginTransaction()
				.add(R.id.container, NewPostFragment.newInstance())
				.commit();
	}

	@Override protected Object[] getModules() {
		return new Object[] { new NewPostModule(this) };
	}
}
