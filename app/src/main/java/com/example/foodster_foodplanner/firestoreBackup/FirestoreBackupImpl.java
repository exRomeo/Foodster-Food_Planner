package com.example.foodster_foodplanner.firestoreBackup;

import android.util.Log;

import com.example.foodster_foodplanner.models.Meal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirestoreBackupImpl implements FirestoreBackup {
    private static FirestoreBackup firestoreBackup;
    private final DocumentReference docfavs = FirebaseFirestore.getInstance().document("foodsterData/users/"+FirebaseAuth.getInstance().getUid()+"/favorites/");
    private final DocumentReference docPlans = FirebaseFirestore.getInstance().document("foodsterData/users/"+FirebaseAuth.getInstance().getUid()+"/plans/");
    private FirestoreBackupImpl(){}

    public static synchronized FirestoreBackup getInstance(){
        if(firestoreBackup ==null)
            firestoreBackup = new FirestoreBackupImpl();
        return  firestoreBackup;
    }

    @Override
    public void backupMeal(Meal meal) {
            Map<String, Meal> mealMap = new HashMap<>();
            mealMap.put(meal.getStrMeal(), meal);
            if(meal.isFavorite()) {
                docfavs.collection(meal.getStrMeal()).add(mealMap).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.i("TAG", "onComplete: success");
                    } else {
                        Log.i("TAG", "onComplete: fail");
                    }
                });
            }else{
                docPlans.collection(meal.getStrMeal()).add(mealMap).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Log.i("TAG", "onComplete: success");
                    } else {
                        Log.i("TAG", "onComplete: fail");
                    }
                });
            }
    }

    @Override
    public void backupMealList(List<Meal> mealList){
        mealList.forEach(this::backupMeal);
    }

}
