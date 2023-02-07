package com.example.foodster_foodplanner.localdatabase;

import androidx.lifecycle.LiveData;

import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

public interface RoomInterface {
    void addToFavorites(Meal meal);

    void insertDaily(Meal meal);

    void insertWeekly(Meal meal);

    LiveData<List<Meal>> getListOfFavorites();

    List<Meal> getListOfDaily();

    List<Meal> getListOfWeekly();

    LiveData<Meal> getFavoriteMeal(int idMeal);

    Meal getDailyMeal(int idMeal);

    Meal getWeeklyMeal(int idMeal);

    void removeFavoriteMeal(Meal meal);

    void removeWeeklyMeal(int idMeal);

}
