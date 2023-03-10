package com.example.foodster_foodplanner.localdatabase;

import android.content.Context;

import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class LocalDatabaseSource implements RoomInterface {

    private static LocalDatabaseSource LOCAL_INSTANCE = null;
    private final MealDao mealDao;


    private LocalDatabaseSource(Context context) {
        LocalDatabase db = LocalDatabase.getInstance(context.getApplicationContext());
        mealDao = db.mealDao();
    }

    public static LocalDatabaseSource getInstance(Context context) {
        if (LOCAL_INSTANCE == null)
            LOCAL_INSTANCE = new LocalDatabaseSource(context);

        return LOCAL_INSTANCE;
    }


    @Override
    public void updateMeal(Meal meal) {
        new Thread(() -> mealDao.updateMeal(meal)).start();
    }

    @Override
    public void insertDaily(Meal meal) {
         new Thread(()->mealDao.insertMeal(meal)).start();
    }

    @Override
    public void insertWeekly(Meal meal) {

    }

    @Override
    public Flowable<List<Meal>> getListOfFavorites() {
        return mealDao.getFavoritesList();
    }

    @Override
    public Flowable<List<Meal>> getPlannedMeals(int day) {
        return mealDao.getPlannedMeals(day);
    }

    @Override
    public Flowable<List<Meal>> getAllPlannedMeals() {
        return mealDao.getAllPlannedMeals();
    }

    @Override
    public Flowable<List<Meal>> getListOfDaily(String date) {
        return mealDao.getDailyMeals(date);
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
        new Thread(() -> mealDao.removeFavorite(meal)).start();
    }

    @Override
    public void removeWeeklyMeal(int idMeal) {

    }

    @Override
    public void emptyDatabase() {
        new Thread(mealDao::emptyDatabase).start();
    }

}
