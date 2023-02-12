package com.example.foodster_foodplanner.retrofitclient;

import androidx.annotation.NonNull;

import com.example.foodster_foodplanner.models.Meal;
import com.example.foodster_foodplanner.models.MealModel;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
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
        Observable<MealModel> randomCall = api.getRandomMeal();

        Observer<MealModel> observer =new Observer<MealModel>() {


            @Override
            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

            }

            @Override
            public void onNext(@io.reactivex.rxjava3.annotations.NonNull MealModel mealModel) {
                   networkDelegate.onResponseSuccess(mealModel.getMeals());
            }

            @Override
            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
               networkDelegate.onResponseFailure(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        };
        randomCall.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(observer);
       // randomCall.subscribe(observer);
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
