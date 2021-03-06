package com.loftschool.Api;

import com.loftschool.BalanseResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    @GET("auth")
    Call<AuthResult> auth(@Query("social_user_id") String userId);

    @GET("items")
    Call<List<Item>> getItems(@Query("type") String type);

    @POST("items/add")
    Call<AddItemResult> addItem(@Query("price") String price, @Query("name") String name, @Query("type") String type);

//    @POST("items/remove?id=<id>")
//    Call<RemoveItem> removeItem();
    @GET("balanse")
    Call<BalanseResult> balance();
}
