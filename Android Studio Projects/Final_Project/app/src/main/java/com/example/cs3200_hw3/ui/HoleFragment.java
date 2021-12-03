package com.example.cs3200_hw3.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.cs3200_hw3.R;
import com.example.cs3200_hw3.databinding.FragmentHoleBinding;
import com.example.cs3200_hw3.databinding.FragmentSigninBinding;
import com.example.cs3200_hw3.databinding.FragmentSignupBinding;
import com.example.cs3200_hw3.viewmodels.UserViewModel;
import com.google.firebase.auth.FirebaseAuth;

public class HoleFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        UserViewModel userViewModel = new UserViewModel();
        FragmentHoleBinding binding = FragmentHoleBinding.inflate(inflater, container, false);
        NavController controller = NavHostFragment.findNavController(this);



        return binding.getRoot();
    }
}
