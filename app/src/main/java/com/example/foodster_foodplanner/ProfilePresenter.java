package com.example.foodster_foodplanner;

public interface ProfilePresenter {
    void getFavoritesCount();
    void getPlannedCount();

    void removeUserData();
    void backupFavorites();
}
