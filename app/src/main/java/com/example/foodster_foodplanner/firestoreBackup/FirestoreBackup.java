package com.example.foodster_foodplanner.firestoreBackup;

import android.content.Context;

import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

public interface FirestoreBackup {
    void backupMealList(List<Meal> mealList);
    void retrieveFavList(Context context);
}
