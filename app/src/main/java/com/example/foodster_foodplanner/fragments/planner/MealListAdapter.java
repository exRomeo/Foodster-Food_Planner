package com.example.foodster_foodplanner.fragments.planner;

import android.content.Context;
import android.util.Log;
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

public class MealListAdapter extends RecyclerView.Adapter<MealListAdapter.ViewHolder> {
    private final Context context;
    private List<Meal> list;
//    private final OnCardClickListener onCardClickListener;
    //private final int icon;

    public MealListAdapter(Context context, List<Meal> mealList/*, OnCardClickListener onCardClickListener, int icon*/) {
        this.list = mealList;
        this.context = context;
//        this.onCardClickListener = onCardClickListener;
        //this.icon = icon;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_square_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meal currentMeal = list.get(position);
//        holder.getTopRightButton().setOnClickListener(view -> onCardClickListener.onFavoriteClick(currentMeal));
//        holder.getTopRightButton().setImageResource(icon);
        holder.getTvMealTitle().setText(currentMeal.getStrMeal());
        Log.i("TAG", "onBindViewHolder this is itititititit: " + currentMeal.getStrMeal());
        Glide.with(context).load(currentMeal.getStrMealThumbPreview()).into(holder.getIvMealImage());
//        holder.getCardView().setOnClickListener(v -> {
//            onCardClickListener.onCardClick(currentMeal);
//            MealPresenterImpl.setMeal(currentMeal);
//            Fragment fragment = new MealFragment();
//            FragmentManager fragmentManager = context.getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.mainFragmentContainer, fragment);
//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
            //TODO navigation functionality
            /** make it go to the meal view and show meal details
             * cant really picture how would the code look right
             * will do after stitching the ui together :D */
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
//        private final CardView cardView;
        private final TextView tvMealTitle;
        private final ImageView ivMealImage;
//        private final ImageView topRightButton;

        ViewHolder(View view) {
            super(view);
            tvMealTitle = view.findViewById(R.id.tv_meal_title);
            ivMealImage = view.findViewById(R.id.iv_meal_image);

        }

        public TextView getTvMealTitle() {
            return tvMealTitle;
        }

        public ImageView getIvMealImage() {
            return ivMealImage;
        }

//        public ImageView getTopRightButton() {
//            return topRightButton;
//        }
//
//        public CardView getCardView() {
//            return cardView;
//        }
    }

    public void setList(List<Meal> list) {
        this.list = list;
    }
}



