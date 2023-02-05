package com.example.foodster_foodplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainScreen extends AppCompatActivity {
     ImageButton menu;
     Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_main);
        menu = findViewById(R.id.menuIcon);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent= new Intent(MainScreen.this,NavigationScreen.class);
                startActivity(intent);
            }
        });
    }
}