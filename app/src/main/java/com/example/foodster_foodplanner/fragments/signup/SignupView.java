package com.example.foodster_foodplanner.fragments.signup;

import com.google.firebase.auth.FirebaseUser;

public interface SignupView {
    String getName();
    String getPassword();
    String getConfirmPassword();
    void updateUI(FirebaseUser user);
    void validPassword();
    void invalidPassword();
    void validEmail();
    void invalidEmail();

}
