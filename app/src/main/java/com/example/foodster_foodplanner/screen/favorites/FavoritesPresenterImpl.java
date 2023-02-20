package com.example.foodster_foodplanner.screen.favorites;

import com.example.foodster_foodplanner.Repository.Repository;
import com.example.foodster_foodplanner.models.Meal;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

class FavoritesPresenterImpl implements FavoritesPresenter {
    private final FavoritesView favoritesView;

    private final Repository repository;

    public FavoritesPresenterImpl(FavoritesView favoritesView, Repository repository) {
        this.favoritesView = favoritesView;
        this.repository = repository;
    }

    @Override
    public void getFavorites() {
       Disposable d = repository.getFavoritesList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(favoritesView::showMeals);
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
