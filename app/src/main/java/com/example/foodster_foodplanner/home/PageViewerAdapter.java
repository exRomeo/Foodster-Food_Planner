package com.example.foodster_foodplanner.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.fragments.CardsGridAdapter;
import com.example.foodster_foodplanner.fragments.OnCardClickListener;
import com.example.foodster_foodplanner.models.Meal;

import java.util.ArrayList;
import java.util.List;

public class PageViewerAdapter extends RecyclerView.Adapter<PageViewerHolder> {
    private List<Meal> dailyMeals;
    private OnCardClickListener cardClickListener;
    private OnFavoriteIconClickListener favoriteIconClickListener;
    private ViewPager2 viewPager;

    private Context context;

    public PageViewerAdapter(List<Meal> dailyMeals, ViewPager2 viewPager, OnCardClickListener cardClickListener, OnFavoriteIconClickListener favoriteIconClickListener, Context context ){
        this.dailyMeals=dailyMeals;
        this.viewPager=viewPager;
        this.context=context;
        this.cardClickListener=cardClickListener;
        this.favoriteIconClickListener=favoriteIconClickListener;
    }

    @NonNull
    @Override
    public PageViewerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.main_meal_row,parent,false);
        return new PageViewerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PageViewerHolder holder, int position) {
        holder.mealName.setText(dailyMeals.get(position).getStrMeal());
        Glide.with(context).load(dailyMeals.get(position).getStrMealThumb()).apply(new RequestOptions().override(150,150)).into(holder.mealImage);
        holder.addToFavorites.setOnClickListener(view -> favoriteIconClickListener.onClickFav(dailyMeals.get(position)));
        holder.mealImage.setOnClickListener(view -> cardClickListener.onClick(dailyMeals.get(position)));
    }

    @Override
    public int getItemCount() {
        return dailyMeals.size();
    }
}
