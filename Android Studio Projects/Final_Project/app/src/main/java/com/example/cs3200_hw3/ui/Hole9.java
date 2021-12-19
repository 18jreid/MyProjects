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
import com.example.cs3200_hw3.databinding.FragmentHole9Binding;
import com.example.cs3200_hw3.viewmodels.UserViewModel;
import com.google.android.gms.ads.AdRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Hole9 extends Fragment {
    QueryDocumentSnapshot myDoc = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        UserViewModel userViewModel = new UserViewModel();
        FragmentHole9Binding binding = FragmentHole9Binding.inflate(inflater, container, false);
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
                    binding.textView.setText("Par " + document.get("hole9par").toString());
                    binding.textView4.setText(document.get("hole9distance").toString() + " Yards");
                    binding.scoreText9.setText(document.get("hole9Score").toString());

                    myDoc = document;
                }
            } else {
                System.out.println("UNSUCCESSFUL");
            }
        });

        binding.button5.setOnClickListener((v) -> {
            controller.navigate(R.id.action_fragmenthole9_to_fragmenthole8, myBundle);

            if (!binding.scoreText9.getText().toString().equals("0")) {
                db.collection("scorecards").document(myDoc.getId())
                        .update("hole9Score", Integer.parseInt(binding.scoreText9.getText().toString()));
            }
        });

        binding.button6.setOnClickListener((v) -> {
            if (!binding.scoreText9.getText().toString().equals("0")) {
                db.collection("scorecards").document(myDoc.getId())
                        .update("hole9Score", Integer.parseInt(binding.scoreText9.getText().toString()));

                db.collection("scorecards").document(myDoc.getId())
                        .update("isFinished", true);
            }

            controller.navigate(R.id.action_fragmenthole9_to_profileFragment);
        });

        return binding.getRoot();
    }
}
