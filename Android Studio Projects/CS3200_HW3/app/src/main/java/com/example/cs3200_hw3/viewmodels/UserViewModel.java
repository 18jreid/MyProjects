package com.example.cs3200_hw3.viewmodels;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.FragmentManager;

import com.example.cs3200_hw3.R;
import com.example.cs3200_hw3.models.User;
import com.example.cs3200_hw3.ui.ProfileFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

public class UserViewModel {
    ObservableArrayList<User> clients = new ObservableArrayList<>();
    FirebaseFirestore db;
    FirebaseAuth auth;

    public UserViewModel() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
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

    public void signUp(String email, String password, FragmentManager manager, TextView error) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                Log.d("__FIREBASE", "NEW USER CREATED");
                // Take user to profile page
                manager.beginTransaction()
                        .replace(R.id.fragment_container, ProfileFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .commit();
            } else {
                Log.d("__FIREBASE", task.getException().toString());
                error.setText(task.getException().toString());
            }
        });
    }

    public void login(String email, String password, FragmentManager manager, TextView error) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                Log.d("__FIREBASE", "USER LOGGED IN");
                // Take user to profile page
                manager.beginTransaction()
                        .replace(R.id.fragment_container, ProfileFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack(null)
                        .commit();
            } else {
                Log.d("__FIREBASE", task.getException().toString());
                error.setText(task.getException().toString());
            }
        });
    }

    public void logout() {
        auth.signOut();
    }
}
