package com.example.cs3200_hw3.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.cs3200_hw3.R;
import com.example.cs3200_hw3.viewmodels.UserViewModel;

public class SignInFragment extends Fragment {
    public SignInFragment() {
        super(R.layout.fragment_signin);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        UserViewModel userViewModel = new UserViewModel();
        FragmentManager manager = getActivity().getSupportFragmentManager();

                view.findViewById(R.id.signUpButton).setOnClickListener((v) -> {
            manager.beginTransaction()
                    .replace(R.id.fragment_container, SignUpFragment.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack(null)
                    .commit();
        });

        EditText email = view.findViewById(R.id.emailEditText);
        EditText password = view.findViewById(R.id.editTextTextPassword);
        TextView errorText = view.findViewById(R.id.signInErrorText);

        view.findViewById(R.id.loginButton).setOnClickListener((v) -> {
            if (checkEmailAndPassword(email.getText().toString(), password.getText().toString(), errorText)) {
                userViewModel.login(email.getText().toString(), password.getText().toString(), manager, errorText);
            }
        });
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
