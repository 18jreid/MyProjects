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

        binding.loginButton.setOnClickListener((v) -> {
            if (checkEmailAndPassword(email.getText().toString(), password.getText().toString(), errorText)) {
                userViewModel.login(email.getText().toString(), password.getText().toString(), controller, errorText);
            }
        });

        binding.signUpButton.setOnClickListener((view) -> {
            controller.navigate(R.id.action_signInFragment_to_signUpFragment);
        });

        return binding.getRoot();
    }

    private boolean checkEmail(String email, TextView error) {
        if (email.equals("")) {
            error.setText("Enter an email");
            return false;
        }
        if (!email.endsWith(".com")) {
            error.setText("Enter a valid email");
            return false;
        }


        return true;
    }

    private boolean checkPassword(String password, TextView error) {
        if(password.equals("")) {
            error.setText("Enter a password");
            return false;
        }

        return true;
    }

    private boolean checkEmailAndPassword(String email, String password, TextView error) {
        if (!checkEmail(email, error) || !checkPassword(password, error)) {
            return false;
        }

        return true;
    }
}
