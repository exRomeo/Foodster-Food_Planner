package com.example.foodster_foodplanner.fragments.filters;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodster_foodplanner.R;

public class FilterActivity extends AppCompatActivity {
    FilterFragment filterFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("filter_by", getIntent().getStringExtra("filter_by"));

        if (savedInstanceState == null) {
            filterFragment = new FilterFragment();
            filterFragment.setArguments(bundle);
            fragmentTransaction.add(R.id.filter_container, filterFragment,"filter_fragment");
        }
        fragmentTransaction.commit();
    }
}