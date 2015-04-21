package com.joebruckner.whoknows;

import android.app.Application;

import com.joebruckner.whoknows.modules.AppModule;
import com.joebruckner.whoknows.modules.TestModule;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

public class WhoKnowsApp extends Application {
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(getModules().toArray());
    }

    protected List<Object> getModules() {
        return Arrays.asList(
                new AppModule(this),
                new TestModule()
        );
    }

    public void inject(Object object) {
        objectGraph.inject(object);
    }
}
