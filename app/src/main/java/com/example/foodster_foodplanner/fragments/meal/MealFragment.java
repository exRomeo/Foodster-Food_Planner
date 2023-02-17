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
import com.example.foodster_foodplanner.Repository.RepositoryImpl;
import com.example.foodster_foodplanner.databinding.FragmentMealBinding;
import com.example.foodster_foodplanner.localdatabase.LocalDatabaseSource;
import com.example.foodster_foodplanner.models.Meal;
import com.example.foodster_foodplanner.retrofitclient.RetrofitClientImpl;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.concurrent.atomic.AtomicInteger;


public class MealFragment extends Fragment implements MealView {
    MealPresenter mealPresenter;
    FragmentMealBinding binding;
    Meal currentMeal;

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
        mealPresenter = new MealPresenterImpl(this, RepositoryImpl.getInstance(RetrofitClientImpl.getInstance(), LocalDatabaseSource.getInstance(this.requireContext())));
        return inflater.inflate(R.layout.fragment_meal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentMealBinding.bind(view);
        currentMeal = mealPresenter.getMeal();
        showMeal(currentMeal);
        binding.addToPlan.setOnClickListener(v -> createDialog().show());
        binding.rightBottomButton.setOnClickListener(v -> addToFavorites(currentMeal));
        binding.backButton.setOnClickListener(v -> requireActivity().finish());

    }

    @Override
    public void showMeal(Meal meal) {
        Glide.with(this).load(meal.getStrMealThumb()).into(binding.mealImage);
        binding.mealTitle.setText(meal.getStrMeal());
        binding.mealOrigin.setText("Origin : " + meal.getStrArea());
        binding.ingredientsText.setText(mealPresenter.getIngredients(meal));
        binding.amountsText.setText(mealPresenter.getMeasures(meal));
        binding.instructionsText.setText(meal.getStrInstructions());
    }

    @Override
    public void addToFavorites(Meal meal) {
        mealPresenter.addToFavorites(meal);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show();
    }


    public MaterialAlertDialogBuilder createDialog() {
        String[] days = {"None", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        AtomicInteger checkedItem = new AtomicInteger(-1);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this.requireContext());
        builder.setTitle("Planning for which day ?");
        builder.setSingleChoiceItems(days, checkedItem.get(), (dialog, which) -> checkedItem.set(which));
        builder.setPositiveButton("OK", (dialog, which) -> {
            if (checkedItem.get() != -1) {
                if (checkedItem.get() == 0) {
                    Toast.makeText(this.requireContext(), "Plan Canceled!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this.requireContext(), "Planned for next\n" + days[checkedItem.get()], Toast.LENGTH_SHORT).show();
                }
                mealPresenter.planMeal(currentMeal, checkedItem.get());
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        return builder;
    }

}