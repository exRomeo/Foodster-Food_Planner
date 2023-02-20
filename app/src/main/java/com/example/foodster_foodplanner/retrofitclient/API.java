package com.example.foodster_foodplanner.retrofitclient;

import com.example.foodster_foodplanner.models.MealModel;

import io.reactivex.rxjava3.core.Observable;
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


    @GET("list.php?")
    Observable<MealModel> getCountryList(@Query("a") String country);
    @GET("list.php?")
    Observable<MealModel> getCategoryList(@Query("c") String category);
    @GET("list.php?")
    Observable<MealModel> getIngredientList(@Query("i") String ingredient);

    @GET("filter.php?")
    Observable<MealModel> filterByCountry(@Query("a") String country);
    @GET("filter.php?")
    Observable<MealModel> filterByCategory(@Query("c") String category);
    @GET("filter.php?")
    Observable<MealModel> filterByIngredient(@Query("i") String ingredient);
    @GET("lookup.php?")
    Observable<MealModel> getMealByID(@Query("i") int id);
}
