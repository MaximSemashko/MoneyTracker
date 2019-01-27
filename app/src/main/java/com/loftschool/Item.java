package com.loftschool;

public class Item {

    private final String title;
    private final float price;

    public Item(String title, float price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public float getPrice() {
        return price;
    }

}
