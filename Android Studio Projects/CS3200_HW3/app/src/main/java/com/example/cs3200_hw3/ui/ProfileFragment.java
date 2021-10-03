package com.example.cs3200_hw3.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cs3200_hw3.R;
import com.example.cs3200_hw3.viewmodels.UserViewModel;

public class ProfileFragment extends Fragment {
    public ProfileFragment() {
        super(R.layout.fragment_profile);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.logOutButton).setOnClickListener((v) -> {
            UserViewModel userViewModel = new UserViewModel();

            userViewModel.logout();
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, SignInFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();
        });
    }
}
