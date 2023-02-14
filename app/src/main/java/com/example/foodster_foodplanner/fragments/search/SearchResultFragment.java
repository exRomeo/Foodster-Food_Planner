package com.example.foodster_foodplanner.fragments.search;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.fragments.CardsGridAdapter;
import com.example.foodster_foodplanner.fragments.OnCardClickListener;
import com.example.foodster_foodplanner.fragments.meal.MealFragment;
import com.example.foodster_foodplanner.fragments.meal.MealPresenterImpl;
import com.example.foodster_foodplanner.home.OnFavoriteIconClickListener;
import com.example.foodster_foodplanner.models.Meal;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class SearchResultFragment extends Fragment implements OnCardClickListener {

    private ArrayList<Meal> results;
    private CardsGridAdapter adapter;
    private RecyclerView resultsMenu;
    RecyclerView.LayoutManager layoutManager;

    public SearchResultFragment() {
        // Required empty public constructor

    }

    public SearchResultFragment(ArrayList<Meal> results) {
        // Required empty public constructor
        this.results = results;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        resultsMenu = view.findViewById(R.id.results_menu);
        layoutManager = new LinearLayoutManager(this.requireContext());
        adapter = new CardsGridAdapter(this.requireContext(), results, this, R.drawable.heart);
        resultsMenu.setLayoutManager(layoutManager);
        resultsMenu.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
    }

    @Override
    public void onFavoriteClick(Meal meal) {

    }

    @Override
    public void onCardClick(Meal meal) {
        MealPresenterImpl.setMeal(meal);
        Fragment fragment = new MealFragment();
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFragmentContainer, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}