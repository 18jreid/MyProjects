package com.example.contacts;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.room.Room;

import com.example.contacts.Components.MaterialInput;
import com.example.contacts.database.AppDatabase;
import com.example.contacts.presenters.NewContactPresenter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class ContactActivity extends AppCompatActivity implements NewContactPresenter.MVPView {
    NewContactPresenter presenter;
    private static int PICK_IMAGE = 1;
    ImageSelector imageSelector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new NewContactPresenter(this);

        LinearLayout mainLayout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setLayoutParams(params);
        params.setMargins(32, 24, 32, 24);

        // Get name, email, and phone number and image
        imageSelector = new ImageSelector(this, () -> {
            presenter.selectPicturePress();
        });
        MaterialInput nameInput = new MaterialInput(this, "Name");
        nameInput.setLayoutParams(params);
        MaterialInput email = new MaterialInput(this, "Email");
        email.setLayoutParams(params);
        MaterialInput phoneNumber = new MaterialInput(this, "Phone Number");
        phoneNumber.setLayoutParams(params);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String emailString = intent.getStringExtra("email");
        String phoneNumberString = intent.getStringExtra("phoneNumber");
        boolean status = intent.getBooleanExtra("status", false);
        if (status) {
            nameInput.setText(name);
            email.setText(emailString);
            phoneNumber.setText(phoneNumberString);

            String imageUri = intent.getStringExtra("imageUri");
            imageSelector.setImageUri(imageUri);
        }

        // Make Save and Cancel Button
        LinearLayout buttonsLayout = new LinearLayout(this);
        buttonsLayout.setGravity(Gravity.RIGHT);

        MaterialButton saveButton = new MaterialButton(this, null, R.attr.materialButtonStyle);
        saveButton.setText("Save");
        MaterialButton cancelButton = new MaterialButton(this, null, R.attr.borderlessButtonStyle);
        cancelButton.setText("Cancel");

        buttonsLayout.addView(cancelButton);
        buttonsLayout.addView(saveButton);

        cancelButton.setOnClickListener(view -> {
            presenter.cancel();
        });

        saveButton.setOnClickListener(view -> {
            if (!nameInput.getText().toString().equals("") && email.getText().toString().endsWith(".com") && phoneNumber.getText().toString().length() == 10) {
                if (status) {
                    presenter.updateContact(name, nameInput.getText().toString(), email.getText().toString(), phoneNumber.getText().toString(), imageSelector.getImageUri());
                }
                else {
                    presenter.createContact(nameInput.getText().toString(),
                            email.getText().toString(),
                            phoneNumber.getText().toString(),
                            imageSelector.getImageUri());
                }
                goBackToMainPage();
            }
            else {
                if (nameInput.getText().toString().isEmpty()) {
                    nameInput.setError("Enter Name");
                }
                else {
                    nameInput.setErrorEnabled(false);
                }

                if (!email.getText().toString().endsWith(".com")) {
                    email.setError("Enter valid email");
                }
                else {
                    email.setErrorEnabled(false);
                }

                if (phoneNumber.getText().toString().length() != 10) {
                    phoneNumber.setError("Enter valid number");
                }
                else {
                    phoneNumber.setErrorEnabled(false);
                }
            }
                }
            );

        mainLayout.addView(imageSelector);
        mainLayout.addView(nameInput);
        mainLayout.addView(email);
        mainLayout.addView(phoneNumber);
        mainLayout.addView(buttonsLayout);

        setContentView(mainLayout);
    }

    /**
     * Gets the correct database, so that there is only one and not multiple.
     * @return Returns database in use.
     */
    @Override
    public AppDatabase getContextDatabase() {
        return Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "contact-database").build();
    }

    /**
     * Sends user back to main contacts page.
     */
    @Override
    public void goBackToMainPage() {
        finish();
    }

    @Override
    public void goToPhotos() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        startActivityForResult(Intent.createChooser(intent,  "Select Picture"), PICK_IMAGE);
    }

    @Override
    public void displayImage(String uri) {
        imageSelector.setImageUri(uri);
    }

    @Override
    public void takePicture() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            String uri = data.getData().toString();
            presenter.handlePictureSelected(uri);
        }
    }
}
