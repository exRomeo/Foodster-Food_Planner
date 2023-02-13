package com.example.foodster_foodplanner.fragments.planner;

import com.example.foodster_foodplanner.models.Meal;

public interface PlannerPresenter {
    void addToPlan(Meal meal, int day);
    void removePlan(Meal meal);
    void getMealList();
}
