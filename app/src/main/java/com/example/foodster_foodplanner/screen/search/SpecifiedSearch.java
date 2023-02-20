package com.example.foodster_foodplanner.screen.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.databinding.FragmentSpecifiedSearchBinding;
import com.example.foodster_foodplanner.screen.filters.FilterActivity;

public class SpecifiedSearch extends Fragment {
    FragmentSpecifiedSearchBinding binding;
    public SpecifiedSearch() {
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
        return inflater.inflate(R.layout.fragment_specified_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentSpecifiedSearchBinding.bind(view);
        binding.searchCountry.setOnClickListener(v->{
            Intent i = new Intent(this.requireContext(), FilterActivity.class);
            i.putExtra("filter_by","country");
            startActivity(i);
        });

        binding.searchCategory.setOnClickListener(v->{
            Intent i = new Intent(this.requireContext(), FilterActivity.class);
            i.putExtra("filter_by","category");
            startActivity(i);
        });

        binding.searchIng.setOnClickListener(v->{
            Intent i = new Intent(this.requireContext(), FilterActivity.class);
            i.putExtra("filter_by","ingredient");
            startActivity(i);
        });
    }
}