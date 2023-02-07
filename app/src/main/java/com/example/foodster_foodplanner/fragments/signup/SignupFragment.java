package com.example.foodster_foodplanner.fragments.signup;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.databinding.FragmentSignupBinding;
import com.google.android.material.internal.TextWatcherAdapter;

public class SignupFragment extends Fragment implements SignupView{
    FragmentSignupBinding binding;
    public SignupFragment() {
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
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentSignupBinding.bind(view);

    }

    @Override
    public String getName() {
        return binding.nameField.getText().toString();
    }

    @Override
    public String getPassword() {
        return binding.passwordField.getText().toString();
    }

    @Override
    public String getConfirmPassword() {
        return binding.confirmPasswordField.getFontFeatureSettings().toString();
    }
}