package com.example.foodster_foodplanner;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodster_foodplanner.databinding.ActivityIntroBinding;
import com.example.foodster_foodplanner.screen.registration.RegistrationActivity;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory;
import com.google.firebase.auth.FirebaseAuth;

public class IntroActivity extends AppCompatActivity {
    ActivityIntroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FirebaseApp.initializeApp(/*context=*/ this);
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(
                PlayIntegrityAppCheckProviderFactory.getInstance());
        Log.i("TAG", "checking logged in state ->> " + FirebaseAuth.getInstance().getCurrentUser());

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                startActivity(new Intent(IntroActivity.this, RegistrationActivity.class));
            } else {
                startActivity(new Intent(IntroActivity.this, MainScreenActivity.class));
            }
        }, 2000);
    }
}