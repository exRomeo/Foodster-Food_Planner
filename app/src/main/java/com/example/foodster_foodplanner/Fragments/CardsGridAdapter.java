package com.example.foodster_foodplanner.Fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

public class CardsGridAdapter extends RecyclerView.Adapter<CardsGridAdapter.ViewHolder> {
    private final Context context;
    private final List<Meal> list;
    private final OnCardClickListener onCardClickListener;
    private final int icon;

    public CardsGridAdapter(Context context, List<Meal> mealList, OnCardClickListener onCardClickListener, int icon) {
        this.list = mealList;
        this.context = context;
        this.onCardClickListener = onCardClickListener;
        this.icon = icon;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_square_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardsGridAdapter.ViewHolder holder, int position) {
        Meal currentMeal = list.get(position);
        holder.getTopRightButton().setOnClickListener(view -> onCardClickListener.onClick(currentMeal));
        holder.getTopRightButton().setImageResource(icon);
        holder.getTitle().setText(currentMeal.getStrMeal());
        Glide.with(context).load(currentMeal.getStrMealThumb()).into(holder.getMealImg());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final ImageView mealImg;
        private final ImageView topRightButton;

        ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.meal_title);
            mealImg = view.findViewById(R.id.meal_img);
            topRightButton = view.findViewById(R.id.top_right_button);
        }

        public TextView getTitle() {
            return title;
        }

        public ImageView getMealImg() {
            return mealImg;
        }

        public ImageView getTopRightButton() {
            return topRightButton;
        }
    }
}
