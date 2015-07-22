package com.joebruckner.whoknows.models;

public class Post {
	String id;
	String userId;
	String title;
	String name;
	long date;
	String contactInfo;
	String description;
	double lat;
	double lng;

	public Post() {
	}

	public Post(Builder builder) {
		this.id = builder.id;
		this.userId = builder.userId;
		this.title = builder.title;
		this.name = builder.name;
		this.date = builder.date;
		this.contactInfo = builder.contactInfo;
		this.description = builder.description;
		this.lat = builder.lat;
		this.lng = builder.lng;
	}

	public String getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public String getTitle() {
		return title;
	}

	public String getName() {
		return name;
	}

	public long getDate() {
		return date;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public String getDescription() {
		return description;
	}

	public double getLat() {
		return lat;
	}

	public double getLng() {
		return lng;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public static class Builder {
		private String id;
		private String userId;
		private String title;
		private String name;
		private long date;
		private String contactInfo;
		private String description;
		private double lat;
		private double lng;

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder userId(String userId) {
			this.userId = userId;
			return this;
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder date(long date) {
			this.date = date;
			return this;
		}

		public Builder contactInfo(String contactInfo) {
			this.contactInfo = contactInfo;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder lat(double lat) {
			this.lat = lat;
			return this;
		}

		public Builder lng(double lng) {
			this.lng = lng;
			return this;
		}

		public Post build() {
			return new Post(this);
		}
	}
}
