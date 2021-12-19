package com.example.cs3200_hw3.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.cs3200_hw3.R;
import com.example.cs3200_hw3.databinding.FragmentHole8Binding;
import com.example.cs3200_hw3.viewmodels.UserViewModel;
import com.google.android.gms.ads.AdRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Hole8 extends Fragment {
    QueryDocumentSnapshot myDoc = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        UserViewModel userViewModel = new UserViewModel();
        FragmentHole8Binding binding = FragmentHole8Binding.inflate(inflater, container, false);
        NavController controller = NavHostFragment.findNavController(this);

        binding.bannerAd2.loadAd(
                new AdRequest.Builder().build()
        );

        Bundle myBundle = getArguments();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("scorecards")
                .whereEqualTo("scorecardId", myBundle.get("scorecard"))
                .get().addOnCompleteListener((task) -> {
            if (task.isSuccessful()) {
                QuerySnapshot collection = task.getResult();
                for (QueryDocumentSnapshot document : collection) {
                    binding.textView.setText("Par " + document.get("hole8par").toString());
                    binding.textView4.setText(document.get("hole8distance").toString() + " Yards");
                    binding.scoreText8.setText(document.get("hole8Score").toString());

                    myDoc = document;
                }
            } else {
                System.out.println("UNSUCCESSFUL");
            }
        });

        binding.button5.setOnClickListener((v) -> {
            controller.navigate(R.id.action_fragmenthole8_to_fragmenthole7, myBundle);

            if (!binding.scoreText8.getText().toString().equals("0")) {
                db.collection("scorecards").document(myDoc.getId())
                        .update("hole8Score", Integer.parseInt(binding.scoreText8.getText().toString()));
            }
        });

        binding.button6.setOnClickListener((v) -> {
            controller.navigate(R.id.action_fragmenthole8_to_fragmenthole9, myBundle);

            if (!binding.scoreText8.getText().toString().equals("0")) {
                db.collection("scorecards").document(myDoc.getId())
                        .update("hole8Score", Integer.parseInt(binding.scoreText8.getText().toString()));
            }
        });

        return binding.getRoot();
    }
}
