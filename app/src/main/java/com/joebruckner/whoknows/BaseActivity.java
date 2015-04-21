package com.joebruckner.whoknows;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import javax.inject.Inject;

public class BaseActivity extends ActionBarActivity {
    @Inject @ForApplication
    protected Context app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((WhoKnowsApp) getApplication()).inject(this);
    }
}
