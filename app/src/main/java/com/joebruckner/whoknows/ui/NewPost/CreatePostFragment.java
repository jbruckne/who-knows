package com.joebruckner.whoknows.ui.NewPost;


import android.app.Activity;
import android.widget.CheckBox;
import android.widget.EditText;

import com.joebruckner.whoknows.R;
import com.joebruckner.whoknows.common.BaseFragment;
import com.joebruckner.whoknows.models.Profile;
import com.joebruckner.whoknows.presenters.NewPostPresenter;
import com.joebruckner.whoknows.ui.SimpleView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;


public class CreatePostFragment extends BaseFragment implements SimpleView<Profile> {
	@Bind(R.id.title) EditText title;
	@Bind(R.id.description) EditText description;
	@Bind(R.id.location) CheckBox location;
	@Inject NewPostPresenter presenter;
	@Inject Activity activity;

	public static CreatePostFragment newInstance() {
		return new CreatePostFragment();
	}

	public CreatePostFragment() {
		// Required empty public constructor
	}

	@Override public int getLayout() {
		return R.layout.fragment_create_post;
	}

	@Override public void onResume() {
		super.onResume();
		presenter.attachView(this);
	}

	@OnClick(R.id.button_post) public void onClick() {
		presenter.sendPost(title.getText().toString(),
				description.getText().toString(),
				location.isChecked());
		activity.finish();
	}

	@Override public void showContent() {

	}

	@Override public void showLoading() {

	}

	@Override public void showError() {

	}

	@Override public void setData(Profile profile) {

	}
}
