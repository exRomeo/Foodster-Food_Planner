package com.example.foodster_foodplanner.Repository;

import androidx.lifecycle.LiveData;

import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

public interface Repository {

    LiveData<List<Meal>> getFavoritesList();

    void removeFavorite(Meal meal);

    void getFavoriteById(int id);

    void addFavorite(Meal meal);


}
