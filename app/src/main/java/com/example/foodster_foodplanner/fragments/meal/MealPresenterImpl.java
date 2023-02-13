package com.example.foodster_foodplanner.fragments.meal;

import com.example.foodster_foodplanner.Repository.Repository;
import com.example.foodster_foodplanner.models.Meal;

import java.util.Objects;

public class MealPresenterImpl implements MealPresenter {
    private static Meal _meal;
    private final Repository repository;

    public MealPresenterImpl(Repository repository) {
        this.repository = repository;
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
            if (!Objects.equals(s, "") && s != null) {
                measures.append("\n").append(s);
            }
        });
        return measures.toString();
    }

    public static Meal getMeal() {
        return _meal;
    }

    public static void setMeal(Meal meal) {
        _meal = meal;
    }

    public void planMeal(Meal meal, int day) {
        repository.planMeal(meal, day);
    }

}
