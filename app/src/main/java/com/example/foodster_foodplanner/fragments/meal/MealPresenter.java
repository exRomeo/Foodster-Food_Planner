package com.example.foodster_foodplanner.fragments.meal;

import com.example.foodster_foodplanner.models.Meal;

public interface MealPresenter {

    void addToFavorites(Meal meal);
    void removeMeal(Meal meal);
    String getIngredients(Meal meal);
    String getMeasures(Meal meal);
    Meal getMeal();
    void planMeal(Meal meal, int day);
}
