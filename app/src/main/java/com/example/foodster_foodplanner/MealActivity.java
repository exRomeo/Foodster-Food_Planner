package com.example.foodster_foodplanner;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodster_foodplanner.fragments.meal.MealFragment;
import com.example.foodster_foodplanner.models.Meal;

public class MealActivity extends AppCompatActivity {
    MealFragment mealFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        Meal meal = (Meal) getIntent().getExtras().get("meal");
        Bundle bundle = new Bundle();
        bundle.putSerializable("meal",meal);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(savedInstanceState == null) {
            mealFragment = new MealFragment();
            mealFragment.setArguments(bundle);
            fragmentTransaction.add(R.id.mealActivityFragmentContainer, mealFragment, "mealFrag");
        }
        fragmentTransaction.commit();
    }
}