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

            if (checkFields(
                    email.getText().toString(),
                    emailConfirmation.getText().toString(),
                    password.getText().toString(),
                    passwordConfirmation.getText().toString(),
                    errorText)) {
                userViewModel.signUp(emailConfirmation.getText().toString(), passwordConfirmation.getText().toString(), controller, errorText);
            }
        });

        return binding.getRoot();
    }

    private boolean checkEmailsConfirm(String email, String emailConfirm, TextView error) {
        if (email.equals(emailConfirm)) {
            return true;
        } else {
            error.setText("Emails do not match");
            return false;
        }
    }

    private boolean checkPasswordsConfirm(String password, String passwordConfirm, TextView error) {
        if (password.equals(passwordConfirm)) {
            return true;
        } else {
            error.setText("Passwords do not match");
            return false;
        }
    }

    private boolean checkPassword(String password, TextView error) {
        if (password.equals("")) {
            error.setText("Enter a password");
            return false;
        }
        if (password.length() < 8) {
            error.setText("Password must be greater than 8 characters");
            return false;
        }

        return true;
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

    private boolean checkFields(String email, String emailConfirm, String password, String passwordConfirm, TextView error) {
        if (!checkEmail(email, error) || !checkPassword(password, error) || !checkEmailsConfirm(email, emailConfirm, error) || !checkPasswordsConfirm(password, passwordConfirm, error)) {
            return false;
        }

        return true;
    }
}
