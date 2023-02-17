package com.example.foodster_foodplanner.fragments.filters;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.Repository.RepositoryImpl;
import com.example.foodster_foodplanner.databinding.FragmentFilterBinding;
import com.example.foodster_foodplanner.fragments.CardsGridAdapter;
import com.example.foodster_foodplanner.fragments.OnCardClickListener;
import com.example.foodster_foodplanner.fragments.meal.MealActivity;
import com.example.foodster_foodplanner.localdatabase.LocalDatabaseSource;
import com.example.foodster_foodplanner.models.Meal;
import com.example.foodster_foodplanner.retrofitclient.RetrofitClientImpl;

import java.util.ArrayList;
import java.util.List;


public class FilterFragment extends Fragment implements OnCardClickListener, FilterView {

    FragmentFilterBinding binding;
    private SpinnerAdapter spinnerAdapter;
    CardsGridAdapter cardsGridAdapter;
    FilterPresenter filterPresenter;
    private String filterType;

    public FilterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_filter, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentFilterBinding.bind(view);
        filterPresenter = new FilterPresenterImpl(RepositoryImpl.getInstance(RetrofitClientImpl.getInstance(), LocalDatabaseSource.getInstance(this.requireContext())), this);
        updateFilter();

        List<Meal> meals = new ArrayList<>();
        spinnerAdapter = new SpinnerAdapter(this.requireContext(), 0, meals, filterType);
        binding.spinnerFilter.setAdapter(spinnerAdapter);
        binding.spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                filter(meals.get(i));
                spinnerAdapter.setSelection(i);
                adapterView.findViewById(R.id.image_selected).setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        cardsGridAdapter = new CardsGridAdapter(this.requireContext(), new ArrayList<>(), this, R.drawable.heart);
        binding.filteredItems.setAdapter(cardsGridAdapter);
    }

    @Override
    public void onFavoriteClick(Meal meal) {
        filterPresenter.addToFavorites(meal);
    }

    @Override
    public void onCardClick(Meal meal) {
        filterPresenter.getMealByID(meal.getIdMeal());
    }

    @Override
    public void showFilter(List<Meal> meals) {
        spinnerAdapter.addAll(meals);
        spinnerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMeals(List<Meal> meals) {
        cardsGridAdapter.setList(meals);
        cardsGridAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void filter(Meal selectedItem) {
        switch (filterType) {
            case "country":
                filterPresenter.filterByCountry(selectedItem.getStrArea());
                break;
            case "category":
                filterPresenter.filterByCategory(selectedItem.getStrCategory());
                break;
            case "ingredient":
                filterPresenter.filterByIngredient(selectedItem.getStrIngredient());
                break;
        }
    }

    @Override
    public void updateFilter() {
        filterType = filterPresenter.getFilterType();
        switch (filterType) {
            case "country":
                filterPresenter.getCountryList();
                break;
            case "category":
                filterPresenter.getCategoryList();
                break;
            case "ingredient":
                filterPresenter.getIngredientsList();
                break;
        }
    }

    @Override
    public void navigateToMeal(Meal meal) {
        Intent i = new Intent(this.requireContext(), MealActivity.class);
        i.putExtra("meal", meal);
        startActivity(i);
    }
}