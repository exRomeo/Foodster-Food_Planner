package com.example.foodster_foodplanner.RemoteDataSource;

import android.util.Log;

import com.example.foodster_foodplanner.models.Meal;
import com.example.foodster_foodplanner.models.MealModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient implements ClientInterface {
    private static RetrofitClient retrofitClient;
    private List<Meal> mealList;
    private final API api;


    private RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(API.class);
    }

    public static synchronized RetrofitClient getInstance(){
        if(retrofitClient == null)
            retrofitClient = new RetrofitClient();
        return retrofitClient;
    }


    @Override
    public void fetchARandom(NetworkDelegate networkDelegate) {
        Call<MealModel> randomMeal = api.getRandomMeal();
        randomMeal.enqueue(new Callback<>(){
            @Override
            public void onResponse(Call<MealModel> call, Response<MealModel> response) {
               mealList = response.body().getMeals();
                Log.i("TAG", "onResponse: " + mealList.get(0).getStrArea());
               networkDelegate.onResponseSuccess(mealList);
            }

            @Override
            public void onFailure(Call<MealModel> call, Throwable t) {

                networkDelegate.onResponseFailure(t.getMessage());
            }
        });



    }
}
