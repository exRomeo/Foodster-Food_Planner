package com.example.foodster_foodplanner.Repository;

import com.example.foodster_foodplanner.localdatabase.RoomInterface;
import com.example.foodster_foodplanner.models.Meal;
import com.example.foodster_foodplanner.retrofitclient.RetrofitClient;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class RepositoryImpl implements Repository{
    private static Repository repository;
    private RetrofitClient retrofitClient;
    private RoomInterface roomInterface;

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
        roomInterface.removeFavoriteMeal(meal);
    }

    @Override
    public void getFavoriteById(int id) {
        roomInterface.getFavoriteMeal(id);
    }

    @Override
    public void addFavorite(Meal meal) {
        roomInterface.addToFavorites(meal);

    }
}
