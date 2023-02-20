package com.example.foodster_foodplanner.screen.search;

import com.example.foodster_foodplanner.Repository.Repository;
import com.example.foodster_foodplanner.models.Meal;

public class NamePresenterImpl implements NameSearchPresenter {

    Repository repo;

    public NamePresenterImpl(Repository instance) {
        repo = instance;
    }

    @Override
    public void addToFavorites(Meal meal) {
        repo.addFavorite(meal);
    }
}
