package com.example.foodster_foodplanner.retrofitclient;

import com.example.foodster_foodplanner.models.MealModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    @GET("random.php")
    Call<MealModel> getRandomMeal();

    @GET("search.php?")
    Call<MealModel> getMealByName(@Query("s") String name);

    @GET("search.php?")
    Call<MealModel> getMealByFirstLetter(@Query("f") char firstLetter);
}
