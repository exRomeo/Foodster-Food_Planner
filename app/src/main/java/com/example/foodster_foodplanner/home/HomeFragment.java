package com.example.foodster_foodplanner.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.models.Meal;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

   private ViewPager2 viewPager;
   private ArrayList<Meal> dailyTen;
   private PageViewerAdapter adapter;
    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewPager = container.findViewById(R.id.viewPager);

        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}