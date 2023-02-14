package com.example.foodster_foodplanner.fragments.favorites;

import com.example.foodster_foodplanner.models.Meal;

public interface FavoritesPresenter {
    void getFavorites();
    void addToFavorites(Meal meal);
    void removeMeal(Meal meal);

}
