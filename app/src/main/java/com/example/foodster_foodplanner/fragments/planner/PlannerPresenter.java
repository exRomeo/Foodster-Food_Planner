package com.example.foodster_foodplanner.fragments.planner;

import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

public interface PlannerPresenter {
    void addToPlan(Meal meal);
    void removePlan(Meal meal);
    List<Meal> getMealList();
}
