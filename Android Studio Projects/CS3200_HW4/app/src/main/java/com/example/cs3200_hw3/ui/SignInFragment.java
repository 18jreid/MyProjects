package com.example.cs3200_hw3.ui;

import android.os.Bundle;
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
import com.example.cs3200_hw3.databinding.FragmentSigninBinding;
import com.example.cs3200_hw3.viewmodels.UserViewModel;

public class SignInFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        UserViewModel userViewModel = new UserViewModel();
        FragmentSigninBinding binding = FragmentSigninBinding.inflate(inflater, container, false);
        NavController controller = NavHostFragment.findNavController(this);

        EditText email = binding.emailEditText;
        EditText password = binding.editTextTextPassword;
        TextView errorText = binding.signInErrorText;

        if (userViewModel.getAuth().getCurrentUser() != null) {
            controller.navigate(R.id.action_signInFragment_to_profileFragment);
        }
        else {
            binding.loginButton.setOnClickListener((v) -> {
                if (checkEmailAndPassword(email, password)) {
                    userViewModel.login(email.getText().toString(), password.getText().toString(), controller, errorText);
                }
            });

            binding.signUpButton.setOnClickListener((view) -> {
                controller.navigate(R.id.action_signInFragment_to_signUpFragment);
            });
        }

        return binding.getRoot();
    }

    private boolean checkEmail(TextView email) {
        if (email.equals("")) {
            email.setError("Enter an email");
            return false;
        }
        if (!email.getText().toString().endsWith(".com")) {
            email.setError("Enter a valid email");
            return false;
        }


        return true;
    }

    private boolean checkPassword(TextView password) {
        if(password.getText().toString().equals("")) {
            password.setError("Enter a password");
            return false;
        }

        return true;
    }

    private boolean checkEmailAndPassword(TextView email, TextView password) {
        if (!checkEmail(email) || !checkPassword(password)) {
            return false;
        }

        return true;
    }
}
