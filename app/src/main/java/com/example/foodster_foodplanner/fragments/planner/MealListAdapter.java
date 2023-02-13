package com.example.foodster_foodplanner.fragments.planner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

public class MealListAdapter extends RecyclerView.Adapter<MealListAdapter.ViewHolder> {
    private final Context context;
    private List<Meal> list;
    private int selectedItem = -1;


    public MealListAdapter(Context context, List<Meal> mealList) {
        this.list = mealList;
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.dialog_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meal currentMeal = list.get(position);
        holder.getTvMealTitle().setText(currentMeal.getStrMeal());
        Glide.with(context).load(currentMeal.getStrMealThumbPreview()).into(holder.getIvMealImage());
        holder.radioButton.setChecked(position == selectedItem);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RadioButton radioButton;
        private final TextView tvMealTitle;
        private final ImageView ivMealImage;

        ViewHolder(View view) {
            super(view);
            tvMealTitle = view.findViewById(R.id.tv_meal_title);
            ivMealImage = view.findViewById(R.id.iv_meal_image);
            radioButton = view.findViewById(R.id.radioButton);
            radioButton.setOnClickListener( new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    selectedItem = getAdapterPosition();
                    notifyDataSetChanged();
                }
            });

        }

        public TextView getTvMealTitle() {
            return tvMealTitle;
        }

        public ImageView getIvMealImage() {
            return ivMealImage;
        }

    }

    public void setList(List<Meal> list) {
        this.list = list;
    }

    public Meal getSelectedItem() {
        return list.get(selectedItem);
    }
}