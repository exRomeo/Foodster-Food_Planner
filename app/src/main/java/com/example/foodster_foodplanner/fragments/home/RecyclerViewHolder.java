package com.example.foodster_foodplanner.fragments.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodster_foodplanner.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    TextView mealName;
    ImageView mealImage;
    FloatingActionButton addToFavorites;
    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        mealName=itemView.findViewById(R.id.country_meal_name);
        mealImage=itemView.findViewById(R.id.home_country_image);
        addToFavorites=itemView.findViewById(R.id.add_to_favorites);
    }
}
