package com.example.cs3200_hw3.viewmodels;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;

import com.example.cs3200_hw3.R;
import com.example.cs3200_hw3.models.Note;
import com.example.cs3200_hw3.models.User;
import com.example.cs3200_hw3.ui.ProfileFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.Document;

import org.w3c.dom.Text;

import java.sql.Timestamp;
import java.util.ArrayList;

public class UserViewModel {
    ObservableArrayList<User> clients = new ObservableArrayList<>();
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

    public ObservableArrayList<User> getClients() {
        return clients;
    }

    public void saveUser(String email, String password) {
        User user = new User(email, password);
        db.collection("users").add(user)
                .addOnCompleteListener((task) -> {
                    if (task.isSuccessful()) {
                        clients.add(user);
                    } else {
                        // Use databinding to display error
                    }
                });
    }

    public void loadClients() {
        db.collection("user").get().addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                QuerySnapshot collection = task.getResult();
                clients.addAll(collection.toObjects(User.class));
            } else {
                // DISPLAY ERROR
            }
        });
    }

    public void signUp(String email, String password, NavController controller, TextView error) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                Log.d("__FIREBASE", "NEW USER CREATED");
                // Take user to profile page
                controller.navigate(R.id.action_signUpFragment_to_profileFragment);
            } else {
                Log.d("__FIREBASE", task.getException().toString());
                error.setText(task.getException().toString());
            }
        });
    }

    public void login(String email, String password, NavController controller, TextView error) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                Log.d("__FIREBASE", "USER LOGGED IN");
                // Take user to profile page
                controller.navigate(R.id.action_signInFragment_to_profileFragment);
            } else {
                Log.d("__FIREBASE", task.getException().toString());
                error.setText(task.getException().toString());
            }
        });
    }

    public void createNote(String title, String note) {
        Note newNote = new Note(auth.getCurrentUser().getEmail().toString(), title, note, new Timestamp(System.currentTimeMillis()).toString());
        db.collection("notes").add(newNote);
    }

    public void logout() {
        auth.signOut();
    }

    public String getUser() {
        return auth.getCurrentUser().getEmail().toString();
    }

    public void setNoteViewer(String title,String body) {
        currentNoteTitle = title;
        currentNoteBody = body;
    }

    public void refreshNoteViewer(TextView title, TextView body) {
        System.out.println(title);
        System.out.println(body);
        title.setText(currentNoteTitle);
        body.setText(currentNoteBody);
    }
}
