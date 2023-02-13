package com.example.foodster_foodplanner.fragments.planner;

import com.example.foodster_foodplanner.Repository.Repository;
import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

public class PlannerPresenterImpl implements PlannerPresenter {
    Repository repository;

    PlannerPresenterImpl(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void addToPlan(Meal meal) {
        repository.addFavorite(meal);
    }

    @Override
    public void removePlan(Meal meal) {
        repository.addFavorite(meal);
    }

    @Override
    public List<Meal> getMealList() {
        return repository.getFavoritesList().toList().blockingGet().get(0);
    }
}
