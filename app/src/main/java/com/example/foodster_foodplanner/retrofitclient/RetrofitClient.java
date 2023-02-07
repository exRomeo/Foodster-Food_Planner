package com.example.foodster_foodplanner.retrofitclient;

import androidx.annotation.NonNull;

import com.example.foodster_foodplanner.models.Meal;
import com.example.foodster_foodplanner.models.MealModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient implements RetrofitClientInterface {

    List<Meal> mealList;
    private static RetrofitClient retrofitClient;
    private final API api;

    public RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(API.class);
    }

    public static synchronized RetrofitClient getInstance() {
        if (retrofitClient == null)
            retrofitClient = new RetrofitClient();
        return retrofitClient;
    }

    @Override
    public void getRandomMeal(NetworkDelegate networkDelegate) {
        //TODO
    }

    @Override
    public void searchByName(NetworkDelegate networkDelegate, String strMeal) {
        Call<MealModel> meal = api.getMealByName(strMeal);
        meal.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<MealModel> call, @NonNull Response<MealModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mealList = response.body().getMeals();
                    networkDelegate.onResponseSuccess(mealList);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MealModel> call, @NonNull Throwable t) {
                networkDelegate.onResponseFailure(t.getMessage());
            }
        });

    }

    @Override
    public void listByFirstLetter(NetworkDelegate networkDelegate, char firstLetter) {
        Call<MealModel> meal = api.getMealByFirstLetter(firstLetter);
        meal.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<MealModel> call, @NonNull Response<MealModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mealList = response.body().getMeals();
                    networkDelegate.onResponseSuccess(mealList);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MealModel> call, @NonNull Throwable t) {
                networkDelegate.onResponseFailure(t.getMessage());
            }
        });
    }
}
