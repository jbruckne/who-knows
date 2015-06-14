package com.joebruckner.whoknows.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.joebruckner.whoknows.WhoKnowsApp;
import com.joebruckner.whoknows.modules.ActivityModule;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

public abstract class BaseActivity extends AppCompatActivity {
	private ObjectGraph activityGraph;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setActivityGraph();
	}

	@Override
	protected void onDestroy() {
		activityGraph = null;
		super.onDestroy();
	}

	protected void setActivityGraph() {
		WhoKnowsApp app = (WhoKnowsApp) getApplication();
		activityGraph = app.getActivityGraph().plus(getModules().toArray());
		activityGraph.inject(this);
	}

	protected List<Object> getModules() {
		return Arrays.<Object>asList(new ActivityModule(this));
	}

	public void inject(Object object) {
		if (activityGraph == null) setActivityGraph();
		activityGraph.inject(object);
	}
}
