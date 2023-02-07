package com.example.foodster_foodplanner.localdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Update;

import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

@Dao
public interface MealDao {

    @Query("SELECT * FROM meal WHERE isFavorite = 1 ORDER BY date")
    LiveData<List<Meal>> getFavoritesList();
    @Query("SELECT * FROM meal WHERE isFavorite = 1 AND idMeal =:idMeal")
    LiveData<Meal> getFavorite(int idMeal);
    @Update
    void addFavorite(Meal meal);

    @Delete
    void removeFavorite(Meal meal);
}
