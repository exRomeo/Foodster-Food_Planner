package com.example.foodster_foodplanner.fragments.planner;

import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

public interface PlannerView {
    String[] days = {"None", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    void addMealToPlan(Meal meal,int day);
    void updateList(List<Meal> mealList);
}
