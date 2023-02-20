package com.example.foodster_foodplanner;

import android.content.Intent;
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

        userProfile = findViewById(R.id.initials);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            if (user.isAnonymous()) {
                userProfile.setText("A");
            } else {
                if (user.getDisplayName() != null && !user.getDisplayName().isEmpty()) {
                    userProfile.setText(user.getDisplayName().substring(0, 2).toUpperCase());
                } else {
                    if (user.getEmail() != null)
                        userProfile.setText(user.getEmail().substring(0, 2).toUpperCase());
                }
            }
        }
        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainScreen.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}