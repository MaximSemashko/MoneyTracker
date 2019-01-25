package com.loftschool;

public class Record {
    private final float mPrice;
    private final String mTitle;
    private String mComment;

    public float getPrice() {
        return mPrice;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getComment() {
        return mComment;
    }

    public Record(String title, float price) {
        mTitle = title;
        mPrice = price;
    }
}
