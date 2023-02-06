package com.example.foodster_foodplanner.localdatabase;

import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

public interface RoomInterface {
    void insertToFavorites(Meal meal);

    void insertDaily(Meal meal);

    void insertWeekly(Meal meal);

    List<Meal> getListOfFavorites();

    List<Meal> getListOfDaily();

    List<Meal> getListOfWeekly();

    Meal getFavoriteMeal(int idMeal);

    Meal getDailyMeal(int idMeal);

    Meal getWeeklyMeal(int idMeal);

    void removeFavoriteMeal(int idMeal);

    void removeWeeklyMeal(int idMeal);

}
