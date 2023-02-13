package com.example.foodster_foodplanner.fragments.planner;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.Repository.RepositoryImpl;
import com.example.foodster_foodplanner.databinding.FragmentPlannerBinding;
import com.example.foodster_foodplanner.localdatabase.LocalDatabaseSource;
import com.example.foodster_foodplanner.models.Meal;
import com.example.foodster_foodplanner.retrofitclient.RetrofitClientImpl;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class PlannerFragment extends Fragment implements PlannerView {
    FragmentPlannerBinding binding;
    PlannerPresenter plannerPresenter;
    MealListAdapter mla;
    RecyclerView recyclerView;

    public PlannerFragment() {
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
        return inflater.inflate(R.layout.fragment_planner, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        plannerPresenter = new PlannerPresenterImpl(RepositoryImpl.getInstance(RetrofitClientImpl.getInstance(), LocalDatabaseSource.getInstance(this.requireContext())),this);
        binding = FragmentPlannerBinding.bind(view);
        binding.saturdayAdd.setOnClickListener(v -> createDialog().show());
//        binding.sundayAdd;
//        binding.mondayAdd;
//        binding.tuesdayAdd;
//        binding.wednesdayAdd;
//        binding.thursdayAdd;
//        binding.fridayAdd;
    }

    public AlertDialog createDialog() {
//        String[] days = {"None", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

//        AtomicInteger checkedItem = new AtomicInteger(-1);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this.requireContext());
        View v = getLayoutInflater().inflate(R.layout.dialog_list_view, null);
        builder.setView(v);
        recyclerView = v.findViewById(R.id.rcv_meals);
        mla = new MealListAdapter(this.requireContext(), new ArrayList<>());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.requireContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mla);
        builder.setTitle("Planning for which day ?");
        plannerPresenter.getMealList();


        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        return builder.create();
    }

    @Override
    public void addMealToPlan(Meal meal) {

    }

    @Override
    public void updateList(List<Meal> mealList){
        mla.setList(mealList);
        mla.notifyDataSetChanged();
        Log.i("TAG", "updateList: called w/meal " + mealList.get(0).getStrMeal());
    }
}