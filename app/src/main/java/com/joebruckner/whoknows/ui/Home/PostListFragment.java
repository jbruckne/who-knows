package com.joebruckner.whoknows.ui.Home;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseFragment;
import com.joebruckner.whoknows.models.Post;
import com.joebruckner.whoknows.presenters.PostListPresenter;
import com.joebruckner.whoknows.ui.Post.PostDetailActivity;
import com.joebruckner.whoknows.ui.Widgets.DividerItemDecoration;
import com.joebruckner.whoknows.ui.Widgets.OnItemClickListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PostListFragment extends BaseFragment implements PostListView {
	@Bind(R.id.list_view) RecyclerView listView;
	@Inject Activity activity;
	@Inject PostListPresenter presenter;
	@Inject PostListAdapter adapter;

	public static final int NEARBY = 0;
	public static final int JOINED = 1;
	public static final int POSTED = 2;
	public static String LIST_TYPE = "TYPE";

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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_post_list, container, false);
		ButterKnife.bind(this, view);
		return view;
	}

	@Override public void onResume() {
		super.onResume();
		int filter = getArguments().getInt(LIST_TYPE);
		presenter.attachView(this);
		presenter.fetchPosts(filter);
	}

	@Override public void showLoading() {
		// TODO
	}

	@Override public void showContent() {
		listView.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL_LIST));
		listView.setLayoutManager(new LinearLayoutManager(activity));
		listView.setAdapter(adapter);
		listView.addOnItemTouchListener(new OnItemClickListener(activity) {
			@Override public void onItemClick(View view, int position) {
				Intent intent = new Intent(activity, PostDetailActivity.class);
				Post post = adapter.getItem(position);
				intent.putExtra(PostDetailActivity.BEACON_ID, post.getId());
				intent.putExtra(PostDetailActivity.BEACON_TITLE, post.getTitle());
				startActivity(intent);
			}
		});
	}

	@Override public void showEmpty() {
		// TODO
	}

	@Override public void showError() {
		// TODO
	}

	@Override public void setData(List<Post> posts) {
		adapter.setItems(posts);
	}
}