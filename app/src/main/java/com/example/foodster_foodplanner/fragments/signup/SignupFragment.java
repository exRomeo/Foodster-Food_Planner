package com.example.foodster_foodplanner.fragments.signup;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.foodster_foodplanner.MainScreen;
import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.databinding.FragmentSignupBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupFragment extends Fragment implements SignupView {
    FragmentSignupBinding binding;
    FirebaseAuth firebaseAuth;

    SignupPresenterImpl presenter;

    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        presenter = new SignupPresenterImpl(firebaseAuth, this);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentSignupBinding.bind(view);
        binding.tvLogin.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStack());
        binding.passwordField.addTextChangedListener(passwordFieldWatcher());
        binding.emailField.addTextChangedListener(emailFieldWatcher());
        binding.signupButton.setOnClickListener(v->{
            if(getPassword().equals(getConfirmPassword())){
                binding.passwordRestriction.setVisibility(View.GONE);
            }else{
                binding.passwordRestriction.setText("Password and Confirm password don't Match");
                binding.passwordRestriction.setTextColor(Color.RED);
            }
        });
    }

    @Override
    public String getName() {
        return binding.emailField.getText().toString();
    }

    @Override
    public String getPassword() {
        return binding.passwordField.getText().toString();
    }

    @Override
    public String getConfirmPassword() {
        return binding.confirmPasswordField.getText().toString();
    }


    public void updateUI(FirebaseUser user) {
        Toast.makeText(this.requireContext(), "Welcome " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this.requireContext(), MainScreen.class));
    }

    @Override
    public void validPassword() {
        binding.passwordRestriction.setTextColor(Color.GREEN);
        binding.signupButton.setEnabled(true);
    }

    @Override
    public void invalidPassword() {
        binding.passwordRestriction.setTextColor(Color.RED);
        binding.signupButton.setEnabled(false);
    }

    @Override
    public void validEmail() {
        binding.emailRestriction.setText(" is Valid");
        binding.emailRestriction.setTextColor(Color.GREEN);
    }

    @Override
    public void invalidEmail() {
        binding.emailRestriction.setText(" is Invalid");
        binding.emailRestriction.setTextColor(Color.RED);
        binding.signupButton.setEnabled(false);
    }
    public TextWatcher passwordFieldWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String password = editable.toString();
                presenter.validatePassword(password);
            }
        };
    }

    public TextWatcher emailFieldWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.validateEmail(editable.toString());
            }
        };
    }
}