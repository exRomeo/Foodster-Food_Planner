package com.example.foodster_foodplanner.fragments.planner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.Repository.RepositoryImpl;
import com.example.foodster_foodplanner.databinding.FragmentPlannerBinding;
import com.example.foodster_foodplanner.localdatabase.LocalDatabaseSource;
import com.example.foodster_foodplanner.retrofitclient.RetrofitClientImpl;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.concurrent.atomic.AtomicInteger;

public class PlannerFragment extends Fragment {
    FragmentPlannerBinding binding;
    PlannerPresenter plannerPresenter;
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
        plannerPresenter = new PlannerPresenterImpl(RepositoryImpl.getInstance(RetrofitClientImpl.getInstance(), LocalDatabaseSource.getInstance(this.requireContext())));
        binding = FragmentPlannerBinding.bind(view);
        binding.saturdayAdd.setOnClickListener(v-> createDialog());
//        binding.sundayAdd;
//        binding.mondayAdd;
//        binding.tuesdayAdd;
//        binding.wednesdayAdd;
//        binding.thursdayAdd;
//        binding.fridayAdd;
    }

    public MaterialAlertDialogBuilder createDialog() {
        String[] days = {"None", "Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

        AtomicInteger checkedItem = new AtomicInteger(-1);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this.requireContext());
        builder.setView(R.layout.dialog_list_view);
        builder.setTitle("Planning for which day ?");

        builder.setSingleChoiceItems(new MealListAdapter(this.requireContext(),R.layout.dialog_list_item,plannerPresenter.getMealList())
        , checkedItem.get(),(dialog, item) -> checkedItem.set(item));










     builder.setPositiveButton("OK", (dialog, which) -> {
            if (checkedItem.get() != -1) {
                Toast.makeText(this.requireContext(),"Planned for next\n" + days[checkedItem.get()], Toast.LENGTH_SHORT).show();
                /*mealPresenter.planMeal(currentMeal, checkedItem.get());*/
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        return builder;
    }
}