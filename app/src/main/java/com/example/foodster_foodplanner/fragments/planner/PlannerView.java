package com.example.foodster_foodplanner.fragments.planner;

import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

public interface PlannerView {
    void addMealToPlan(Meal meal);
    void updateList(List<Meal> mealList);
}
