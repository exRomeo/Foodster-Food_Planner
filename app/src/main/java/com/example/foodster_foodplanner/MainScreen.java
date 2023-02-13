package com.example.foodster_foodplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodster_foodplanner.databinding.ScreenMainBinding;
import com.example.foodster_foodplanner.fragments.favorites.FavoritesFragment;
import com.example.foodster_foodplanner.fragments.planner.PlannerFragment;

public class MainScreen extends AppCompatActivity {
     ImageButton menu;
     Intent intent;
     ScreenMainBinding binding;
     FavoritesFragment favoritesFragment;
     PlannerFragment plannerFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ScreenMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        menu = findViewById(R.id.menuIcon);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent= new Intent(MainScreen.this,NavigationScreen.class);
                startActivity(intent);
            }
        });
        binding.favIcon.setOnClickListener(v->{
            favoritesFragment = new FavoritesFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.mainFragmentContainer,favoritesFragment);
            fragmentTransaction.addToBackStack("favorites");
            fragmentTransaction.commit();
        });
        binding.calendarIcon.setOnClickListener(v->{
            plannerFragment = new PlannerFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.mainFragmentContainer,plannerFragment);
            fragmentTransaction.addToBackStack("planner");
            fragmentTransaction.commit();
        });
    }
}