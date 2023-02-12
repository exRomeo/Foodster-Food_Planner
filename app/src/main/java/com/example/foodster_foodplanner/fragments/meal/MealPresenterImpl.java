package com.example.foodster_foodplanner.fragments.meal;

import com.example.foodster_foodplanner.models.Meal;

public class MealPresenterImpl implements MealPresenter {
    private static Meal _meal;


    @Override
    public void addToFavorites(Meal meal) {

    }

    @Override
    public void removeMeal(Meal meal) {

    }

    @Override
    public String getIngredients(Meal meal) {
        StringBuilder ingredients = new StringBuilder();
        meal.getIngredientsList().forEach(s -> ingredients.append("\n").append(s));
        return ingredients.toString();
    }

    @Override
    public String getMeasures(Meal meal) {
        StringBuilder measures = new StringBuilder();
        meal.getMeasuresList().forEach(s -> measures.append("\n").append(s) );
        return measures.toString();
    }

    public static Meal getMeal() {
        return _meal;
    }

    public static void setMeal(Meal meal) {
        _meal = meal;
    }

}
