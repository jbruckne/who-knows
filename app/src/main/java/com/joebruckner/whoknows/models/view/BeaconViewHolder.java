package com.joebruckner.whoknows.models.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.joebruckner.whoknows.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class BeaconViewHolder extends RecyclerView.ViewHolder {
	@InjectView (R.id.title) TextView titleView;
	@InjectView (R.id.name) TextView nameView;
	@InjectView (R.id.date) TextView dateView;

	public BeaconViewHolder(View itemView) {
		super(itemView);
		ButterKnife.inject(this, itemView);
	}

	public TextView getTitleView() {
		return titleView;
	}

	public TextView getNameView() {
		return nameView;
	}

	public TextView getDateView() {
		return dateView;
	}
}
