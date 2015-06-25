package com.joebruckner.whoknows.ui.Beacon;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.joebruckner.whoknows.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.Optional;

public class PostViewHolder extends RecyclerView.ViewHolder {
	@Optional @InjectView (R.id.title) TextView titleView;
	@Optional @InjectView (R.id.name) TextView nameView;
	@Optional @InjectView (R.id.description) TextView descriptionView;
	@Optional @InjectView (R.id.date) TextView dateView;
	@Optional @InjectView (R.id.contact_info) TextView contactInfoView;

	public static SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy", Locale.US);

	public PostViewHolder(View itemView) {
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

	public static String formatDate(Date date) {
		return format.format(date);
	}
}
