package com.example.foodster_foodplanner.Fragments.Favorites;

import com.example.foodster_foodplanner.models.Meal;

public interface FavoritesPresenter {
    void getFavorites();
    void getMeal(int id);
    void addToFavorites(Meal meal);
    void removeMeal(Meal meal);

}
