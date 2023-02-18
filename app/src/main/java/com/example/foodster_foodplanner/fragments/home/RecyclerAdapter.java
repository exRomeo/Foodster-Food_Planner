package com.example.foodster_foodplanner.fragments.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.fragments.OnCardClickListener;
import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    private Context context;
    private List<Meal>  countryMeals;
    private OnCardClickListener cardClickListener;

    public RecyclerAdapter(Context context,List<Meal>  countryMeals,OnCardClickListener cardClickListener){
        this.context=context;
        this.countryMeals=countryMeals;
        this.cardClickListener=cardClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.home_country_card,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.mealName.setText(countryMeals.get(position).getStrMeal());
        Glide.with(context).load(countryMeals.get(position).getStrMealThumb()).apply(new RequestOptions()).into(holder.mealImage);
        holder.mealImage.setOnClickListener(view -> cardClickListener.onCardClick(countryMeals.get(position)));
        holder.addToFavorites.setOnClickListener(view -> cardClickListener.onFavoriteClick(countryMeals.get(position)));
    }

    @Override
    public int getItemCount() {
        return countryMeals.size();
    }
}
