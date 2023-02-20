package com.example.foodster_foodplanner.models;

import java.io.Serializable;
import java.util.List;

public class MealModel implements Serializable {
    List<Meal> meals;

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }
}
