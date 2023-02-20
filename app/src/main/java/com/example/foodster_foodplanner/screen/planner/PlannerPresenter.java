package com.example.foodster_foodplanner.screen.planner;

import com.example.foodster_foodplanner.models.Meal;

public interface PlannerPresenter {
    void addToPlan(Meal meal, int day);
    void removePlan(Meal meal);
    void getMealList();
    void getSaturdayMeals();
    void getSundayMeals();
    void getMondayMeals();
    void getTuesdayMeals();
    void getWednesdayMeals();
    void getThursdayMeals();
    void getFridayMeals();
    void updateRecyclerViews();
}
