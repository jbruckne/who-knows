package com.joebruckner.whoknows.ui.Beacon;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joebruckner.whoknows.R;

import java.util.List;

public class AttendeesListAdapter extends RecyclerView.Adapter<PostViewHolder> {
	List<String> attendees;

	public void setItems(List<String> attendees) {
		this.attendees = attendees;
		notifyDataSetChanged();
	}

	public String getItem(int position) {
		return attendees.get(position);
	}

	@Override public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater
				.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
		return new PostViewHolder(view);
	}

	@Override public void onBindViewHolder(PostViewHolder holder, int position) {
		holder.getNameView().setText(attendees.get(position));
	}

	@Override public int getItemCount() {
		return attendees.size();
	}
}
