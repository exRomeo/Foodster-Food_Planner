package com.example.foodster_foodplanner.screen.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodster_foodplanner.R;
import com.example.foodster_foodplanner.Repository.RepositoryImpl;
import com.example.foodster_foodplanner.localdatabase.LocalDatabaseSource;
import com.example.foodster_foodplanner.screen.registration.RegistrationActivity;
import com.example.foodster_foodplanner.retrofitclient.RetrofitClientImpl;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ProfileActivity extends AppCompatActivity implements ProfileView {

    TextView email;
    TextView name;
    TextView initials;
    TextView signout;
    ImageButton back;
    Button backupFavorites;

    TextView favoritesCount;
    TextView plannedCount;
    ProfilePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ProfilePresenterImpl(RepositoryImpl.getInstance(RetrofitClientImpl.getInstance(), LocalDatabaseSource.getInstance(this)), this);
        setContentView(R.layout.activity_profile);
        email = findViewById(R.id.email);
        name = findViewById(R.id.display_name);
        initials = findViewById(R.id.initials_profile);
        signout = findViewById(R.id.signout);
        back = findViewById(R.id.BackIcon);
        backupFavorites = findViewById(R.id.backup_favs);
        favoritesCount = findViewById(R.id.favs_count);
        plannedCount = findViewById(R.id.plan_count);

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
                    Intent login = new Intent(ProfileActivity.this, RegistrationActivity.class);
                    presenter.removeUserData();
                    login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(login);
                    finish();
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        backupFavorites.setOnClickListener(v -> {
            if (FirebaseAuth.getInstance().getCurrentUser().isAnonymous()) {
                Snackbar.make(v, "Please Sign in with an account to use Backup!", Snackbar.LENGTH_SHORT).show();
            } else {
                presenter.backupFavorites();
                Snackbar.make(v, "Your Favorites are being Backed up!", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI(FirebaseUser user) {
        if (user.isAnonymous()) {
            name.setText("Hey Anonymous!");
            email.setText("Please signup to display your data");
            initials.setText("A");
        } else {
            name.setText(user.getEmail());
            initials.setText(getUserText(user));
            email.setText(" ");
        }
        presenter.getFavoritesCount();
        presenter.getPlannedCount();
    }

    private String getUserText(FirebaseUser user) {
        String userText = null;
        if (user != null) {
            if (user.isAnonymous()) {
                userText = "A";
            } else {
                if (user.getDisplayName() != null && !user.getDisplayName().isEmpty()) {
                    userText = user.getDisplayName().substring(0, 2).toUpperCase();
                } else {
                    if (user.getEmail() != null)
                        userText = user.getEmail().substring(0, 2).toUpperCase();
                }
            }
        }
        return userText;
    }

    @Override
    public void setFavoritesCount(String count) {
        favoritesCount.setText(count);
    }

    @Override
    public void setPlannedCount(String count) {
        plannedCount.setText(count);
    }
}