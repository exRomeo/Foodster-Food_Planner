package com.example.foodster_foodplanner.firestoreBackup;

import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

public interface FirestoreBackup {
    void backupMeal(Meal meal);
    void backupMealList(List<Meal> mealList);

}
