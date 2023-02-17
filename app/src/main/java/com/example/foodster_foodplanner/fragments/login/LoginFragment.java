package com.example.foodster_foodplanner.fragments.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodster_foodplanner.MainScreen;
import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.fragments.signup.SignupFragment;
import com.example.foodster_foodplanner.home.HomeFragment;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.concurrent.Executor;

public class LoginFragment extends Fragment {

    TextView signupLink;
    Button login;
    Intent intent;
    private SignInClient oneTapClient;
    private BeginSignInRequest signInRequest;
    Context context;
    ImageButton use_google;
    private static final String TAG = "GOOGle";
    private static final int REQ_ONE_TAP = 1;
    private boolean showOneTapUI = true;
    private FirebaseAuth mAuth;
    Intent homeIntent;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this.requireContext();
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.login_fragement, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signupLink = view.findViewById(R.id.signupLink);
        login = view.findViewById(R.id.loginBtn);
        use_google = view.findViewById(R.id.login_google);

        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new SignupFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragmentContainerView, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent(getActivity(), MainScreen.class);
                startActivity(intent);
            }
        });

        use_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oneTapClient = Identity.getSignInClient(context);
                signInRequest = BeginSignInRequest.builder().setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder().setSupported(true).build()).setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder().setSupported(true)
                        //add our server's client ID in strings if changed(get from https://console.cloud.google.com/apis)
                        .setServerClientId(getString(R.string.default_google_client_id)).setFilterByAuthorizedAccounts(true).build()).setAutoSelectEnabled(true).build();
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_ONE_TAP:
                try {
                    SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(data);
                    String idToken = credential.getGoogleIdToken();
                    if (idToken != null) {
                        AuthCredential firebaseCredential = GoogleAuthProvider.getCredential(idToken, null);
                        mAuth.signInWithCredential(firebaseCredential).addOnCompleteListener((Executor) this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI
                                    Log.d(TAG, "signInWithCredential:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    String name= user.getDisplayName();
                                    homeIntent=new Intent(context, MainScreen.class);
                                    homeIntent.putExtra("Display Name",name);
                                    startActivity(homeIntent);
                                    //updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(context, "signInWithCredential:failed", Toast.LENGTH_SHORT).show();
                                    Log.w(TAG, "signInWithCredential:failure", task.getException());
                                }
                            }
                        });
                        Log.d(TAG, "Got ID token.");
                    }
                } catch (ApiException e) {
                    e.getMessage();
                }
                break;
        }
    }

}
