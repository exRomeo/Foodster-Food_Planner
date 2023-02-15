package com.example.foodster_foodplanner.fragments.planner;

import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

public interface PlannerView {
    String[] days = {"None", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    void addMealToPlan(Meal meal,int day);
    void updateList(List<Meal> mealList);
    void updateSaturdayMeals(List<Meal> mealList);
    void updateSundayMeals(List<Meal> mealList);
    void updateMondayMeals(List<Meal> mealList);
    void updateTuesdayMeals(List<Meal> mealList);
    void updateWednesdayMeals(List<Meal> mealList);
    void updateThursdayMeals(List<Meal> mealList);
    void updateFridayMeals(List<Meal> mealList);

}
