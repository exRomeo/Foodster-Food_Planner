package com.example.foodster_foodplanner.screen.search;

import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

public interface NameSearchView {
    public void showResults(List<Meal> results);
    public void showErr(String error);
}
