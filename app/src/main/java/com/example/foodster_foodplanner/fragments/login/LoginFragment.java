package com.example.foodster_foodplanner.fragments.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodster_foodplanner.MainScreen;
import com.example.foodster_foodplanner.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;


public class LoginFragment extends Fragment {

    TextView signupLink;
    Button login;
    Intent intent;
    private GoogleSignInClient client;
    ImageButton use_google;
    private static final String TAG = "GOOGle";


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_google_client_id)).requestEmail().requestProfile().requestId().build();
        client = GoogleSignIn.getClient(this.requireContext(), options);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this.requireContext());
        if(account!=null){
            updateUI(account);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.login_fragement, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View my_view = view;
        signupLink = view.findViewById(R.id.signupLink);
        login = view.findViewById(R.id.loginBtn);
        use_google = view.findViewById(R.id.login_google);

        signupLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(my_view).navigate(R.id.action_loginFragment_to_signupFragment);
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
                Intent i = client.getSignInIntent();
                startActivityForResult(i, 111);
            }
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
                            updateUI(account);

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
    public void updateUI(GoogleSignInAccount account){
        Intent intent = new Intent(getActivity(), MainScreen.class);
        intent.putExtra("user_name", account.getDisplayName());
        startActivity(intent);
    }

}
