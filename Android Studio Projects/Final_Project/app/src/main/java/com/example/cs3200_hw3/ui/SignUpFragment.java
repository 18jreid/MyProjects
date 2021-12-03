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
import com.example.cs3200_hw3.databinding.FragmentSigninBinding;
import com.example.cs3200_hw3.databinding.FragmentSignupBinding;
import com.example.cs3200_hw3.viewmodels.UserViewModel;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        UserViewModel userViewModel = new UserViewModel();
        FragmentSignupBinding binding = FragmentSignupBinding.inflate(inflater, container, false);
        NavController controller = NavHostFragment.findNavController(this);

        EditText email = binding.emailSignUpEditText;
        EditText emailConfirmation = binding.emailSignUpConfirmEditText;
        EditText password = binding.passwordSignUpEditText;
        EditText passwordConfirmation = binding.passwordSignUpConfirmEditText;
        TextView errorText = binding.signUpErrorText;

        binding.signUpConfirmButton.setOnClickListener((v) -> {

            if (checkFields(email, emailConfirmation, password, passwordConfirmation)) {
                userViewModel.signUp(emailConfirmation.getText().toString(), passwordConfirmation.getText().toString(), controller, errorText);
            }
        });

        return binding.getRoot();
    }

    private boolean checkEmailsConfirm(TextView email, TextView emailConfirm) {
        if (email.getText().toString().equals(emailConfirm.getText().toString())) {
            return true;
        } else {
            emailConfirm.setError("Emails do not match");
            return false;
        }
    }

    private boolean checkPasswordsConfirm(TextView password, TextView passwordConfirm) {
        if (password.getText().toString().equals(passwordConfirm.getText().toString())) {
            return true;
        } else {
            passwordConfirm.setError("Passwords do not match");
            return false;
        }
    }

    private boolean checkPassword(TextView password) {
        if (password.getText().toString().equals("")) {
            password.setError("Enter a password");
            return false;
        }
        if (password.getText().toString().length() < 8) {
            password.setError("Password must be greater than 8 characters");
            return false;
        }

        return true;
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

    private boolean checkFields(TextView email, TextView emailConfirm, TextView password, TextView passwordConfirm) {
        if (!checkEmail(email) || !checkPassword(password) || !checkEmailsConfirm(email, emailConfirm) || !checkPasswordsConfirm(password, passwordConfirm)) {
            return false;
        }

        return true;
    }
}
