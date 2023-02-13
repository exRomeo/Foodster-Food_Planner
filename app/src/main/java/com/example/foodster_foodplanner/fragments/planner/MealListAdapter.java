package com.example.foodster_foodplanner.fragments.planner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

public class MealListAdapter extends ArrayAdapter<Meal> {

    private final Context context;
    private final List<Meal> mealList;

    public MealListAdapter(@NonNull Context context, int resource, @NonNull List<Meal> mealList) {
        super(context, resource, mealList);
        this.mealList = mealList;
        this.context = context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.dialog_list_item, parent, false);
        ImageView profilePic = rowView.findViewById(R.id.iv_meal_image);
        TextView userName = rowView.findViewById(R.id.tv_meal_title);
        Meal meal = mealList.get(position);
        userName.setText(meal.getStrMeal());
        String bitmap = meal.getStrMealThumbPreview();
        Glide.with(context).load(bitmap).into(profilePic);

        return rowView;
    }


}
