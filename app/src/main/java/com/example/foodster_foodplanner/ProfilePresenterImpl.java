package com.example.foodster_foodplanner;

import com.example.foodster_foodplanner.Repository.Repository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ProfilePresenterImpl implements ProfilePresenter{
    private final Repository repository;
    private final ProfileView profileView;

    public ProfilePresenterImpl(Repository repository, ProfileView profileView) {
        this.repository = repository;
        this.profileView = profileView;
    }

    @Override
    public void getFavoritesCount() {
        Disposable d =repository.getFavoritesList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals -> profileView.setFavoritesCount(String.valueOf(meals.size())),
                        e->profileView.setFavoritesCount("None"));
    }

    @Override
    public void getPlannedCount() {
        Disposable d =repository.getAllPlannedMeals()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meals -> profileView.setPlannedCount(String.valueOf(meals.size())),
                        e->profileView.setPlannedCount("None"));
    }
}
