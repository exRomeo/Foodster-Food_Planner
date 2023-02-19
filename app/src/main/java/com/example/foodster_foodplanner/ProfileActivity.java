package com.example.foodster_foodplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
        email.setText(user.getEmail());
        String display = user.getDisplayName();
        if (display.length()==0) {
            String initial = "UN";
            name.setText(" ");
            initials.setText(initial);
        } else {
            name.setText(user.getDisplayName());
            String initial = " ";
            initial += user.getDisplayName().charAt(0);
            initials.setText(initial);
        }
    }
}