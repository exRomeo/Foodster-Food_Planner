package com.example.foodster_foodplanner.fragments.planner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    MealListAdapter listAdapter;
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
        binding.saturdayAdd.setOnClickListener(v -> createDialog(1).show());
        binding.sundayAdd.setOnClickListener(v -> createDialog(2).show());
        binding.mondayAdd.setOnClickListener(v -> createDialog(3).show());
        binding.tuesdayAdd.setOnClickListener(v -> createDialog(4).show());
        binding.wednesdayAdd.setOnClickListener(v -> createDialog(5).show());
        binding.thursdayAdd.setOnClickListener(v -> createDialog(6).show());
        binding.fridayAdd.setOnClickListener(v -> createDialog(7).show());
    }

    public MaterialAlertDialogBuilder createDialog(int day) {

        MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(this.requireContext());
        View v = getLayoutInflater().inflate(R.layout.dialog_list_view, null);
        dialog.setView(v);
        recyclerView = v.findViewById(R.id.rcv_meals);
        listAdapter = new MealListAdapter(this.requireContext(), new ArrayList<>());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.requireContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(listAdapter);
        dialog.setTitle("Planning for Next "+ PlannerView.days[day]);
        plannerPresenter.getMealList();
        dialog.setNegativeButton("Cancel", (d, which) -> d.dismiss());
        dialog.setPositiveButton("Done", (dialogInterface, i) -> addMealToPlan(listAdapter.getSelectedItem(),day));
        return dialog;
    }

    @Override
    public void addMealToPlan(Meal meal,int day) {
        plannerPresenter.addToPlan(listAdapter.getSelectedItem(),day);
    }

    @Override
    public void updateList(List<Meal> mealList){
        listAdapter.setList(mealList);
        listAdapter.notifyDataSetChanged();
    }
}