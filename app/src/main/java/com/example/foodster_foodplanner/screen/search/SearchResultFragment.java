package com.example.foodster_foodplanner.screen.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodster_foodplanner.screen.meal.MealActivity;
import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.Repository.RepositoryImpl;
import com.example.foodster_foodplanner.screen.CardsGridAdapter;
import com.example.foodster_foodplanner.screen.OnCardClickListener;
import com.example.foodster_foodplanner.localdatabase.LocalDatabaseSource;
import com.example.foodster_foodplanner.models.Meal;
import com.example.foodster_foodplanner.retrofitclient.RetrofitClientImpl;

import java.util.ArrayList;


public class SearchResultFragment extends Fragment implements OnCardClickListener {

    private ArrayList<Meal> results;
    private CardsGridAdapter adapter;
    private RecyclerView resultsMenu;
    RecyclerView.LayoutManager layoutManager;
    View screenView;
    NamePresenterImpl presenter;

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
        screenView = view;
        resultsMenu = view.findViewById(R.id.results_menu);
        presenter = new NamePresenterImpl(RepositoryImpl.getInstance(RetrofitClientImpl.getInstance(), LocalDatabaseSource.getInstance(this.requireContext())));
        layoutManager = new GridLayoutManager(this.requireContext(),2);
        adapter = new CardsGridAdapter(this.requireContext(), results, this, R.drawable.heart);
        resultsMenu.setLayoutManager(layoutManager);
        resultsMenu.setAdapter(adapter);
    }

    @Override
    public void onFavoriteClick(Meal meal) {
        Toast.makeText(this.requireContext(), "Meal is added: " + meal.getStrMeal(), Toast.LENGTH_SHORT).show();
        presenter.addToFavorites(meal);
    }

    @Override
    public void onCardClick(Meal meal) {
        Intent i = new Intent(this.requireContext(), MealActivity.class);
        i.putExtra("meal",meal);
        startActivity(i);
    }
}