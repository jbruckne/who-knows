package com.joebruckner.whoknows.models.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.joebruckner.whoknows.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;

public class BeaconViewHolder extends RecyclerView.ViewHolder {
	@Optional @InjectView (R.id.title) TextView titleView;
	@Optional @InjectView (R.id.name) TextView nameView;
	@Optional @InjectView (R.id.description) TextView descriptionView;
	@Optional @InjectView (R.id.date) TextView dateView;
	@Optional @InjectView (R.id.contact_info) TextView contactInfoView;

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

	public TextView getDescriptionView() {
		return descriptionView;
	}

	public TextView getDateView() {
		return dateView;
	}

	public TextView getContactInfo() {
		return contactInfoView;
	}
}
