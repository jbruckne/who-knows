package com.joebruckner.whoknows.modules;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;

import com.joebruckner.whoknows.ForApplication;
import com.joebruckner.whoknows.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module (
        injects = MainActivity.class,
        library = true
)
public class AppModule {
    private final Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Provides @Singleton @ForApplication
    Context provideApplicationContext() {
        return app;
    }

    @Provides @Singleton
    LayoutInflater provideLayoutInflater() {
        return (LayoutInflater) app.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
}
