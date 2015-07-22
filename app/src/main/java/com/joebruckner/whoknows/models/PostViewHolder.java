package com.joebruckner.whoknows.models;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.joebruckner.whoknows.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

@SuppressWarnings( "ALL" ) public class PostViewHolder extends RecyclerView.ViewHolder {
	@Nullable @Bind (R.id.title) TextView titleView;
	@Nullable @Bind (R.id.name) TextView nameView;
	@Nullable @Bind (R.id.description) TextView descriptionView;
	@Nullable @Bind (R.id.date) TextView dateView;
	@Nullable @Bind (R.id.contact_info) TextView contactInfoView;

	public static SimpleDateFormat format = new SimpleDateFormat("MMM dd, yyyy", Locale.US);

	public PostViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
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
