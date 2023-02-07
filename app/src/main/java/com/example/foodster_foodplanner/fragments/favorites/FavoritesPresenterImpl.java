package com.example.foodster_foodplanner.fragments.favorites;

import com.example.foodster_foodplanner.models.Meal;

class FavoritesPresenterImpl implements FavoritesPresenter {
    //    TODO waiting on repository >:c
    private final FavoritesView favoritesView;

    //    private final Repository repository;
    public FavoritesPresenterImpl(FavoritesView favoritesView) {
        this.favoritesView = favoritesView;
    }

    @Override
    public void getFavorites() {
//        repository.getFavorites().observe(((FavoritesFragment)favoritesView).requireActivity(), favoritesView::showMeals);
    }

    @Override
    public void getMeal(int id) {
        //repository.getFavoriteById(id);
    }

    @Override
    public void addToFavorites(Meal meal) {
        //repository.add(meal);
    }

    @Override
    public void removeMeal(Meal meal) {
        //repository.remove(meal);
    }
}
