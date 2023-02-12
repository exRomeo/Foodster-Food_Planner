package com.example.foodster_foodplanner.retrofitclient;

import com.example.foodster_foodplanner.models.MealModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    @GET("random.php")
    Observable<MealModel> getRandomMeal();

    @GET("search.php?")
    Observable<MealModel> getMealByName(@Query("s") String name);

    @GET("search.php?")
    Observable<MealModel> getMealByFirstLetter(@Query("f") char firstLetter);
}
