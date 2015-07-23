package com.joebruckner.whoknows.presenters;

import com.joebruckner.whoknows.common.SimplePresenter;
import com.joebruckner.whoknows.managers.AccountManager;
import com.joebruckner.whoknows.managers.DatabaseManager;
import com.joebruckner.whoknows.models.Post;
import com.joebruckner.whoknows.ui.views.NewPostView;
import com.squareup.otto.Bus;

public class NewPostPresenter extends SimplePresenter<Post,NewPostView> {
	DatabaseManager database;
	AccountManager account;

	public NewPostPresenter(DatabaseManager database, AccountManager account, Bus bus) {
		super(bus);
		this.database = database;
		this.account = account;
	}

	@Override public void execute(int tag) {
		if (getView() == null) return;

		String title = getView().getTitle();
		String desciption = getView().getDescription();
		database.putPost(title, desciption, DatabaseManager.PHONE);
		getView().showSuccess();
	}
}
