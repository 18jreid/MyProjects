package com.example.cs3200_hw3.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cs3200_hw3.R;
import com.example.cs3200_hw3.databinding.FragmentCreateNoteBinding;
import com.example.cs3200_hw3.databinding.FragmentProfileBinding;
import com.example.cs3200_hw3.models.Note;
import com.example.cs3200_hw3.viewmodels.UserViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateNote#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateNote extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        UserViewModel userViewModel = new UserViewModel();
        FragmentCreateNoteBinding binding = FragmentCreateNoteBinding.inflate(inflater, container, false);
        NavController controller = NavHostFragment.findNavController(this);

        binding.saveNote.setOnClickListener((view) -> {
            if (!binding.titleText.getText().toString().equals("")) {
                binding.saveNote.setEnabled(false);
                userViewModel.createNote(binding.titleText.getText().toString(), binding.noteText.getText().toString());
                controller.navigate(R.id.action_createNote_to_profileFragment);
            }
            else {
                binding.titleText.setError("Title cannot be empty!");
            }
        });

        return binding.getRoot();
    }
}