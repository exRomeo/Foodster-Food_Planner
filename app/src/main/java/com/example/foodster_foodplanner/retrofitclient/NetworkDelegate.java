package com.example.foodster_foodplanner.retrofitclient;

import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

public interface NetworkDelegate {
    void onResponseSuccess(List<Meal> meals);

    void onResponseFailure(String errorMessage);
}
