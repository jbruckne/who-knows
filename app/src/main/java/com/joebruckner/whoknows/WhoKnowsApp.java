package com.joebruckner.whoknows;

import android.app.Application;

import com.joebruckner.whoknows.modules.AppModule;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

public class WhoKnowsApp extends Application {
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(getModules().toArray());
        inject(this);
    }

    protected List<Object> getModules() {
        return Arrays.<Object>asList(new AppModule(this));
    }

    public void inject(Object object) {
        objectGraph.inject(object);
    }

    public ObjectGraph getActivityGraph() {
        return objectGraph;
    }
}
