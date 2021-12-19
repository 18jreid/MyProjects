package com.example.cs3200_hw3.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.cs3200_hw3.R;
import com.example.cs3200_hw3.databinding.FragmentCreateScorecardBinding;
import com.example.cs3200_hw3.databinding.FragmentProfileBinding;
import com.example.cs3200_hw3.models.Scorecard;
import com.example.cs3200_hw3.viewmodels.UserViewModel;
import com.google.android.gms.ads.AdRequest;
import com.google.firebase.firestore.FirebaseFirestore;

public class CreateScorecardFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        UserViewModel userViewModel = new UserViewModel();
        FragmentCreateScorecardBinding binding = FragmentCreateScorecardBinding.inflate(inflater, container, false);

        binding.bannerAd2.loadAd(
                new AdRequest.Builder().build()
        );

        NavController controller = NavHostFragment.findNavController(this);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CardView hole1 = binding.hole1;
        CardView hole2 = binding.hole2;
        CardView hole3 = binding.hole3;
        CardView hole4 = binding.hole4;
        CardView hole5 = binding.hole5;
        CardView hole6 = binding.hole6;
        CardView hole7 = binding.hole7;
        CardView hole8 = binding.hole8;
        CardView hole9 = binding.hole9;
        CardView hole10 = binding.hole10;
        CardView hole11 = binding.hole11;
        CardView hole12 = binding.hole12;
        CardView hole13 = binding.hole13;
        CardView hole14 = binding.hole14;
        CardView hole15 = binding.hole15;
        CardView hole16 = binding.hole16;
        CardView hole17 = binding.hole17;
        CardView hole18 = binding.hole18;

        binding.nineHolesButton.setOnClickListener((v) -> {
            binding.frontNineButton.setVisibility(View.INVISIBLE);
            binding.backNineButton.setVisibility(View.INVISIBLE);

            binding.nineHolesButton.setAlpha(1);
            binding.eighteenHolesButton.setAlpha(.5f);
        });

        binding.eighteenHolesButton.setOnClickListener((v) -> {
            binding.frontNineButton.setVisibility(View.VISIBLE);
            binding.backNineButton.setVisibility(View.VISIBLE);

            binding.nineHolesButton.setAlpha(.5f);
            binding.eighteenHolesButton.setAlpha(1);

            hole1.setVisibility(View.VISIBLE);
            hole2.setVisibility(View.VISIBLE);
            hole3.setVisibility(View.VISIBLE);
            hole4.setVisibility(View.VISIBLE);
            hole5.setVisibility(View.VISIBLE);
            hole6.setVisibility(View.VISIBLE);
            hole7.setVisibility(View.VISIBLE);
            hole8.setVisibility(View.VISIBLE);
            hole9.setVisibility(View.VISIBLE);

            hole10.setVisibility(View.INVISIBLE);
            hole11.setVisibility(View.INVISIBLE);
            hole12.setVisibility(View.INVISIBLE);
            hole13.setVisibility(View.INVISIBLE);
            hole14.setVisibility(View.INVISIBLE);
            hole15.setVisibility(View.INVISIBLE);
            hole16.setVisibility(View.INVISIBLE);
            hole17.setVisibility(View.INVISIBLE);
            hole18.setVisibility(View.INVISIBLE);
        });

        binding.frontNineButton.setOnClickListener((v) -> {
            hole1.setVisibility(View.VISIBLE);
            hole2.setVisibility(View.VISIBLE);
            hole3.setVisibility(View.VISIBLE);
            hole4.setVisibility(View.VISIBLE);
            hole5.setVisibility(View.VISIBLE);
            hole6.setVisibility(View.VISIBLE);
            hole7.setVisibility(View.VISIBLE);
            hole8.setVisibility(View.VISIBLE);
            hole9.setVisibility(View.VISIBLE);

            hole10.setVisibility(View.INVISIBLE);
            hole11.setVisibility(View.INVISIBLE);
            hole12.setVisibility(View.INVISIBLE);
            hole13.setVisibility(View.INVISIBLE);
            hole14.setVisibility(View.INVISIBLE);
            hole15.setVisibility(View.INVISIBLE);
            hole16.setVisibility(View.INVISIBLE);
            hole17.setVisibility(View.INVISIBLE);
            hole18.setVisibility(View.INVISIBLE);

            binding.frontNineButton.setAlpha(1);
            binding.backNineButton.setAlpha(0.5F);
        });

        binding.backNineButton.setOnClickListener((v) -> {
            hole1.setVisibility(View.INVISIBLE);
            hole2.setVisibility(View.INVISIBLE);
            hole3.setVisibility(View.INVISIBLE);
            hole4.setVisibility(View.INVISIBLE);
            hole5.setVisibility(View.INVISIBLE);
            hole6.setVisibility(View.INVISIBLE);
            hole7.setVisibility(View.INVISIBLE);
            hole8.setVisibility(View.INVISIBLE);
            hole9.setVisibility(View.INVISIBLE);

            hole10.setVisibility(View.VISIBLE);
            hole11.setVisibility(View.VISIBLE);
            hole12.setVisibility(View.VISIBLE);
            hole13.setVisibility(View.VISIBLE);
            hole14.setVisibility(View.VISIBLE);
            hole15.setVisibility(View.VISIBLE);
            hole16.setVisibility(View.VISIBLE);
            hole17.setVisibility(View.VISIBLE);
            hole18.setVisibility(View.VISIBLE);

            binding.frontNineButton.setAlpha(0.5F);
            binding.backNineButton.setAlpha(1);
        });

        binding.createScorecardButton.setOnClickListener((v) -> {
            controller.navigate(R.id.action_createScorecardFragment_to_profileFragment);

            if (binding.nineHolesButton.getAlpha() == 1) {
                Scorecard scorecard = new Scorecard(
                        userViewModel.getUser(), true,
                        0, Integer.parseInt(binding.hole1par.getText().toString()), Integer.parseInt(binding.hole1distance.getText().toString()),
                        0, Integer.parseInt(binding.hole2par.getText().toString()), Integer.parseInt(binding.hole2distance.getText().toString()),
                        0, Integer.parseInt(binding.hole3par.getText().toString()), Integer.parseInt(binding.hole3distance.getText().toString()),
                        0, Integer.parseInt(binding.hole4par.getText().toString()), Integer.parseInt(binding.hole4distance.getText().toString()),
                        0, Integer.parseInt(binding.hole5par.getText().toString()), Integer.parseInt(binding.hole5distance.getText().toString()),
                        0, Integer.parseInt(binding.hole6par.getText().toString()), Integer.parseInt(binding.hole6distance.getText().toString()),
                        0, Integer.parseInt(binding.hole7par.getText().toString()), Integer.parseInt(binding.hole7distance.getText().toString()),
                        0, Integer.parseInt(binding.hole8par.getText().toString()), Integer.parseInt(binding.hole8distance.getText().toString()),
                        0, Integer.parseInt(binding.hole9par.getText().toString()), Integer.parseInt(binding.hole9distance.getText().toString())
                );

                userViewModel.saveScorecard(scorecard);

            } else {
                Scorecard scorecard = new Scorecard(
                        userViewModel.getUser(), true,
                        0, Integer.parseInt(binding.hole1par.getText().toString()), Integer.parseInt(binding.hole1distance.getText().toString()),
                        0, Integer.parseInt(binding.hole2par.getText().toString()), Integer.parseInt(binding.hole2distance.getText().toString()),
                        0, Integer.parseInt(binding.hole3par.getText().toString()), Integer.parseInt(binding.hole3distance.getText().toString()),
                        0, Integer.parseInt(binding.hole4par.getText().toString()), Integer.parseInt(binding.hole4distance.getText().toString()),
                        0, Integer.parseInt(binding.hole5par.getText().toString()), Integer.parseInt(binding.hole5distance.getText().toString()),
                        0, Integer.parseInt(binding.hole6par.getText().toString()), Integer.parseInt(binding.hole6distance.getText().toString()),
                        0, Integer.parseInt(binding.hole7par.getText().toString()), Integer.parseInt(binding.hole7distance.getText().toString()),
                        0, Integer.parseInt(binding.hole8par.getText().toString()), Integer.parseInt(binding.hole8distance.getText().toString()),
                        0, Integer.parseInt(binding.hole9par.getText().toString()), Integer.parseInt(binding.hole9distance.getText().toString()),
                        0, Integer.parseInt(binding.hole10par.getText().toString()), Integer.parseInt(binding.hole10distance.getText().toString()),
                        0, Integer.parseInt(binding.hole11par.getText().toString()), Integer.parseInt(binding.hole11distance.getText().toString()),
                        0, Integer.parseInt(binding.hole12par.getText().toString()), Integer.parseInt(binding.hole12distance.getText().toString()),
                        0, Integer.parseInt(binding.hole13par.getText().toString()), Integer.parseInt(binding.hole13distance.getText().toString()),
                        0, Integer.parseInt(binding.hole14par.getText().toString()), Integer.parseInt(binding.hole14distance.getText().toString()),
                        0, Integer.parseInt(binding.hole15par.getText().toString()), Integer.parseInt(binding.hole15distance.getText().toString()),
                        0, Integer.parseInt(binding.hole16par.getText().toString()), Integer.parseInt(binding.hole16distance.getText().toString()),
                        0, Integer.parseInt(binding.hole17par.getText().toString()), Integer.parseInt(binding.hole17distance.getText().toString()),
                        0, Integer.parseInt(binding.hole18par.getText().toString()), Integer.parseInt(binding.hole18distance.getText().toString())
                );

                userViewModel.saveScorecard(scorecard);
            }
        });

        return binding.getRoot();
    }
}
