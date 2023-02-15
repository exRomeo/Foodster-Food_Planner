package com.example.foodster_foodplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.foodster_foodplanner.databinding.ScreenMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainScreen extends AppCompatActivity {
    ImageButton menu;
    Intent intent;
    ScreenMainBinding binding;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ScreenMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        navController = Navigation.findNavController(this,R.id.main_fragment_host);
       BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
       NavigationUI.setupWithNavController(bottomNavigationView,navController);

        menu = findViewById(R.id.menuIcon);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainScreen.this, NavigationScreen.class);
                startActivity(intent);
            }
        });
    }
}