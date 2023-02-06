package com.example.foodster_foodplanner.retrofitclient;

public interface RetrofitClientInterface {
    void getRandomMeal(NetworkDelegate networkDelegate);

    void searchByName(NetworkDelegate networkDelegate, String strMeal);

    void listByFirstLetter(NetworkDelegate networkDelegate, char firstLetter);
}

