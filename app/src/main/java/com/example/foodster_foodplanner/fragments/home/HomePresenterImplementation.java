package com.example.foodster_foodplanner.fragments.home;


import com.example.foodster_foodplanner.Repository.Repository;
import com.example.foodster_foodplanner.models.Meal;
import com.example.foodster_foodplanner.retrofitclient.NetworkDelegate;
import com.example.foodster_foodplanner.retrofitclient.RetrofitClientImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class HomePresenterImplementation implements HomePresenter, NetworkDelegate {
    private RetrofitClientImpl retrofit;
    private Repository repository;
    private HomeView view;


    public HomePresenterImplementation(HomeView view, Repository repository) {
        this.repository = repository;
        this.view = view;
        retrofit = RetrofitClientImpl.getInstance();
    }

    @Override
    public void getMeals() {
        for (int i = 0; i < 9; i++) {
            retrofit.getRandomMeal(this);
        }
    }

    @Override
    public void addToFavs(Meal meal) {
        repository.addFavorite(meal);
    }

    @Override
    public void addDailyToDb(Meal meal) {
        repository.insertMeal(meal);
    }

    @Override
    public void getDailyFromDb(String date) {
        repository.getDailyMeals(date).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(item -> {
                    if(item.isEmpty()){
                        getMeals();
                    }
                    view.showFromDataBase(item);
                });
    }

    @Override
    public void getCountryMeals() {
        String name = generateCountryName();
        repository.filterByCountry(name).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).subscribe(item -> view.showFromCountry(item.getMeals()));
        view.setName(name);
    }

    @Override
    public void onResponseSuccess(List<Meal> meals) {
        view.showDailyMeals(meals);
    }

    @Override
    public void onResponseFailure(String errorMessage) {
        view.showError(errorMessage);
    }

    @Override
    public void getMealByID(int id) {
        Disposable d = repository.getMealByID(id).subscribe(mealModel -> {
            view.goToMeal(mealModel.getMeals().get(0));
        });
    }

    public String generateCountryName(){
        Random random = new Random();
        int index= random.nextInt(10);

        ArrayList<String> countries= new ArrayList<>();

        countries.add("Egyptian");
        countries.add("Indian");
        countries.add("Chinese");
        countries.add("English");
        countries.add("American");
        countries.add("Spanish");
        countries.add("Japanese");
        countries.add("Thai");
        countries.add("Mexican");
        countries.add("Canadian");

        return countries.get(index);
    }
}
