package com.example.foodster_foodplanner;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.foodster_foodplanner.databinding.ScreenMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainScreen extends AppCompatActivity {
    ScreenMainBinding binding;
    NavController navController;

    TextView userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ScreenMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        navController = Navigation.findNavController(this, R.id.main_fragment_host);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        userProfile = binding.initials;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            char initial = user.getDisplayName().charAt(0);
            userProfile.setText(initial);
        }
        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.profileFragment);
            }
        });
    }
}