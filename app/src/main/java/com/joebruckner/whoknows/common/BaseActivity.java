package com.joebruckner.whoknows.common;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.joebruckner.whoknows.WhoKnowsApp;
import com.joebruckner.whoknows.modules.ActivityModule;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

public abstract class BaseActivity extends FragmentActivity {
	private ObjectGraph activityGraph;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WhoKnowsApp app = (WhoKnowsApp) getApplication();
		activityGraph = app.getActivityGraph().plus(getModules().toArray());
		activityGraph.inject(this);
	}

	@Override
	protected void onDestroy() {
		activityGraph = null;
		super.onDestroy();
	}

	protected List<Object> getModules() {
		return Arrays.<Object>asList(new ActivityModule(this));
	}

	public void inject(Object object) {
		activityGraph.inject(object);
	}
}
