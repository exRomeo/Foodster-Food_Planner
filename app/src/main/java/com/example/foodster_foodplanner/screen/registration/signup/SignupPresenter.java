package com.example.foodster_foodplanner.screen.registration.signup;

public interface SignupPresenter {
    void validatePassword(final String password);
    void validateEmail(final String email);
    void signup(String email, String password);
}
