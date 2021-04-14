package com.example.foodparcelsapp;

import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;

import java.security.Key;
import java.util.Date;

public class FoodParcel implements Parcelable {

    // Class Attributes
    private int imageID;
    private String name;
    private String url;
    private String keywords;
    private String dateObtained;
    private String owner;
    private int rating;
    private boolean share;

    public FoodParcel(int ImageID, String Name, String Url, String Keywords, String DateObtained, String Owner, int Rating, boolean Share)
    {
        imageID = ImageID;
        name = Name;
        url = Url;
        keywords = Keywords;
        dateObtained = DateObtained;
        owner = Owner;
        rating = Rating;
        share = Share;
    }

    protected FoodParcel(Parcel in)
    {
        imageID = in.readInt();
        name = in.readString();
        url = in.readString();
        keywords = in.readString();
        dateObtained = in.readString();
        owner = in.readString();
        rating = in.readInt();
        share = in.readInt() == 1;
    }

    public static final Creator<FoodParcel> CREATOR = new Creator<FoodParcel>() {
        @Override
        public FoodParcel createFromParcel(Parcel in) {
            return new FoodParcel(in);
        }

        @Override
        public FoodParcel[] newArray(int size) {
            return new FoodParcel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(imageID);
        parcel.writeString(name);
        parcel.writeString(url);
        parcel.writeString(keywords);
        parcel.writeString(dateObtained);
        parcel.writeString(owner);
        parcel.writeInt(rating);
        parcel.writeInt(share ? 1 : 0);
    }

    public int getImageID(){
        return imageID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDateObtained() {
        return dateObtained;
    }

    public void setDateObtained(String dateObtained) {
        this.dateObtained = dateObtained;
    }

    public boolean isShare() {
        return share;
    }

    public void setShare(boolean share) {
        this.share = share;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
