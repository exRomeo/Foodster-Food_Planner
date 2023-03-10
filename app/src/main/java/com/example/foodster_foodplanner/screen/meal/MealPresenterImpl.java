package com.example.foodster_foodplanner.screen.meal;

import androidx.fragment.app.Fragment;

import com.example.foodster_foodplanner.Repository.Repository;
import com.example.foodster_foodplanner.models.Meal;

import java.util.Objects;

public class MealPresenterImpl implements MealPresenter {
    private final Repository repository;
    private final MealView mealView;

    public MealPresenterImpl(MealView mealView, Repository repository) {
        this.repository = repository;
        this.mealView = mealView;
    }

    @Override
    public void addToFavorites(Meal meal) {
        repository.addFavorite(meal);
    }

    @Override
    public void removeMeal(Meal meal) {
        repository.removeFavorite(meal);
    }

    @Override
    public String getIngredients(Meal meal) {
        StringBuilder ingredients = new StringBuilder();
        meal.getIngredientsList().forEach(s -> {
            if (!Objects.equals(s, "") && s != null) {
                ingredients.append("\n").append(s);
            }
        });
        return ingredients.toString();
    }

    @Override
    public String getMeasures(Meal meal) {
        StringBuilder measures = new StringBuilder();
        meal.getMeasuresList().forEach(s -> {
            if (!Objects.equals(s, "") && !Objects.equals(s, " ") && s != null) {
                measures.append("\n").append(s);
            }
        });
        return measures.toString();
    }

    @Override
    public Meal getMeal() {
        return (Meal)((Fragment) mealView).getArguments().getSerializable("meal") ;
    }

    @Override
    public void planMeal(Meal meal, int day) {
        repository.planMeal(meal, day);
    }

}
