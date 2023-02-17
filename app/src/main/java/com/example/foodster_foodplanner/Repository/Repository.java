package com.example.foodster_foodplanner.Repository;

import com.example.foodster_foodplanner.models.Meal;
import com.example.foodster_foodplanner.models.MealModel;
import com.example.foodster_foodplanner.retrofitclient.NetworkDelegate;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public interface Repository {

    Flowable<List<Meal>> getFavoritesList();


    void removeFavorite(Meal meal);

    Flowable<Meal> getFavoriteById(int id);

    void addFavorite(Meal meal);

    void planMeal(Meal meal, int day);

    Flowable<List<Meal>> getPlannedMeals(int day);

    void getRandomMeal(NetworkDelegate networkDelegate);

    Flowable<List<Meal>> getDailyMeals(String date);

    void insertMeal(Meal meal);

    void getCountryList(NetworkDelegate networkDelegate);

    void getCategoryList(NetworkDelegate networkDelegate);

    void getIngredientsList(NetworkDelegate networkDelegate);

    Observable<MealModel> filterByCountry(String country);

    Observable<MealModel> filterByCategory(String category);

    Observable<MealModel> filterByIngredient(String ingredient);
    Observable<MealModel> getMealByID(int id);


}
