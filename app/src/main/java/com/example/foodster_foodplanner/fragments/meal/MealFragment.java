package com.example.foodster_foodplanner.fragments.meal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.databinding.FragmentMealBinding;
import com.example.foodster_foodplanner.models.Meal;


public class MealFragment extends Fragment implements MealView {
    MealPresenterImpl mealPresenter;
    FragmentMealBinding binding;

    public MealFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mealPresenter = new MealPresenterImpl();
        return inflater.inflate(R.layout.fragment_meal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentMealBinding.bind(view);
        binding.rightBottomButton.setOnClickListener(v -> addToFavorites(MealPresenterImpl.getMeal()));
        //TODO make it go back for real :D
        binding.backButton.setOnClickListener(v -> Toast.makeText(this.requireContext(), "go bak >:( !", Toast.LENGTH_SHORT).show());

    }

    @Override
    public void showMeal(Meal meal) {
        Glide.with(this).load(meal.getStrMealThumb()).into(binding.mealImage);
        binding.mealTitle.setText(meal.getStrMeal());
        binding.mealOrigin.setText("Origin : " + meal.getStrArea());
        binding.ingredientsText.setText(mealPresenter.getIngredients(meal));
        binding.amountsText.setText(mealPresenter.getMeasures(meal));
    }

    @Override
    public void addToFavorites(Meal meal) {
        mealPresenter.addToFavorites(meal);
    }

    @Override
    public void showError(String message) {

    }
}