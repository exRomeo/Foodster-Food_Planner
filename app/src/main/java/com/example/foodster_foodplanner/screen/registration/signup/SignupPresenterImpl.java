package com.example.foodster_foodplanner.screen.registration.signup;

import android.app.Activity;
import android.util.Log;

import androidx.fragment.app.Fragment;

import com.example.foodster_foodplanner.screen.CredentialsValidator;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import io.reactivex.rxjava3.disposables.Disposable;

public class SignupPresenterImpl implements SignupPresenter {

    private final SignupView signupView;
    private final FirebaseAuth firebaseAuth;
    private final Activity activity;

    public SignupPresenterImpl(SignupView signupView) {
        this.signupView = signupView;
        firebaseAuth = FirebaseAuth.getInstance();
        this.activity = ((Fragment) signupView).requireActivity();
    }

    @Override
    public void validatePassword(final String password) {
        Disposable d = CredentialsValidator.getInstance().validatePassword(password).subscribe(isValid -> {
            if (isValid) {
                signupView.validPassword();
            } else {
                signupView.invalidPassword();
            }
        });
    }

    @Override
    public void validateEmail(final String email) {
        Disposable d = CredentialsValidator.getInstance().validateEmail(email).subscribe(isValid -> {
            if (isValid) {
                signupView.validEmail();
            } else {
                signupView.invalidEmail();
            }
        });
    }

    @Override
    public void signup(String email, String password) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity, task -> {
            if (task.isSuccessful()) {
                Log.i("TAG", "onComplete: task  " + Objects.requireNonNull(firebaseAuth.getCurrentUser()).getEmail());
            } else {
                Log.i("TAG", "onComplete: Failed");
            }
        });
    }
}
