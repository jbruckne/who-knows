package com.joebruckner.whoknows.models;

public class Offer {
	String id;
	String userId;
	String userName;
	String recipientId;
	String postId;

	public Offer() {
	}

	public Offer(Builder builder) {
		this.id = builder.id;
		this.userId = builder.userId;
		this.userName = builder.userName;
		this.recipientId = builder.recipientId;
		this.postId = builder.postId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}

	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public static class Builder {

		private String id;
		private String userId;
		private String userName;
		private String recipientId;
		private String postId;

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder userId(String userId) {
			this.userId = userId;
			return this;
		}

		public Builder userName(String userName) {
			this.userName = userName;
			return this;
		}

		public Builder recipientId(String recipientId) {
			this.recipientId = recipientId;
			return this;
		}

		public Builder postId(String postId) {
			this.postId = postId;
			return this;
		}

		public Offer build() {
			return new Offer(this);
		}
	}
}
