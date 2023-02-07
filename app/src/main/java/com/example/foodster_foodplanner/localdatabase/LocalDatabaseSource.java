package com.example.foodster_foodplanner.localdatabase;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Database;

import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

@Database(entities = {Meal.class}, version = 1, exportSchema = false)
public class LocalDatabaseSource implements RoomInterface {

    private static LocalDatabaseSource LOCAL_INSTANCE;
    private MealDao mealDao;
    private LiveData<List<Meal>> mealsList;
    Context context;

    public LocalDatabaseSource(Context context) {
        this.context = context;
        LocalDatabase db = LocalDatabase.getInstance(context.getApplicationContext());
        mealDao = db.mealDao();
    }

    @Override
    public void insertToFavorites(Meal meal) {

    }

    @Override
    public void insertDaily(Meal meal) {

    }

    @Override
    public void insertWeekly(Meal meal) {

    }

    @Override
    public List<Meal> getListOfFavorites() {
        return null;
    }

    @Override
    public List<Meal> getListOfDaily() {
        return null;
    }

    @Override
    public List<Meal> getListOfWeekly() {
        return null;
    }

    @Override
    public Meal getFavoriteMeal(int idMeal) {
        return null;
    }

    @Override
    public Meal getDailyMeal(int idMeal) {
        return null;
    }

    @Override
    public Meal getWeeklyMeal(int idMeal) {
        return null;
    }

    @Override
    public void removeFavoriteMeal(int idMeal) {

    }

    @Override
    public void removeWeeklyMeal(int idMeal) {

    }
}
