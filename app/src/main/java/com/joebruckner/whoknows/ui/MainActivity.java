package com.joebruckner.whoknows.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseActivity;
import com.joebruckner.whoknows.modules.ActivityModule;
import com.melnykov.fab.FloatingActionButton;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.squareup.otto.Bus;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends BaseActivity {
    @InjectView (R.id.sliding_layout) SlidingUpPanelLayout slidePanel;
    @InjectView (R.id.fab) FloatingActionButton fab;
    @Inject Bus bus;
    boolean expanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        slidePanel.setPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override public void onPanelSlide(View view, float v) {
                if (v > 0.02 && !expanded) {
                    fab.hide();
                    expanded = true;
                } else if (v < 0.02 && expanded) {
                    fab.show();
                    expanded = false;
                }
            }

            @Override public void onPanelCollapsed(View view) {
                // Empty
            }

            @Override public void onPanelExpanded(View view) {
                // Empty
            }

            @Override public void onPanelAnchored(View view) {
                // Empty
            }

            @Override public void onPanelHidden(View view) {
                // Empty
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new ActivityModule(this));
    }
}
