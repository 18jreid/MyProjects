package com.example.cs3200_hw3.viewmodels;

import android.util.Log;
import android.widget.TextView;

import androidx.databinding.ObservableArrayList;
import androidx.navigation.NavController;

import com.example.cs3200_hw3.R;
import com.example.cs3200_hw3.models.Scorecard;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserViewModel {
    FirebaseFirestore db;
    FirebaseAuth auth;
    String currentNoteTitle;
    String currentNoteBody;
    ObservableArrayList<Scorecard> scorecards = new ObservableArrayList<>();

    public UserViewModel() {
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        currentNoteTitle = "NO TITLE";
        currentNoteBody = "NO BODY";
    }

    public void signUp(String email, String password, NavController controller, TextView error) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                Log.d("__FIREBASE", "NEW USER CREATED");
                // Take user to profile page
                controller.navigate(R.id.action_signUpFragment_to_profileFragment);
            } else {
                Log.d("__FIREBASE", task.getException().toString());
                error.setText(task.getException().getMessage());
            }
        });
    }

    public void login(String email, String password, NavController controller,TextView error) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                Log.d("__FIREBASE", "USER LOGGED IN");
                // Take user to profile page
                controller.navigate(R.id.action_signInFragment_to_profileFragment);
            } else {
                Log.d("__FIREBASE", task.getException().toString());
                error.setText(task.getException().getMessage());
            }
        });
    }

    public void logout() {
        auth.signOut();
    }

    public String getUser() {
        return auth.getCurrentUser().getEmail();
    }

    public FirebaseAuth getAuth() {
        return auth;
    }

    public void saveScorecard(Scorecard scorecard) {
        db.collection("scorecards").add(scorecard)
                .addOnCompleteListener((task) -> {
                    if (task.isSuccessful()) {
                        scorecards.add(scorecard);
                        System.out.println("SUCCESSFULL\n\n\n");
                    } else {
                        // Use databinding to display error
                    }
                });
    }

    public ObservableArrayList<Scorecard> getScorecards() {
        return scorecards;
    }
}
