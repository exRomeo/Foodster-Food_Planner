package com.example.foodster_foodplanner.screen.planner;

import com.example.foodster_foodplanner.Repository.Repository;
import com.example.foodster_foodplanner.models.Meal;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlannerPresenterImpl implements PlannerPresenter {
    Repository repository;
    PlannerView plannerView;

    PlannerPresenterImpl(Repository repository, PlannerView plannerView) {
        this.repository = repository;
        this.plannerView = plannerView;
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

    @Override
    public void getSaturdayMeals() {
        int day = 1;
        Disposable d = repository.getPlannedMeals(day).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).flatMap(meals -> Flowable.fromIterable(meals)
                .filter(meal -> meal.getDay() == day).toList().toFlowable()).subscribe(items -> plannerView.updateSaturdayMeals(items));
    }

    @Override
    public void getSundayMeals() {
        int day = 2;
        Disposable d = repository.getPlannedMeals(day).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).flatMap(meals -> Flowable.fromIterable(meals)
                .filter(meal -> meal.getDay() == day).toList().toFlowable()).subscribe(items -> plannerView.updateSundayMeals(items));
    }

    @Override
    public void getMondayMeals() {
        int day = 3;
        Disposable d = repository.getPlannedMeals(day).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).flatMap(meals -> Flowable.fromIterable(meals)
                .filter(meal -> meal.getDay() == day).toList().toFlowable()).subscribe(items -> plannerView.updateMondayMeals(items));
    }

    @Override
    public void getTuesdayMeals() {
        int day = 4;
        Disposable d = repository.getPlannedMeals(day).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).flatMap(meals -> Flowable.fromIterable(meals)
                .filter(meal -> meal.getDay() == day).toList().toFlowable()).subscribe(items -> plannerView.updateTuesdayMeals(items));
    }

    @Override
    public void getWednesdayMeals() {
        int day = 5;
        Disposable d = repository.getPlannedMeals(day).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).flatMap(meals -> Flowable.fromIterable(meals)
                .filter(meal -> meal.getDay() == day).toList().toFlowable()).subscribe(items -> plannerView.updateWednesdayMeals(items));
    }

    @Override
    public void getThursdayMeals() {
        int day = 6;
        Disposable d = repository.getPlannedMeals(day).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).flatMap(meals -> Flowable.fromIterable(meals)
                .filter(meal -> meal.getDay() == day).toList().toFlowable()).subscribe(items -> plannerView.updateThursdayMeals(items));
    }

    @Override
    public void getFridayMeals() {
        int day = 7;
        Disposable d = repository.getPlannedMeals(day).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).flatMap(meals -> Flowable.fromIterable(meals)
                .filter(meal -> meal.getDay() == day).toList().toFlowable()).subscribe(items -> plannerView.updateFridayMeals(items));
    }

    @Override
    public void updateRecyclerViews() {
        getSaturdayMeals();
        getSundayMeals();
        getMondayMeals();
        getTuesdayMeals();
        getWednesdayMeals();
        getThursdayMeals();
        getFridayMeals();
    }
}
