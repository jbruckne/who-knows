package com.joebruckner.whoknows.models.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.joebruckner.whoknows.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class EventViewHolder extends RecyclerView.ViewHolder {
	@InjectView (R.id.title) TextView titleView;

	public EventViewHolder(View itemView) {
		super(itemView);
		ButterKnife.inject(this, itemView);
	}

	public TextView getTitleView() {
		return titleView;
	}
}
