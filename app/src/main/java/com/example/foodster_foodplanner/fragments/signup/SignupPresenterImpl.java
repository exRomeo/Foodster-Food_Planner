package com.example.foodster_foodplanner.fragments.signup;

import com.example.foodster_foodplanner.fragments.CredentialsValidator;

import io.reactivex.rxjava3.disposables.Disposable;

public class SignupPresenterImpl implements SignupPresenter {

    private final SignupView signupView;


    public SignupPresenterImpl(SignupView signupView) {
        this.signupView = signupView;
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
}
