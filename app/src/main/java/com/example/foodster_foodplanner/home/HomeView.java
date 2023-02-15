package com.example.foodster_foodplanner.home;

import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

public interface HomeView {
    public void showDailyMeals(List<Meal> dailyTen);
    public void showError(String errMsg);
    public void showFromDataBase(List<Meal> dailyTen);
}
