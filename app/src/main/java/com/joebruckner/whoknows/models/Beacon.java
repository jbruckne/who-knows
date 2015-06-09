package com.joebruckner.whoknows.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;
import java.util.UUID;

public class Beacon implements Parcelable {
	long id;
	String title;
	String name;
	Date date;
	String contactInfo;
	String description;
	LatLng location;

	public Beacon(String title) {
		this(title, "Anonymous", new Date(), "(123) 456-7890", "None", null);
	}

	public Beacon(String title, String name, Date date, String contactInfo, String description,
	 LatLng location) {
		this.id = Math.abs(UUID.randomUUID().getLeastSignificantBits());
		this.title = title;
		this.name = name;
		this.date = date;
		this.contactInfo = contactInfo;
		this.description = description;
		this.location = location;
	}

	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getName() {
		return name;
	}

	public Date getDate() {
		return date;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public String getDescription() {
		return description;
	}

	public LatLng getLocation() {
		return location;
	}

	@Override public int describeContents() {
		return 0;
	}

	public Beacon(Parcel in) {
		this.id = in.readLong();
		this.title = in.readString();
		this.name = in.readString();
		this.date = (Date) in.readSerializable();
		this.contactInfo = in.readString();
		this.description = in.readString();
		this.location = in.readParcelable(LatLng.class.getClassLoader());
	}

	@Override public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(id);
		dest.writeString(title);
		dest.writeString(name);
		dest.writeSerializable(date);
		dest.writeString(contactInfo);
		dest.writeString(description);
		dest.writeParcelable(location, flags);
	}

	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public Beacon createFromParcel(Parcel in) {
			return new Beacon(in);
		}

		public Beacon[] newArray(int size) {
			return new Beacon[size];
		}
	};
}
