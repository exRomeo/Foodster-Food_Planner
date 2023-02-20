package com.example.foodster_foodplanner.firestoreBackup;

import android.content.Context;
import android.util.Log;

import com.example.foodster_foodplanner.models.Meal;
import com.example.foodster_foodplanner.models.MealModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

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
    public void backupMealList(List<Meal> mealList) {
        MealModel mealModel = new MealModel();
        mealModel.setMeals(mealList);
        firestore.document(currentUserPath() + favoritesPath).set(mealModel).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.i("TAG", "onComplete: success");
            } else {
                Log.i("TAG", "onComplete: fail");
            }
        });
    }



    private String currentUserPath() {
        return "foodsterData/" + FirebaseAuth.getInstance().getUid();
    }
}
