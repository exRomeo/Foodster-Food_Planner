package com.example.foodster_foodplanner.fragments.search;

import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

public interface NameSearchPresenter {
    public void getMeals(List<Meal> searchResult);
    public void showError(String error);
}
