package com.example.foodster_foodplanner;

import android.util.Log;

import com.example.foodster_foodplanner.models.Meal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class BackupMechanism {


    public static final String MEAL = "meal";
    private DocumentReference doc = FirebaseFirestore.getInstance().document("foodsterData/users/"+FirebaseAuth.getInstance().getUid()+"/favorites/");
    public void backupMeal(Meal meal) {
        if (meal.isFavorite()) {
            Map<String, Meal> mealMap = new HashMap<>();
            mealMap.put(String.valueOf(meal.getIdMeal()), meal);
            doc.collection(meal.getStrMeal()).add(mealMap).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    Log.i("TAG", "onComplete: succ");
                } else {
                    Log.i("TAG", "onComplete: fail");
                }
            });
        }
    }
}
