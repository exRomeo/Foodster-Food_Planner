package com.example.foodster_foodplanner;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodster_foodplanner.databinding.ActivityIntroBinding;
import com.google.firebase.auth.FirebaseAuth;

public class IntroActivity extends AppCompatActivity {
    ActivityIntroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        binding = ActivityIntroBinding.inflate(getLayoutInflater());
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            Log.i("TAG", "checking logged in state ->> " + FirebaseAuth.getInstance().getCurrentUser());
            if(FirebaseAuth.getInstance().getCurrentUser() == null) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            } else {
                startActivity(new Intent(getApplicationContext(), MainScreen.class));
            };
        }, 2000);


    }
}