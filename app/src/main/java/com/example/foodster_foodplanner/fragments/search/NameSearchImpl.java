package com.example.foodster_foodplanner.fragments.search;

import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

public class NameSearchImpl implements NameSearchPresenter{
    NameSearchView view;

    public NameSearchImpl(NameSearchView view){
        this.view=view;
    }
    @Override
    public void getMeals(List<Meal> searchResult) {
        view.showResults(searchResult);
    }

    @Override
    public void showError(String error) {
        view.showErr(error);
    }
}
