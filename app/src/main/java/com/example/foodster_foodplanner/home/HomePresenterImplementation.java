package com.example.foodster_foodplanner.home;

import android.util.Log;


import com.example.foodster_foodplanner.Repository.Repository;
import com.example.foodster_foodplanner.Repository.RepositoryImpl;
import com.example.foodster_foodplanner.localdatabase.LocalDatabaseSource;
import com.example.foodster_foodplanner.models.Meal;
import com.example.foodster_foodplanner.retrofitclient.NetworkDelegate;
import com.example.foodster_foodplanner.retrofitclient.RetrofitClientImpl;

import java.util.List;

public class HomePresenterImplementation implements HomePresenter, NetworkDelegate {
    private RetrofitClientImpl retrofit;
    private Repository repository;
    private HomeView view;

    public HomePresenterImplementation(HomeView view, Repository repository){
     this.repository= repository;
      this.view=view;
      retrofit= RetrofitClientImpl.getInstance();
    }
    @Override
    public void getMeals() {
        for (int i = 0; i < 9; i++) {
            retrofit.getRandomMeal(this);
            Log.i("trace", "createCall: here ");
        }
    }

    @Override
    public void addToFavs(Meal meal) {
        repository.addFavorite(meal);
    }

    @Override
    public void addDailyToDb(List<Meal> dailyTen) {

    }

    @Override
    public void getDailyFromDb() {

    }

    @Override
    public boolean mealsFound() {
        return false;
    }

    @Override
    public void onResponseSuccess(List<Meal> meals) {
        view.showDailyMeals(meals);
    }

    @Override
    public void onResponseFailure(String errorMessage) {
        view.showError(errorMessage);
    }
}
