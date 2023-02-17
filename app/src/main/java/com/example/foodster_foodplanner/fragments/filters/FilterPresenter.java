package com.example.foodster_foodplanner.fragments.filters;

import com.example.foodster_foodplanner.models.Meal;

public interface FilterPresenter {

    void getCountryList();
    void getCategoryList();
    void getIngredientsList();
    void filterByCountry(String country);
    void filterByCategory(String category);
    void filterByIngredient(String ingredient);
    void addToFavorites(Meal meal);
    String getFilterType();
    void getMealByID(int id);
}
