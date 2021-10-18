package com.example.cs3200_hw3.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cs3200_hw3.R;
import com.example.cs3200_hw3.databinding.FragmentNoteViewerBinding;
import com.example.cs3200_hw3.databinding.FragmentProfileBinding;
import com.example.cs3200_hw3.viewmodels.UserViewModel;

public class NoteViewer extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        UserViewModel userViewModel = new UserViewModel();
        FragmentNoteViewerBinding binding = FragmentNoteViewerBinding.inflate(inflater, container, false);
        Bundle myArgs = getArguments();
        NavController controller = NavHostFragment.findNavController(this);

        binding.titleEditText.setText(myArgs.get("title").toString());
        binding.bodyEditText.setText(myArgs.get("body").toString());

        binding.saveButton.setOnClickListener((view) -> {
            userViewModel.createNote(binding.titleEditText.getText().toString(), binding.bodyEditText.getText().toString());
            controller.navigate(R.id.action_noteViewer_to_profileFragment);
        });

        return binding.getRoot();
    }
}