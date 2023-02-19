package com.example.foodster_foodplanner.fragments.signup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class SignupFragment extends Fragment implements SignupView {
    FragmentSignupBinding binding;
    FirebaseAuth firebaseAuth;
    CallbackManager callbackManager;
    LoginButton loginButton;

    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        callbackManager = CallbackManager.Factory.create();
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
        binding.tvLogin.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStack());
        loginButton = view.findViewById(R.id.fb_button);
        loginButton.setReadPermissions(List.of("email", "public_profile"));
        loginButton.setFragment(this);
        loginButton.setBackgroundResource(R.drawable.facebook);
        loginButton.setCompoundDrawables(null, null, null, null);
        loginButton.setText("");
        loginButton.setLoginText("");
        loginButton.setLogoutText("");

        loginButton.setOnClickListener(view1 -> Log.i("TAG", "onViewCreated: "));
        loginButton.registerCallback(callbackManager, new FacebookCallback<>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.i("TAG", "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                // App code
                Log.i("TAG", "facebook:onCancel");
            }

            @Override
            public void onError(@NonNull FacebookException exception) {
                // App code
                Log.i("TAG", "facebook:onError", exception);
            }
        });
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

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("TAG", "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this.requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.i("TAG", "signInWithCredential:success " + firebaseAuth.getCurrentUser().getDisplayName());
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.i("TAG", "signInWithCredential:failure", task.getException());
                        Toast.makeText(requireContext(), "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        Toast.makeText(this.requireContext(), "Welcome " + user.getDisplayName(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this.requireContext(), MainScreen.class));
    }
}