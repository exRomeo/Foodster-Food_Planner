package com.example.foodster_foodplanner.fragments.filters;

import androidx.fragment.app.Fragment;

import com.example.foodster_foodplanner.Repository.Repository;
import com.example.foodster_foodplanner.models.Meal;
import com.example.foodster_foodplanner.retrofitclient.NetworkDelegate;

import java.util.List;

import io.reactivex.rxjava3.disposables.Disposable;

public class FilterPresenterImpl implements FilterPresenter, NetworkDelegate {
    Repository repository;
    FilterView filterView;

    public FilterPresenterImpl(Repository repository, FilterView filterView) {
        this.repository = repository;
        this.filterView = filterView;
    }

    @Override
    public void getCountryList() {
        repository.getCountryList(this);
    }

    @Override
    public void getCategoryList() {repository.getCategoryList(this);}

    @Override
    public void getIngredientsList() {
        repository.getIngredientsList(this);
    }

    @Override
    public void filterByCountry(String country) {
        Disposable d =repository.filterByCountry(country).subscribe(mealModel -> filterView.showMeals(mealModel.getMeals()));
    }

    @Override
    public void filterByCategory(String category) {
        Disposable d =repository.filterByCategory(category).subscribe(mealModel -> filterView.showMeals(mealModel.getMeals()));
    }

    @Override
    public void filterByIngredient(String ingredient) {
        Disposable d =repository.filterByIngredient(ingredient).subscribe(mealModel -> filterView.showMeals(mealModel.getMeals()));
    }

    @Override
    public void addToFavorites(Meal meal) {
        Disposable d = repository.getMealByID(meal.getIdMeal()).subscribe(mealModel -> repository.addFavorite(mealModel.getMeals().get(0)));

    }

    @Override
    public String getFilterType() {
        return ((Fragment) filterView).getArguments().getString("filter_by");
    }

    @Override
    public void getMealByID(int id) {
       Disposable d = repository.getMealByID(id).subscribe(mealModel -> {
            filterView.navigateToMeal(mealModel.getMeals().get(0));
        });
    }

    @Override
    public void onResponseSuccess(List<Meal> meals) {
        filterView.showFilter(meals);
    }

    @Override
    public void onResponseFailure(String errorMessage) {
        filterView.showError(errorMessage);
    }
}
