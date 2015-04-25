package com.joebruckner.whoknows.presenters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.models.logic.Event;
import com.joebruckner.whoknows.models.view.EventViewHolder;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventViewHolder> {
	List<Event> events;

	public EventAdapter(List<Event> events) {
		this.events = events;
	}

	@Override public EventViewHolder onCreateViewHolder(ViewGroup parent, int type) {
		View view = LayoutInflater
				.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
		return new EventViewHolder(view);
	}

	@Override public void onBindViewHolder(EventViewHolder eventViewHolder, int position) {
		Event event = events.get(position);
		eventViewHolder.getTitleView().setText(event.getTitle());
	}

	@Override public int getItemCount() {
		return events.size();
	}
}
