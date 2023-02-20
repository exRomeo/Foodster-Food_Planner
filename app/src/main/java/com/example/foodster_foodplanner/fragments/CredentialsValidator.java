package com.example.foodster_foodplanner.fragments;

import java.util.regex.Pattern;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CredentialsValidator {

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
    private static CredentialsValidator credentialsValidator;

    private CredentialsValidator() {
    }

    public static CredentialsValidator getInstance() {
        if (credentialsValidator == null)
            credentialsValidator = new CredentialsValidator();
        return credentialsValidator;
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

    public Observable<Boolean> validatePassword(final String password){
        return validate(password,PASSWORD_PATTERN);
    }

    public Observable<Boolean> validateEmail(final String email){
        return validate(email,EMAIL_PATTERN);
    }

}
