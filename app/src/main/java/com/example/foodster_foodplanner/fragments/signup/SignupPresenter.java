package com.example.foodster_foodplanner.fragments.signup;

public interface SignupPresenter {
    void validatePassword(final String password);
    void validateEmail(final String email);
}
