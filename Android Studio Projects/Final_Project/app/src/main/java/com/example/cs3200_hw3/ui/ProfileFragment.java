package com.example.cs3200_hw3.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableArrayList;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.cs3200_hw3.R;
import com.example.cs3200_hw3.databinding.FragmentProfileBinding;
import com.example.cs3200_hw3.models.Scorecard;
import com.example.cs3200_hw3.viewmodels.UserViewModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

public class ProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        UserViewModel userViewModel = new UserViewModel();
        FragmentProfileBinding binding = FragmentProfileBinding.inflate(inflater, container, false);
        NavController controller = NavHostFragment.findNavController(this);
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        binding.logOutButton.setOnClickListener((v) -> {
            userViewModel.logout();
            controller.navigate(R.id.action_profileFragment_to_signInFragment);
        });

        binding.addNote.setOnClickListener((v) -> {
            controller.navigate(R.id.action_profileFragment_to_createScorecardFragment);
        });

        db.collection("scorecards")
                .whereEqualTo("user", userViewModel.getUser())
                .get().addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                QuerySnapshot collection = task.getResult();
                int index = 1;
                for (QueryDocumentSnapshot document : collection) {
                    Scorecard scorecard = document.toObject(Scorecard.class);

                    TextView test = new TextView(getContext());
                    test.setText("Scorecard " + index + " Total Score: " + scorecard.getTotalScore() + " Finished: " + scorecard.isFinished());
                    binding.scorecardsLayout.addView(test);
                    index += 1;

                    test.setOnClickListener((v) -> {
                        controller.navigate(R.id.action_profileFragment_to_holeFragment);
                    });
                }
            }
        });


        return binding.getRoot();
    }
}
