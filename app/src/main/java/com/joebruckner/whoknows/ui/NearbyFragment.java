package com.joebruckner.whoknows.ui;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseFragment;
import com.joebruckner.whoknows.modules.qualifiers.ForActivity;
import com.joebruckner.whoknows.presenters.NearbyAdapter;
import com.joebruckner.whoknows.ui.Widgets.DividerItemDecoration;
import com.joebruckner.whoknows.ui.Widgets.OnItemClickListener;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class NearbyFragment extends BaseFragment {
    @InjectView (R.id.list_view) RecyclerView listView;
	@Inject @ForActivity Context context;
    @Inject NearbyAdapter adapter;

	public static NearbyFragment newInstance() {
		return new NearbyFragment();
	}

    public NearbyFragment() {
        // Required empty public constructor
    }

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nearby, container, false);
        ButterKnife.inject(this, view);
        listView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));
        listView.setLayoutManager(new LinearLayoutManager(context));
        listView.setAdapter(adapter);
		listView.addOnItemTouchListener(new OnItemClickListener(context) {
			@Override public void onItemClick(View view, int position) {
				Intent intent = new Intent(context, BeaconDetailActivity.class);
				intent.putExtra("Beacon", adapter.getItem(position));
				startActivity(intent);
			}
		});
        listView.setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        return view;
    }

	@Override public void onResume() {
		super.onResume();
		adapter.updateItems();
	}
}