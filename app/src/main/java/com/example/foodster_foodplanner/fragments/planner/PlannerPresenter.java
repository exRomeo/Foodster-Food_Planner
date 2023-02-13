package com.example.foodster_foodplanner.fragments.planner;

import com.example.foodster_foodplanner.models.Meal;

public interface PlannerPresenter {
    void addToPlan(Meal meal);
    void removePlan(Meal meal);
    void getMealList();
}
