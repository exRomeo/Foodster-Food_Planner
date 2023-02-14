package com.example.foodster_foodplanner.home;

import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

public interface HomePresenter {
    public void getMeals();
    public void addToFavs(Meal meal);
    public void addDailyToDb(List<Meal> dailyTen);
    public void getDailyFromDb();
}
