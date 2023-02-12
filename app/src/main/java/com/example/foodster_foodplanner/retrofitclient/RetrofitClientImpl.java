package com.example.foodster_foodplanner.retrofitclient;


import com.example.foodster_foodplanner.models.Meal;
import com.example.foodster_foodplanner.models.MealModel;

import java.util.List;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientImpl implements RetrofitClient {

    List<Meal> mealList;
    private static RetrofitClientImpl retrofitClientImpl;
    private final API api;

    public RetrofitClientImpl() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        api = retrofit.create(API.class);
    }

    public static synchronized RetrofitClientImpl getInstance() {
        if (retrofitClientImpl == null)
            retrofitClientImpl = new RetrofitClientImpl();
        return retrofitClientImpl;
    }

    @Override
    public void getRandomMeal(NetworkDelegate networkDelegate) {
        //TODO
        /**  for (int i = 0; i <= 9; i++) {
         Call<MealModel> randomMeal = api.getRandomMeal();
         randomMeal.enqueue(new Callback<MealModel>() {
        @Override public void onResponse(Call<MealModel> call, Response<MealModel> response) {
        if (response.isSuccessful() && response.body() != null) {
        mealList.addAll(response.body().getMeals());
        networkDelegate.onResponseSuccess(mealList);
        }
        }

        @Override public void onFailure(Call<MealModel> call, Throwable t) {
        networkDelegate.onResponseFailure(t.getMessage());
        }
        });
         }**/
    }

    @Override
    public void searchByName(NetworkDelegate networkDelegate, String strMeal) {
        Observable<MealModel> meal = api.getMealByName(strMeal);
        Disposable d = meal.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(10)
                .subscribe(mealModel -> networkDelegate.onResponseSuccess(mealModel.getMeals()),
                        throwable -> networkDelegate.onResponseFailure(throwable.getMessage()));
    }

    @Override
    public void listByFirstLetter(NetworkDelegate networkDelegate, char firstLetter) {
        Observable<MealModel> meal = api.getMealByFirstLetter(firstLetter);
        Disposable d = meal.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(10)
                .subscribe(mealModel -> networkDelegate.onResponseSuccess(mealModel.getMeals()),
                        throwable -> networkDelegate.onResponseFailure(throwable.getMessage()));
    }
}
