package com.example.foodster_foodplanner.retrofitclient;

public interface RetrofitClient {

    void getRandomMeal(NetworkDelegate networkDelegate);

    void searchByName(NetworkDelegate networkDelegate, String strMeal);

    void listByFirstLetter(NetworkDelegate networkDelegate, char firstLetter);
}

