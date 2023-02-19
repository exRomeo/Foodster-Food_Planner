package com.example.foodster_foodplanner.fragments.signup;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SignupPresenterImpl {
    private final FirebaseAuth firebaseAuth;
    private final SignupView signupView;
    private final Activity activity;

    private final Pattern PASSWORD_PATTERN = Pattern.compile(
            "(?=.*\\d)" +
                    "(?=.*[a-z])" +
                    "(?=.*[A-Z])" +
                    "(?=.*[@#$%^&+=!])" +
                    "(?=\\S+$)" +
                    ".{8,}"
    );

    private final Pattern EMAIL_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    public SignupPresenterImpl(FirebaseAuth firebaseAuth, SignupView signupView) {
        this.firebaseAuth = firebaseAuth;
        this.signupView = signupView;
        this.activity = ((Fragment) signupView).requireActivity();
    }

    private Observable<Boolean> validate(final String data, Pattern pattern) {
        return Observable.create((ObservableOnSubscribe<Boolean>) emitter -> {
            if (data.isEmpty()) {
                emitter.onNext(false);
            } else if (!pattern.matcher(data).matches()) {
                emitter.onNext(false);
            } else {
                emitter.onNext(true);
            }
            emitter.onComplete();
        }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
    }

    public void validatePassword(final String password) {
        Disposable d = validate(password, PASSWORD_PATTERN).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean isValid) throws Exception {
                if (isValid) {
                    signupView.validPassword();
                } else {
                    signupView.invalidPassword();
                }
            }
        });

    }

    public void validateEmail(final String email) {
        Disposable d = validate(email, EMAIL_PATTERN).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean isValid) throws Exception {
                if (isValid) {
                    signupView.validEmail();
                } else {
                    signupView.invalidEmail();
                }
            }
        });
    }

}
