package com.example.foodster_foodplanner.fragments.search;

import com.example.foodster_foodplanner.Repository.Repository;
import com.example.foodster_foodplanner.models.Meal;
import com.example.foodster_foodplanner.retrofitclient.NetworkDelegate;
import com.example.foodster_foodplanner.retrofitclient.RetrofitClientImpl;

import java.util.List;

public class SearchPresenterImplementation implements SearchPresenter, NetworkDelegate {

    private RetrofitClientImpl retrofit;
    private Repository repository;
    NameSearchPresenter presenter;
    public SearchPresenterImplementation() {
        //this.repository= repository;
        retrofit = RetrofitClientImpl.getInstance();

    }

    @Override
    public void searchByMealName(String mealName) {
        retrofit.searchByName(this, mealName);

    }

    @Override
    public void addToFavorites() {

    }

    @Override
    public void onResponseSuccess(List<Meal> meals) {
        presenter.getMeals(meals);
    }

    @Override
    public void onResponseFailure(String errorMessage) {

    }
}
