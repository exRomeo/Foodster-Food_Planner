package com.example.foodster_foodplanner.screen.profile;

public interface ProfilePresenter {
    void getFavoritesCount();
    void getPlannedCount();

    void removeUserData();
    void backupFavorites();
}
