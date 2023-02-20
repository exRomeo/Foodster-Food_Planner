package com.example.foodster_foodplanner.screen.filters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.models.Meal;

import java.util.List;

public class SpinnerAdapter extends ArrayAdapter<Meal> {

    private final Context context;
    private List<Meal> list;
    String type;
    int selection;

    public SpinnerAdapter(@NonNull Context context, int itemResourceID, List<Meal> meals, String type) {
        super(context, itemResourceID, meals);
        this.context = context;
        this.list = meals;
        this.type = type;
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = convertView;
        if(view == null)
               view = inflater.inflate(R.layout.spinner_list_item, parent, false);
        TextView tvFilter = view.findViewById(R.id.tv_filter);
        ImageView ivFilter = view.findViewById(R.id.iv_filter);
        ImageView ivSelected = view.findViewById(R.id.image_selected);
        if(selection == position) {
            ivSelected.setVisibility(View.VISIBLE);
        } else {
            ivSelected.setVisibility(View.INVISIBLE);
        }
        tvFilter.setText(getText(position));
        Glide.with(context).load(getImgURL(position)).into(ivFilter);
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public void setList(List<Meal> list) {
        this.list = list;
    }
    public void setSelection(int selection){
        this.selection = selection;
    }

    String getImgURL(int position) {
        String imgURL = null;
        switch (type) {
            case "country":
                imgURL = Meal.getFlag(position);
                break;
            case "category":
                imgURL = list.get(position).getCategoryThumb();
                break;
            case "ingredient":
                imgURL = list.get(position).getIngredientThumbPreview(list.get(position).getStrIngredient());
                break;
        }
        return imgURL;
    }

    String getText(int position) {
        String text = null;
        switch (type) {
            case "country":
                text = list.get(position).getStrArea();
                break;
            case "category":
                text = list.get(position).getStrCategory();
                break;
            case "ingredient":
                text = list.get(position).getStrIngredient();
                break;
        }
        return text;
    }


}
