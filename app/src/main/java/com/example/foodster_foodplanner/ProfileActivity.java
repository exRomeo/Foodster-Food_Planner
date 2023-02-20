package com.example.foodster_foodplanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ProfileActivity extends AppCompatActivity {

    TextView email;
    TextView name;
    TextView initials;
    TextView signout;
    ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_screen);
        email = findViewById(R.id.email);
        name = findViewById(R.id.display_name);
        initials = findViewById(R.id.initials_profile);
        signout = findViewById(R.id.signout);
        back = findViewById(R.id.BackIcon);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            updateUI(user);
        }
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user != null) {
                    auth.signOut();
                    Toast.makeText(ProfileActivity.this, user.getEmail() + "You are signed out!", Toast.LENGTH_SHORT).show();
                    Intent login = new Intent(ProfileActivity.this, MainActivity.class);
                    startActivity(login);
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void updateUI(FirebaseUser user) {
        if (user.isAnonymous()){
            name.setText("Hey Anonymous!");
            email.setText("Please signup to display your data");
            initials.setText("A");
        }
        else {
            name.setText(user.getEmail());
            initials.setText(getUserText(user));
            email.setText(" ");
        }
    }

    private String getUserText(FirebaseUser user){
        String userText = null;
        if (user != null) {
            if (user.isAnonymous()) {
                userText = "A";
            } else {
                if(user.getDisplayName()!=null) {
                    userText = user.getDisplayName().substring(0,2).toUpperCase();
                } else {
                    if( user.getEmail() != null)
                        userText = user.getEmail().substring(0,2).toUpperCase();
                }
            }
        }
        return userText;
    }
}