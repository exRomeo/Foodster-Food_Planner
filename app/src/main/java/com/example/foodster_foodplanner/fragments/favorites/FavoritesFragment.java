package com.example.foodster_foodplanner.fragments.favorites;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.Repository.RepositoryImpl;
import com.example.foodster_foodplanner.databinding.FragmentFavoritesBinding;
import com.example.foodster_foodplanner.fragments.CardsGridAdapter;
import com.example.foodster_foodplanner.fragments.OnCardClickListener;
import com.example.foodster_foodplanner.fragments.meal.MealFragment;
import com.example.foodster_foodplanner.fragments.meal.MealPresenterImpl;
import com.example.foodster_foodplanner.localdatabase.LocalDatabaseSource;
import com.example.foodster_foodplanner.models.Meal;
import com.example.foodster_foodplanner.retrofitclient.RetrofitClientImpl;

import java.util.ArrayList;
import java.util.List;


public class FavoritesFragment extends Fragment implements OnCardClickListener, FavoritesView {
    FragmentFavoritesBinding binder;
    CardsGridAdapter cardsGridAdapter;
    FavoritesPresenter presenter;


    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        presenter = new FavoritesPresenterImpl(this,RepositoryImpl.getInstance(RetrofitClientImpl.getInstance(), LocalDatabaseSource.getInstance(this.requireContext())));

        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binder = FragmentFavoritesBinding.bind(view);
        cardsGridAdapter = new CardsGridAdapter(this.requireContext(),
                new ArrayList<>(), this, R.drawable.cross);
        binder.mealsGrid.setAdapter(cardsGridAdapter);
        presenter.getFavorites();
    }

    @Override
    public void onFavoriteClick(Meal meal) {
        removeButton(meal);
    }

    @Override
    public void onCardClick(Meal meal) {
        MealPresenterImpl.setMeal(meal);
        Fragment fragment = new MealFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFragmentContainer, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void showMeals(List<Meal> meals) {
        cardsGridAdapter.setList(meals);
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