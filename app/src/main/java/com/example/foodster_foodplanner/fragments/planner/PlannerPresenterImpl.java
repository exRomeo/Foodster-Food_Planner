package com.example.foodster_foodplanner.fragments.planner;

import com.example.foodster_foodplanner.Repository.Repository;
import com.example.foodster_foodplanner.models.Meal;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlannerPresenterImpl implements PlannerPresenter {
    Repository repository;
    PlannerView plannerView;

    PlannerPresenterImpl(Repository repository,PlannerView plannerView) {
        this.repository = repository;
        this.plannerView=plannerView;
    }

    @Override
    public void addToPlan(Meal meal, int day) {
        meal.setDay(day);
        repository.addFavorite(meal);
    }

    @Override
    public void removePlan(Meal meal) {
        repository.addFavorite(meal);
    }

    @Override
    public void getMealList() {
       Disposable d = repository.getFavoritesList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(items -> plannerView.updateList(items));
    }
}
