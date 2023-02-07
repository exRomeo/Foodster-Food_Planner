package com.example.foodster_foodplanner.Fragments.Favorites;

import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

public interface FavoritesView {
    void showMeals(List<Meal> meals);
    void removeButton(Meal meal);
    void showError(String message);
}
