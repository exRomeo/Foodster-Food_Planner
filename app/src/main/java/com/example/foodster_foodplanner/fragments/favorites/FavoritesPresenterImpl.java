package com.example.foodster_foodplanner.fragments.favorites;

import com.example.foodster_foodplanner.Repository.Repository;
import com.example.foodster_foodplanner.models.Meal;

class FavoritesPresenterImpl implements FavoritesPresenter {
    private final FavoritesView favoritesView;

        private final Repository repository;
    public FavoritesPresenterImpl(FavoritesView favoritesView, Repository repository) {
        this.favoritesView = favoritesView;
        this.repository = repository;
    }

    @Override
    public void getFavorites() {
        repository.getFavoritesList().subscribe(favoritesView::showMeals);
    }

    @Override
    public void getMeal(int id) {
        repository.getFavoriteById(id);
    }

    @Override
    public void addToFavorites(Meal meal) {
        meal.setFavorite(true);
        repository.addFavorite(meal);
    }

    @Override
    public void removeMeal(Meal meal) {
        repository.removeFavorite(meal);
    }
}
