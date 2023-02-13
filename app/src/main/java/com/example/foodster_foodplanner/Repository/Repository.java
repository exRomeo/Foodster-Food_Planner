package com.example.foodster_foodplanner.Repository;

import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface Repository {

    Flowable<List<Meal>> getFavoritesList();

    void removeFavorite(Meal meal);

    Flowable<Meal> getFavoriteById(int id);

    void addFavorite(Meal meal);


}
