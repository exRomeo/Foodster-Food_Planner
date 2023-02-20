package com.example.foodster_foodplanner.screen.meal;

import com.example.foodster_foodplanner.models.Meal;

public interface MealView {
    String[] days = {"None", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    void showMeal(Meal meal);
    void addToFavorites(Meal meal);
    void showError(String message);
}
