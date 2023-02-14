package com.example.foodster_foodplanner.localdatabase;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Database;

import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class LocalDatabaseSource implements RoomInterface {

    private static LocalDatabaseSource LOCAL_INSTANCE = null;
    private Context context;
    private MealDao mealDao;
    private LiveData<List<Meal>> mealsList;


    private LocalDatabaseSource(Context context) {
        this.context = context;
        LocalDatabase db = LocalDatabase.getInstance(context.getApplicationContext());
        mealDao = db.mealDao();
    }

    public static LocalDatabaseSource getInstance(Context context) {
        if (LOCAL_INSTANCE == null)
            LOCAL_INSTANCE = new LocalDatabaseSource(context);

        return LOCAL_INSTANCE;
    }


    @Override
    public void addToFavorites(Meal meal) {
        mealDao.addFavorite(meal);
    }

    @Override
    public void insertDaily(List<Meal> meals) {

    }

    @Override
    public void insertWeekly(Meal meal) {

    }

    @Override
    public Flowable<List<Meal>> getListOfFavorites() {
        return mealDao.getFavoritesList();
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
    public Flowable<Meal> getFavoriteMeal(int idMeal) {
        return mealDao.getFavorite(idMeal);
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
    public void removeFavoriteMeal(Meal meal) {
        mealDao.removeFavorite(meal);
    }

    @Override
    public void removeWeeklyMeal(int idMeal) {

    }
}
