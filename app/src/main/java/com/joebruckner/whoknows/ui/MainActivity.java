package com.joebruckner.whoknows.ui;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.joebruckner.whoknows.BaseActivity;
import com.joebruckner.whoknows.R;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(app, "It Works!", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
