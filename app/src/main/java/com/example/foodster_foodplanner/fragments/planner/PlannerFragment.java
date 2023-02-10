package com.example.foodster_foodplanner.fragments.planner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.databinding.FragmentPlannerBinding;

public class PlannerFragment extends Fragment {
    FragmentPlannerBinding binding;

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
        binding = FragmentPlannerBinding.bind(view);
//        binding.saturdayAdd;
//        binding.sundayAdd;
//        binding.mondayAdd;
//        binding.tuesdayAdd;
//        binding.wednesdayAdd;
//        binding.thursdayAdd;
//        binding.fridayAdd;
    }
}