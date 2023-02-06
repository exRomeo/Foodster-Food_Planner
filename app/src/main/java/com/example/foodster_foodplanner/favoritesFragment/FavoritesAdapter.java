package com.example.foodster_foodplanner.favoritesFragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {
    private final Context context;
    private final List<Meal> list;

    public FavoritesAdapter(Context context, List<Meal> mealList){
        this.list = mealList;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_square_card,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesAdapter.ViewHolder holder, int position) {
        Meal currentMeal = list.get(position);
        // TODO: 2/6/2023  implement the required view fillers
        // Done ?

        holder.getRemoveButton().setOnClickListener(view -> Toast.makeText(context,currentMeal.getStrMeal(),Toast.LENGTH_SHORT).show());
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
        private final ImageView removeButton;
        ViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.meal_title);
            mealImg = view.findViewById(R.id.meal_img);
            removeButton = view.findViewById(R.id.remove_button);
        }

        public TextView getTitle() {
            return title;
        }

        public ImageView getMealImg() {
            return mealImg;
        }

        public ImageView getRemoveButton() {
            return removeButton;
        }
    }
}
