package com.example.foodster_foodplanner.favoritesFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.databinding.FragmentFavoritesBinding;
import com.example.foodster_foodplanner.models.Meal;

import java.util.ArrayList;
import java.util.List;


public class FavoritesFragment extends Fragment {
    FragmentFavoritesBinding binder;
    FavoritesAdapter favoritesAdapter;
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
        mealList = new ArrayList<>();
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //TODO ------
/*        binder = FragmentFavoritesBinding.bind(view);
        favoritesAdapter = new FavoritesAdapter(this.requireContext(), mealList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.requireContext(), 2);
        binder.mealsGrid.setAdapter(favoritesAdapter);
        binder.mealsGrid.setLayoutManager(gridLayoutManager);*/
    }

}