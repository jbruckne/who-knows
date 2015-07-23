package com.joebruckner.whoknows.ui.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.models.Post;
import com.joebruckner.whoknows.models.PostViewHolder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostListAdapter extends RecyclerView.Adapter<PostViewHolder> {
	List<Post> posts = new ArrayList<>();

	public void setItems(List<Post> posts) {
		this.posts = posts;
		notifyDataSetChanged();
	}

	public Post getItem(int position) {
		return posts.get(position);
	}

	@Override public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater
				.from(parent.getContext()).inflate(R.layout.item_post, parent, false);
		return new PostViewHolder(view);
	}

	@Override public void onBindViewHolder(PostViewHolder holder, int position) {
		Post post = posts.get(position);
		holder.getTitleView().setText(post.getTitle());
		holder.getNameView().setText(post.getName());
		holder.getDescriptionView().setText(post.getDescription());
		Date date = new Date();
		date.setTime(post.getDate());
		holder.getDateView().setText(PostViewHolder.formatDate(date));
	}

	@Override public int getItemCount() {
		return posts.size();
	}

}
