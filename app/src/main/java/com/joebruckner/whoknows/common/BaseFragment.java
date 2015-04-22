package com.joebruckner.whoknows.common;


import android.app.Activity;
import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((BaseActivity) activity).inject(this);
    }
}
