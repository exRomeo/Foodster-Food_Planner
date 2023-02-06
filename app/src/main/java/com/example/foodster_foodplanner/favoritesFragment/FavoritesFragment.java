package com.example.foodster_foodplanner.favoritesFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.databinding.FragmentFavoritesBinding;


public class FavoritesFragment extends Fragment {
FragmentFavoritesBinding binder;
    public FavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binder = FragmentFavoritesBinding.inflate(getLayoutInflater());
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        binder.mealsGrid.setAdapter(new Adapter() {
//
//        });
    }
}