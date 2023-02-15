package com.example.foodster_foodplanner.home;

import android.util.Log;


import com.example.foodster_foodplanner.Repository.Repository;
import com.example.foodster_foodplanner.Repository.RepositoryImpl;
import com.example.foodster_foodplanner.localdatabase.LocalDatabaseSource;
import com.example.foodster_foodplanner.models.Meal;
import com.example.foodster_foodplanner.retrofitclient.NetworkDelegate;
import com.example.foodster_foodplanner.retrofitclient.RetrofitClientImpl;

import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenterImplementation implements HomePresenter, NetworkDelegate {
    private RetrofitClientImpl retrofit;
    private Repository repository;
    private HomeView view;


    public HomePresenterImplementation(HomeView view, Repository repository) {
        this.repository = repository;
        this.view = view;
        retrofit = RetrofitClientImpl.getInstance();
    }

    @Override
    public void getMeals() {
        for (int i = 0; i < 9; i++) {
            retrofit.getRandomMeal(this);
            Log.i("trace", "get mealss ");
        }
    }

    @Override
    public void addToFavs(Meal meal) {
        repository.addFavorite(meal);
    }

    @Override
    public void addDailyToDb(Meal meal) {
        repository.insertMeal(meal);
    }

    @Override
    public void getDailyFromDb(String date) {
        repository.getDailyMeals(date).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(item -> {
                            if (item == null) {
                                getMeals();
                                Log.i("test", "nulll ");
                            } else {
                                view.showFromDataBase(item);
                                Log.i("test", "not null ");
                            }
                        },
                        throwable -> {
                            getDailyFromDb(date);
                        }
    );
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
