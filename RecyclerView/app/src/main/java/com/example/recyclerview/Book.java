package com.example.recyclerview;

public class Book {
    private int imageID;
    private String title;
    private String rating;


    public Book()
    {}

    public Book(int imageID, String title, String rating)
    {
        this.imageID = imageID;
        this.title = title;
        this.rating = rating;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
