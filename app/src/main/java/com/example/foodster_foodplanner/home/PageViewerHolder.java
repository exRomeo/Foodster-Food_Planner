package com.example.foodster_foodplanner.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodster_foodplanner.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PageViewerHolder extends RecyclerView.ViewHolder {
    TextView mealName;
    ImageView mealImage;
    FloatingActionButton addToFavorites;
    public PageViewerHolder(@NonNull View itemView) {
        super(itemView);
        mealName=itemView.findViewById(R.id.mealName);
        mealImage=itemView.findViewById(R.id.mealImage);
        addToFavorites=itemView.findViewById(R.id.addToFav);
    }
}
