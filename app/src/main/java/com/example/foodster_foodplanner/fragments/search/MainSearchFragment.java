package com.example.foodster_foodplanner.fragments.search;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.models.Meal;

import java.util.ArrayList;
import java.util.List;

public class MainSearchFragment extends Fragment implements NameSearchView {

    EditText searchBar;
    SearchPresenterImplementation presenter;
    ArrayList<Meal> searchResults;

    public MainSearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchResults=new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchBar = view.findViewById(R.id.searchBar);
        presenter = new SearchPresenterImplementation(this);
        searchBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((actionId == EditorInfo.IME_ACTION_DONE)) {
                    //Your Action as go button
                    String mealName = searchBar.getText().toString();
                    presenter.searchByMealName(mealName);
                    Fragment fragment = new SearchResultFragment(searchResults);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.filters, fragment).setReorderingAllowed(true);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
                return false;
            }
        });
    }

    @Override
    public void showResults(List<Meal> results) {
        searchResults.addAll(results);
    }

    @Override
    public void showErr(String error) {
        Toast.makeText(this.requireContext(), error, Toast.LENGTH_SHORT).show();
    }
}