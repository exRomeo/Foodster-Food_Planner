package com.example.foodster_foodplanner.home;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.databinding.FragmentHomeBinding;
import com.example.foodster_foodplanner.fragments.OnCardClickListener;
import com.example.foodster_foodplanner.models.Meal;
import com.example.foodster_foodplanner.retrofitclient.API;
import com.example.foodster_foodplanner.retrofitclient.RetrofitClientImpl;

import java.util.ArrayList;


public class HomeFragment extends Fragment implements OnFavoriteIconClickListener, OnCardClickListener {

    private ViewPager2 viewPager2;
    private ArrayList<Meal> dailyTen;
    private PageViewerAdapter adapter;
    private RetrofitClientImpl retrofit;
    private FragmentHomeBinding homeBinding;
    private LinearLayoutManager layoutManager;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         retrofit = RetrofitClientImpl.getInstance();
          //add rx retrofit obs here

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeBinding = FragmentHomeBinding.bind(view);
        layoutManager=new LinearLayoutManager(this.requireContext());
        viewPager2=homeBinding.viewPager;
        adapter = new PageViewerAdapter(dailyTen,viewPager2,this,
                this,this.requireContext());
        viewPager2.setAdapter(adapter);
    }

    @Override
    public void onClick(Meal meal) {
        Toast.makeText(this.requireContext(), "Meal Clicked"+meal.getStrMeal(), Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onClickFav(Meal meal) {
        Toast.makeText(this.requireContext(), "Fav Clicked"+meal.getStrMeal(), Toast.LENGTH_SHORT).show();
    }
}