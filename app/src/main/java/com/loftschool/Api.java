package com.loftschool;

import java.util.List;

import retrofit2.http.GET;

public interface Api {
    @GET
    List<Item> getItems();
}
