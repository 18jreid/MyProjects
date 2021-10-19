package com.example.cs3200_hw3.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.example.cs3200_hw3.R;
import com.example.cs3200_hw3.databinding.FragmentProfileBinding;
import com.example.cs3200_hw3.databinding.FragmentSignupBinding;
import com.example.cs3200_hw3.models.Note;
import com.example.cs3200_hw3.viewmodels.UserViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
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
                .orderBy("stamp", Query.Direction.DESCENDING)
                .whereEqualTo("user", userViewModel.getUser())
                .get().addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                QuerySnapshot collection = task.getResult();
                for (QueryDocumentSnapshot document : collection) {
                    Note note = document.toObject(Note.class);
                    System.out.println(note.getTitle());
                    CardView card = new CardView(getContext());
                    LinearLayout layout = new LinearLayout(getContext());
                    TextView title = new TextView(getContext());
                    TextView timeStamp = new TextView(getContext());

                    title.setTextColor(Color.BLACK);
                    title.setTextSize(20);
                    title.setText(note.getTitle());

                    timeStamp.setText(note.getStamp());
                    ViewGroup.LayoutParams params = new ViewGroup.MarginLayoutParams(1000, 175);

                    layout.addView(title);
                    layout.addView(timeStamp);
                    layout.setOrientation(LinearLayout.VERTICAL);

                    card.addView(layout);
                    card.setLayoutParams(params);

                    card.setOnClickListener((view) -> {
                        Log.d("__FIREBASE", db.collection("notes").document(note.toString()).toString());
                        Bundle args = new Bundle();
                        args.putString("title", note.getTitle());
                        args.putString("body", note.getNote());
                        args.putString("id", document.getId());

                        controller.navigate(R.id.action_profileFragment_to_noteViewer, args);
                    });

                    binding.linearLayout.addView(card);
                }
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
