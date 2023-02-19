package com.example.foodster_foodplanner.fragments.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodster_foodplanner.MainScreen;
import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.fragments.signup.SignupFragment;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.List;

public class LoginFragment extends Fragment {

    TextView signupLink;
    TextView guestLogin;
    Button login;
    Intent intent;
    private GoogleSignInClient client;
    ImageButton use_google;

    FirebaseAuth firebaseAuth;
    CallbackManager callbackManager;
    LoginButton fbLogin;
    private static final String TAG = "GOOGle";

    Intent homeIntent;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseAuth = FirebaseAuth.getInstance();
        callbackManager = CallbackManager.Factory.create();

        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_google_client_id)).requestEmail().requestProfile().requestId().build();
        client = GoogleSignIn.getClient(this.requireContext(), options);
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this.requireContext());
//        if (account != null) {
//            updateUI(account);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signupLink = view.findViewById(R.id.signupLink);
        login = view.findViewById(R.id.loginBtn);
        use_google = view.findViewById(R.id.login_google);
        guestLogin = view.findViewById(R.id.guestLogin);

        fbLogin = initFbButton(view);
        signupLink.setOnClickListener(v -> {
            FragmentTransaction fragmentTransaction = requireActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainerView, new SignupFragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
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
                Intent i = client.getSignInIntent();
                startActivityForResult(i, 111);
            }
        });

        guestLogin.setOnClickListener(v -> {
            firebaseAuth.signInAnonymously()
                    .addOnCompleteListener(this.requireActivity(), task -> {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInAnonymously:success");
//                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            updateUI();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInAnonymously:failure", task.getException());
                        }
                    });
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);

                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            updateUI();

                        } else {
                            Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });

            } catch (ApiException e) {
                e.printStackTrace();
            }

        }

    }


    private void updateUI() {
        Toast.makeText(this.requireContext(), "Welcome " + FirebaseAuth.getInstance().getCurrentUser().getDisplayName(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this.requireContext(), MainScreen.class));
    }

    public LoginButton initFbButton(View view) {
        fbLogin = view.findViewById(R.id.facebookButton);
        fbLogin.setReadPermissions(List.of("email"));
        fbLogin.setFragment(this);
        fbLogin.setBackgroundResource(R.drawable.facebook);
        fbLogin.setCompoundDrawables(null, null, null, null);
        fbLogin.setText("");
        fbLogin.setLoginText("");
        fbLogin.setLogoutText("");

        fbLogin.registerCallback(callbackManager, new FacebookCallback<>() {
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
        return fbLogin;
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("TAG", "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this.requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.i("TAG", "signInWithCredential:success " + firebaseAuth.getCurrentUser().getDisplayName());
//                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        updateUI();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.i("TAG", "signInWithCredential:failure", task.getException());
                        Toast.makeText(requireContext(), "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
