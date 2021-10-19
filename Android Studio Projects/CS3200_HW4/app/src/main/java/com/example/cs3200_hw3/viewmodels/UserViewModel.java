package com.example.cs3200_hw3.viewmodels;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;

import com.example.cs3200_hw3.R;
import com.example.cs3200_hw3.models.Note;
import com.example.cs3200_hw3.models.User;
import com.example.cs3200_hw3.ui.ProfileFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;
import com.google.firestore.v1.Document;

import org.w3c.dom.Text;

import java.sql.Timestamp;
import java.util.ArrayList;

public class UserViewModel {
    FirebaseFirestore db;
    FirebaseAuth auth;
    String currentNoteTitle;
    String currentNoteBody;

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

    public void createNote(String title, String note) {
        Note newNote = new Note(auth.getCurrentUser().getEmail().toString(), title, note, new Timestamp(System.currentTimeMillis()).toString());
        db.collection("notes").add(newNote);
    }

    public void deleteNote(String note) {
        Log.d("__FIREBASE", db.collection("notes").document(note).toString());
        db.collection("notes").document(note).delete();
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
}
