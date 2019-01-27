package com.loftschool;

public class Record {

    private final String title;
    private final float price;
    private String comment;

    public Record(String title, float price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public float getPrice() {
        return price;
    }

    public String getComment() {
        return comment;
    }
}
