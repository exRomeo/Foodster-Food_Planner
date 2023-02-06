package com.example.foodster_foodplanner.retrofitclient;

public interface RetrofitClientInterface {
    void getRandomMeal();

    void searchByName(String strMeal);

    void listByFirstLetter(char firstLetter);
}

