package com.example.foodster_foodplanner.localdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.foodster_foodplanner.models.Meal;

@Database(entities = {Meal.class}, version = 2, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class LocalDatabase extends RoomDatabase {
    public static LocalDatabase DATABASE_INSTANCE = null;
    public abstract MealDao mealDao();
    public static synchronized LocalDatabase getInstance(Context context){
        if (DATABASE_INSTANCE == null){
            DATABASE_INSTANCE = Room.databaseBuilder(context.getApplicationContext(), LocalDatabase.class, "mealDB").build(); }
        return DATABASE_INSTANCE; }
}
