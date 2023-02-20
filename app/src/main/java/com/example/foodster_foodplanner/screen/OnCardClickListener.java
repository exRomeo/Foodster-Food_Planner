package com.example.foodster_foodplanner.screen;

import com.example.foodster_foodplanner.models.Meal;

public interface OnCardClickListener {
    void onFavoriteClick(Meal meal);
    void onCardClick(Meal meal);
}
