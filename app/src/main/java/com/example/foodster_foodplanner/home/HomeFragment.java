package com.example.foodster_foodplanner.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.fragments.OnCardClickListener;
import com.example.foodster_foodplanner.fragments.meal.MealFragment;
import com.example.foodster_foodplanner.fragments.meal.MealPresenterImpl;
import com.example.foodster_foodplanner.models.Meal;
import com.example.foodster_foodplanner.retrofitclient.NetworkDelegate;
import com.example.foodster_foodplanner.retrofitclient.RetrofitClientImpl;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements OnFavoriteIconClickListener, OnCardClickListener, NetworkDelegate {

    private ViewPager2 viewPager2;
    private List<Meal> dailyTen;
    private PageViewerAdapter adapter;
    private RetrofitClientImpl retrofit;

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

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        retrofit = RetrofitClientImpl.getInstance();
        retrofit.getRandomMeal(this);

        viewPager2 = view.findViewById(R.id.viewPager);

    }

    @Override
    public void onClick(Meal meal) {
        Toast.makeText(this.requireContext(), "Meal Clicked" + meal.getStrMeal(), Toast.LENGTH_SHORT).show();

        MealPresenterImpl.setMeal(meal);
        Fragment fragment = new MealFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFragmentContainer, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void onClickFav(Meal meal) {
        Toast.makeText(this.requireContext(), "Fav Clicked" + meal.getStrMeal(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponseSuccess(List<Meal> meals) {

        adapter = new PageViewerAdapter(meals, viewPager2, this,
                this, this.requireContext());
        viewPager2.setAdapter(adapter);
    }

    @Override
    public void onResponseFailure(String errorMessage) {
        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();

    }
}