package com.example.foodster_foodplanner.fragments.search;

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
import androidx.navigation.ActionOnlyNavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.Repository.Repository;
import com.example.foodster_foodplanner.Repository.RepositoryImpl;
import com.example.foodster_foodplanner.fragments.CardsGridAdapter;
import com.example.foodster_foodplanner.fragments.OnCardClickListener;
import com.example.foodster_foodplanner.fragments.meal.MealFragment;
import com.example.foodster_foodplanner.fragments.meal.MealPresenterImpl;
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
        screenView=view;
        resultsMenu = view.findViewById(R.id.results_menu);
        presenter = new NamePresenterImpl(RepositoryImpl.getInstance(RetrofitClientImpl.getInstance(), LocalDatabaseSource.getInstance(this.requireContext())));
        layoutManager = new LinearLayoutManager(this.requireContext());
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
        Navigation.findNavController(screenView).
                navigate(MainSearchFragmentDirections.actionMainSearchFragmentToMealFragment(meal));

    }
}