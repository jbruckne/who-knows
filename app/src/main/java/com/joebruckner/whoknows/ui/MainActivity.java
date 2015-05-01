package com.joebruckner.whoknows.ui;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseActivity;
import com.melnykov.fab.FloatingActionButton;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends BaseActivity implements SlidingUpPanelLayout.PanelSlideListener {
    @InjectView (R.id.sliding_layout) SlidingUpPanelLayout slidePanel;
    @InjectView (R.id.fab) FloatingActionButton fab;
    @InjectView (R.id.toolbar) Toolbar toolbar;
    @InjectView (R.id.drawer) DrawerLayout drawer;
    @Inject Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        bus.register(this);
        initToolbar();
        initDrawers();
        initSlider();
    }

    protected void initToolbar() {
        setSupportActionBar(toolbar);
        toolbar.bringToFront();
    }

    protected void initDrawers() {
        drawer.setStatusBarBackground(R.color.green_500);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.setDrawerListener(toggle);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toggle.syncState();
    }

    protected void initSlider() {
        Log.d("Main", slidePanel.getPanelHeight() + "");
        slidePanel.setPanelSlideListener(this);
        slidePanel.setOverScrollMode(SlidingUpPanelLayout.OVER_SCROLL_NEVER);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override public void onPanelSlide(View view, float v) {
        if (v > 0.02 && fab.isVisible()) fab.hide();
        else if (v < 0.02 && !fab.isVisible()) fab.show();
        int alpha = Math.round(255*v);
    }

    @Override public void onPanelCollapsed(View view) {

    }

    @Override public void onPanelExpanded(View view) {

    }

    @Override public void onPanelAnchored(View view) {

    }

    @Override public void onPanelHidden(View view) {

    }
}
