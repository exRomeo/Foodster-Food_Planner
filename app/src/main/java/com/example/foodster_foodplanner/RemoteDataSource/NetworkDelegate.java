package com.example.foodster_foodplanner.RemoteDataSource;

import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

public interface NetworkDelegate {
    void onResponseSuccess(List<Meal> mealList);

    void onResponseFailure(String message);
}
