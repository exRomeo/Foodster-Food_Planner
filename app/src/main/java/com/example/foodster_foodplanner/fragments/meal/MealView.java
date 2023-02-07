package com.example.foodster_foodplanner.fragments.meal;

import com.example.foodster_foodplanner.models.Meal;

public interface MealView {
    void showMeal(Meal meal);
    void addToFavorites(Meal meal);
    void showError(String message);
}
