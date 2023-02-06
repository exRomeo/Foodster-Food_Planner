package com.example.foodster_foodplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.foodster_foodplanner.databinding.ActivityIntroBinding;

public class IntroActivity extends AppCompatActivity {
    ActivityIntroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            startActivity(new Intent(binding.getRoot().getContext(), MainActivity.class));
        }, 2000);


    }
}