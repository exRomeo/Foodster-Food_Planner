package com.example.foodster_foodplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodster_foodplanner.databinding.ScreenMainBinding;

public class MainScreen extends AppCompatActivity {
    ImageButton menu, search, home;
    Intent intent;
    ScreenMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ScreenMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        menu = findViewById(R.id.menuIcon);
//        search = findViewById(R.id.searchIcon);
//        home = findViewById(R.id.homeIcon);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainScreen.this, NavigationScreen.class);
                startActivity(intent);
            }
        });

//        search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Fragment fragment = new MainSearchFragment();
//                FragmentManager fragmentManager = MainScreen.this.getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.mainFragmentContainer, fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//            }
//        });
//
//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Fragment fragment = new HomeFragment();
//                FragmentManager fragmentManager = MainScreen.this.getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.mainFragmentContainer, fragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
//            }
//        });
//
//        binding.favIcon.setOnClickListener(v -> {
//            FavoritesFragment favoritesFragment = new FavoritesFragment();
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.mainFragmentContainer, favoritesFragment);
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
//        });
//
//        binding.calendarIcon.setOnClickListener(v -> {
//            PlannerFragment plannerFragment = new PlannerFragment();
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.mainFragmentContainer, plannerFragment);
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
//        });
    }
}