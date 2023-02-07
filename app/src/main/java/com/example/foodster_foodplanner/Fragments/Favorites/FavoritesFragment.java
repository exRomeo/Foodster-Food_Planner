package com.example.foodster_foodplanner.Fragments.Favorites;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.foodster_foodplanner.Fragments.CardsGridAdapter;
import com.example.foodster_foodplanner.Fragments.OnCardClickListener;
import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.databinding.FragmentFavoritesBinding;
import com.example.foodster_foodplanner.models.Meal;

import java.util.ArrayList;
import java.util.List;


public class FavoritesFragment extends Fragment implements OnCardClickListener, FavoritesView {
    FragmentFavoritesBinding binder;
    CardsGridAdapter cardsGridAdapter;
    FavoritesPresenter presenter;
    List<Meal> mealList;

    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        presenter = new FavoritesPresenterImpl(this);
        mealList = new ArrayList<>();
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //TODO ------ TO.. DONE? >:D
        binder = FragmentFavoritesBinding.bind(view);
        cardsGridAdapter = new CardsGridAdapter(this.requireContext(),
                mealList, this, R.drawable.cross);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.requireContext(), 2);
        binder.mealsGrid.setLayoutManager(gridLayoutManager);
        binder.mealsGrid.setAdapter(cardsGridAdapter);
        presenter.getFavorites();
    }

    @Override
    public void onClick(Meal meal) {
        removeButton(meal);
    }
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void showMeals(List<Meal> meals) {
        mealList = meals;
        cardsGridAdapter.notifyDataSetChanged();
    }
    @Override
    public void removeButton(Meal meal) {
        presenter.removeMeal(meal);
        Toast.makeText(this.requireContext(), meal.getStrMeal() + " Removed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this.requireContext(),message, Toast.LENGTH_SHORT).show();
    }
}