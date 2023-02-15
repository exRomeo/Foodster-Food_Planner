package com.example.foodster_foodplanner.localdatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Upsert;

import com.example.foodster_foodplanner.models.Meal;

import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

@Dao
public interface MealDao {

    @Query("SELECT * FROM meal WHERE isFavorite = 1")
    Flowable<List<Meal>> getFavoritesList();
    @Query("SELECT * FROM meal WHERE isFavorite = 1 AND idMeal =:idMeal")
    Flowable<Meal> getFavorite(int idMeal);
    @Upsert
    void updateMeal(Meal meal);

    @Delete
    void removeFavorite(Meal meal);
    @Query("SELECT * FROM meal WHERE day = :day")
    Flowable<List<Meal>> getPlannedMeals(int day);

    @Query("select * from meal where date= :date")
    Flowable<List<Meal>> getDailyMeals(Date date);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertMeal(Meal meal);
}
