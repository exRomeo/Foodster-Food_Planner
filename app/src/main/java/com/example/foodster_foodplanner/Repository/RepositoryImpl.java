package com.example.foodster_foodplanner.Repository;

import com.example.foodster_foodplanner.localdatabase.RoomInterface;
import com.example.foodster_foodplanner.models.Meal;
import com.example.foodster_foodplanner.retrofitclient.RetrofitClient;

import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class RepositoryImpl implements Repository{
    private static Repository repository;
    private final RetrofitClient retrofitClient;
    private final RoomInterface roomInterface;

    private RepositoryImpl(RetrofitClient retrofitClient, RoomInterface roomInterface){
        this.retrofitClient = retrofitClient;
        this.roomInterface = roomInterface;
    }
    public static Repository getInstance(RetrofitClient retrofitClient,RoomInterface roomIntervace){
        if(repository == null)
            repository = new RepositoryImpl(retrofitClient, roomIntervace);
        return repository;
    }
    @Override
    public Flowable<List<Meal>> getFavoritesList() {
        return roomInterface.getListOfFavorites();
    }

    @Override
    public void removeFavorite(Meal meal) {
        meal.setFavorite(false);
        roomInterface.updateMeal(meal);
    }

    @Override
    public Flowable<Meal> getFavoriteById(int id) {
        return roomInterface.getFavoriteMeal(id);
    }

    @Override
    public void addFavorite(Meal meal) {
        meal.setFavorite(true);
        roomInterface.updateMeal(meal);

    }

    @Override
    public void planMeal(Meal meal, int day) {
        meal.setDay(day);
        roomInterface.updateMeal(meal);
    }

    @Override
    public Flowable<List<Meal>> getPlannedMeals(int day) {
        return roomInterface.getPlannedMeals(day);
    }

    @Override
    public Flowable<List<Meal>> getDailyMeals(Date date) {
        return roomInterface.getListOfDaily(date);
    }

    @Override
    public void insertMeal(Meal meal) {
        roomInterface.insertDaily(meal);
    }
}
