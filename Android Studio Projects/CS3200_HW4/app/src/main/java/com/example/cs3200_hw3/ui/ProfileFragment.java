package com.example.cs3200_hw3.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.cs3200_hw3.R;
import com.example.cs3200_hw3.databinding.FragmentProfileBinding;
import com.example.cs3200_hw3.databinding.FragmentSignupBinding;
import com.example.cs3200_hw3.models.Note;
import com.example.cs3200_hw3.viewmodels.UserViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        UserViewModel userViewModel = new UserViewModel();
        FragmentProfileBinding binding = FragmentProfileBinding.inflate(inflater, container, false);
        NavController controller = NavHostFragment.findNavController(this);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("notes")
                .whereEqualTo("user", userViewModel.getUser())
                .get().addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                QuerySnapshot collection = task.getResult();
                ArrayList<Note> noteArrayList = (ArrayList<Note>) collection.toObjects(Note.class);

                noteArrayList.forEach((note) -> {
                    TextView title = new TextView(getContext());
                    title.setText(note.getTitle() + "\n" + note.getStamp());

                    title.setOnClickListener((view) -> {
                        Bundle args = new Bundle();
                        args.putString("title", note.getTitle());
                        args.putString("body", note.getNote());

                        controller.navigate(R.id.action_profileFragment_to_noteViewer, args);
                    });

                    binding.linearLayout.addView(title);
                });
            }});


        binding.logOutButton.setOnClickListener((v) -> {
            userViewModel.logout();
            controller.navigate(R.id.action_profileFragment_to_signInFragment);
        });

        binding.addNote.setOnClickListener((view) -> {
            controller.navigate(R.id.action_profileFragment_to_createNote);
        });

        return binding.getRoot();
    }
}
