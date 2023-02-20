package com.example.foodster_foodplanner.retrofitclient;


import com.example.foodster_foodplanner.models.MealModel;

import io.reactivex.rxjava3.core.Observable;

public interface RetrofitClient {

    void getRandomMeal(NetworkDelegate networkDelegate);

    void searchByName(NetworkDelegate networkDelegate, String strMeal);

    void listByFirstLetter(NetworkDelegate networkDelegate, char firstLetter);

    void getCountryList(NetworkDelegate networkDelegate);

    void getCategoryList(NetworkDelegate networkDelegate);

    void getIngredientsList(NetworkDelegate networkDelegate);

    Observable<MealModel> filterByCountry(String country);

    Observable<MealModel> filterByCategory(String category);

    Observable<MealModel> filterByIngredient(String ingredient);

    Observable<MealModel> getMealByID(int id);
}

