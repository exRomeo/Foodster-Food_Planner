package com.example.foodster_foodplanner.localdatabase;

import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface RoomInterface {
    void updateMeal(Meal meal);

    void insertDaily(Meal meal);

    void insertWeekly(Meal meal);

    Flowable<List<Meal>> getListOfFavorites();
    Flowable<List<Meal>> getPlannedMeals(int day);

    List<Meal> getListOfDaily();

    List<Meal> getListOfWeekly();

    Flowable<Meal> getFavoriteMeal(int idMeal);

    Meal getDailyMeal(int idMeal);

    Meal getWeeklyMeal(int idMeal);

    void removeFavoriteMeal(Meal meal);

    void removeWeeklyMeal(int idMeal);


}
