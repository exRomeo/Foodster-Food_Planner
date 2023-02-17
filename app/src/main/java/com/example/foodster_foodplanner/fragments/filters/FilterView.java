package com.example.foodster_foodplanner.fragments.filters;

import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

public interface FilterView {
    void showFilter(List<Meal> meals);
    void showMeals(List<Meal> meals);
    void showError(String message);
    void filter(Meal selectedItem);
    void updateFilter();
    void navigateToMeal(Meal meal);
}
