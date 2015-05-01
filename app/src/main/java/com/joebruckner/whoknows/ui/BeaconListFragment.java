package com.joebruckner.whoknows.ui;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseFragment;
import com.joebruckner.whoknows.presenters.BeaconListAdapter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class BeaconListFragment extends BaseFragment implements BeaconListView {
    @InjectView (R.id.list_view) RecyclerView listView;
    @InjectView (R.id.list_header) TextView header;
    @Inject RecyclerView.LayoutManager layoutManager;
    @Inject BeaconListAdapter adapter;

    public BeaconListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beacon_list, container, false);
        ButterKnife.inject(this, view);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(adapter);
        listView.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        adapter.setView(this);
        return view;
    }

    @Override
    public void setHeader(String text) {
        header.setText(text);
    }

    @Override
    public void hideHeader() {
        header.animate().translationY(-R.dimen.toolbar_height).setDuration(500000).start();
    }

    @Override
    public void showHeader() {
        header.animate().translationY(0).setDuration(500000).start();
    }
}
