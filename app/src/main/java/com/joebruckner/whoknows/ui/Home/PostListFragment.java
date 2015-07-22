package com.joebruckner.whoknows.ui.Home;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseFragment;
import com.joebruckner.whoknows.models.Post;
import com.joebruckner.whoknows.presenters.SimplePresenter;
import com.joebruckner.whoknows.ui.Post.PostActivity;
import com.joebruckner.whoknows.ui.Widgets.DividerItemDecoration;
import com.joebruckner.whoknows.ui.Widgets.OnItemClickListener;
import com.joebruckner.whoknows.ui.views.PostListView;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class PostListFragment extends BaseFragment implements PostListView {
	@Bind(R.id.list_view) RecyclerView listView;
	@Inject Activity activity;
	@Inject SimplePresenter<List<Post>, PostListView> presenter;
	@Inject PostListAdapter adapter;

	public static final int NEARBY = 0;
	public static final int JOINED = 1;
	public static final int POSTED = 2;
	public static final String LIST_TYPE = "type";

	private int filter = 0;

	public static PostListFragment newInstance(int type) {
		PostListFragment fragment = new PostListFragment();
		Bundle bundle = new Bundle();
		bundle.putInt(LIST_TYPE, type);
		fragment.setArguments(bundle);
		return fragment;
	}

	public PostListFragment() {
		// Required empty public constructor
	}

	@Override public void setupLayout() {
		super.setupLayout();
		listView.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL_LIST));
		listView.setLayoutManager(new LinearLayoutManager(activity));
		listView.setAdapter(adapter);
		listView.addOnItemTouchListener(new OnItemClickListener(activity) {
			@Override public void onItemClick(View view, int position) {
				Intent intent = new Intent(activity, PostActivity.class);
				Post post = adapter.getItem(position);
				intent.putExtra(PostActivity.BEACON_ID, post.getId());
				intent.putExtra(PostActivity.BEACON_TITLE, post.getTitle());
				startActivity(intent);
			}
		});
	}

	@Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		filter = getArguments().getInt(LIST_TYPE);
		presenter.attachView(this);
		presenter.execute();
	}

	@Override public int getLayout() {
		return R.layout.fragment_post_list;
	}

	@Override public void sendEvent(int tag) {
		presenter.execute(tag);
	}

	@Override public void showLoading() {
		// TODO
	}

	@Override public void showContent() {
	}

	@Override public void showEmpty() {
		// TODO
	}

	@Override public void refresh() {
		Log.d(TAG, "refresh()");
		presenter.execute();
	}

	@Override public int getFilter() {
		return this.filter;
	}

	@Override public void showError() {
		// TODO
	}

	@Override public void setData(@NonNull List<Post> posts) {
		adapter.setItems(posts);
	}

	@Override public void onDestroyView() {
		presenter.detachView();
		super.onDestroyView();
	}

	@Override public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_refresh) refresh();
		return super.onOptionsItemSelected(item);
	}
}