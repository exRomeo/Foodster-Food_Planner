package com.example.foodster_foodplanner.fragments.search;

import com.example.foodster_foodplanner.Repository.Repository;
import com.example.foodster_foodplanner.Repository.RepositoryImpl;
import com.example.foodster_foodplanner.localdatabase.LocalDatabaseSource;
import com.example.foodster_foodplanner.localdatabase.RoomInterface;
import com.example.foodster_foodplanner.models.Meal;
import com.example.foodster_foodplanner.retrofitclient.RetrofitClient;

public class NamePresenterImpl implements NameSearchPresenter{

      Repository repo;
    public NamePresenterImpl(Repository instance) {
        repo=instance;
    }

    @Override
    public void addToFavorites(Meal meal) {
       repo.addFavorite(meal);
    }
}
