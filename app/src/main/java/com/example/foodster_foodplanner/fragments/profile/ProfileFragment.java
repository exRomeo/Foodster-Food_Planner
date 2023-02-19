package com.example.foodster_foodplanner.fragments.profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodster_foodplanner.MainActivity;
import com.example.foodster_foodplanner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {

    TextView email;
    TextView name;
    TextView initials;

    TextView signout;
    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        email=view.findViewById(R.id.email);
        name=view.findViewById(R.id.display_name);
        initials=view.findViewById(R.id.initials_profile);
        signout=view.findViewById(R.id.signout);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if(user!=null){
            updateUI(user);
        }
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user!=null){
                    auth.signOut();
                    Toast.makeText(getContext(), user.getEmail()+ "You are signed out!", Toast.LENGTH_SHORT).show();
                    Intent login = new Intent(requireActivity(), MainActivity.class);
                    startActivity(login);
                }
            }
        });
    }
    private void updateUI(FirebaseUser user) {
        email.setText(user.getEmail());
        name.setText(user.getDisplayName());
        char initial= user.getDisplayName().charAt(0);
        initials.setText(initial);
    }
}