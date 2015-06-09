package com.joebruckner.whoknows.ui.Widgets;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public abstract class OnItemClickListener implements RecyclerView.OnItemTouchListener {

	GestureDetector gestureDetector;

	public OnItemClickListener(Context context) {
		gestureDetector = new GestureDetector(context, new GestureDetector
				.SimpleOnGestureListener() {
			@Override public boolean onSingleTapUp(MotionEvent e) {
				return true;
			}
		});
	}

	@Override public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
		View childView = rv.findChildViewUnder(e.getX(), e.getY());
		if (childView != null && gestureDetector.onTouchEvent(e)) {
			onItemClick(childView, rv.getChildPosition(childView));
			return true;
		}
		return false;
	}

	public abstract void onItemClick(View view, int position);

	@Override public void onTouchEvent(RecyclerView rv, MotionEvent e) {

	}
}
