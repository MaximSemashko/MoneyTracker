package com.loftschool;

import com.google.gson.annotations.SerializedName;

public class Item {

    public static final String TYPE_INCOMES = "incomes";
    public static final String TYPE_COSTS = "costs";
    public static final String TYPE_BALANSE = "balanse";
    public static final String TYPE_UNKNOWN = "unknown";

    public int id;
    @SerializedName("name")
    public String name;
    public float price;
    public String type;

    public Item(String name, float price, String type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }
}
