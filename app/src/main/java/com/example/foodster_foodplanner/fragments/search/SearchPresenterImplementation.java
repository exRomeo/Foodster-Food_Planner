package com.example.foodster_foodplanner.fragments.search;

import android.util.Log;

import com.example.foodster_foodplanner.Repository.Repository;
import com.example.foodster_foodplanner.models.Meal;
import com.example.foodster_foodplanner.retrofitclient.NetworkDelegate;
import com.example.foodster_foodplanner.retrofitclient.RetrofitClientImpl;

import java.util.List;

public class SearchPresenterImplementation implements SearchPresenter, NetworkDelegate {

    private RetrofitClientImpl retrofit;
    private Repository repository;
    NameSearchView view;

    public SearchPresenterImplementation(NameSearchView view) {
        this.view=view;
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
           view.showResults(meals);
    }

    @Override
    public void onResponseFailure(String errorMessage) {
        view.showErr(errorMessage);
    }
}
