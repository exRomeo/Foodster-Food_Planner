package com.example.foodster_foodplanner.fragments.planner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.fragments.OnCardClickListener;
import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

public class DayListAdapter extends RecyclerView.Adapter<DayListAdapter.ViewHolder> {
    private final Context context;
    private List<Meal> list;
    private OnCardClickListener onCardClickListener;

    public DayListAdapter(Context context, List<Meal> list,OnCardClickListener onCardClickListener) {
        this.context = context;
        this.list = list;
        this.onCardClickListener = onCardClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.meal_icon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayListAdapter.ViewHolder holder, int position) {
        Meal currentMeal = list.get(position);
        holder.tvMealTitle.setText(currentMeal.getStrMeal());
        Glide.with(context).asBitmap().load(currentMeal.getStrMealThumb()).override(150).into(holder.ivMealImage);
        holder.cvIcon.setOnClickListener(v -> onCardClickListener.onCardClick(currentMeal));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final TextView tvMealTitle;
        final ImageView ivMealImage;
        final CardView cvIcon;

        ViewHolder(View view) {
            super(view);
            tvMealTitle = view.findViewById(R.id.tv_meal_title);
            ivMealImage = view.findViewById(R.id.iv_meal_image);
            cvIcon = view.findViewById(R.id.cv_icon);
        }

    }

    public void setList(List<Meal> list) {
        this.list = list;
    }
}
