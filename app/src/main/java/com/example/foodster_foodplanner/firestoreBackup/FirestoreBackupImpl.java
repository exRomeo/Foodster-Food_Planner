package com.example.foodster_foodplanner.firestoreBackup;

import android.util.Log;

import com.example.foodster_foodplanner.models.Meal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirestoreBackupImpl implements FirestoreBackup {
    private static FirestoreBackup firestoreBackup;
    private final FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    private static final String favoritesPath = "/favorite/meals/";
    private static final String plansPath = "/plans/meals/";

    private FirestoreBackupImpl() {
    }

    public static synchronized FirestoreBackup getInstance() {
        if (firestoreBackup == null)
            firestoreBackup = new FirestoreBackupImpl();
        return firestoreBackup;
    }

    @Override
    public void backupMeal(Meal meal) {

        if (meal.isFavorite()) {
            backFavorite(meal);
        } else {
            backPlanned(meal);
        }
    }

    @Override
    public void backupMealList(List<Meal> mealList) {
        mealList.forEach(this::backupMeal);
    }

    private void backFavorite(Meal meal) {
        Map<String, Meal> mealMap = new HashMap<>();
        mealMap.put(meal.getStrMeal(), meal);
        firestore.document(currentUserPath() + favoritesPath + meal.getStrMeal()).set(mealMap).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.i("TAG", "onComplete: success");
            } else {
                Log.i("TAG", "onComplete: fail");
            }
        });
    }

    private void backPlanned(Meal meal) {
        Map<String, Meal> mealMap = new HashMap<>();
        mealMap.put(meal.getStrMeal(), meal);

        firestore.document(currentUserPath() + plansPath + meal.getStrMeal()).set(mealMap).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.i("TAG", "onComplete: success");
            } else {
                Log.i("TAG", "onComplete: fail");
            }
        });

    }
    private String currentUserPath() {
        return "foodsterData/users/" + FirebaseAuth.getInstance().getUid();
    }
}
