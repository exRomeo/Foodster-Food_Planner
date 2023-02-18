package com.example.foodster_foodplanner.fragments.home;

import com.example.foodster_foodplanner.models.Meal;

import java.util.Date;
import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface HomePresenter {
    public void getMeals();
    public void addToFavs(Meal meal);
    public void addDailyToDb(Meal meal);
    public void getDailyFromDb(String date);


}
