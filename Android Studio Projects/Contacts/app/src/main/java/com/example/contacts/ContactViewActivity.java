package com.example.contacts;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import com.example.contacts.database.AppDatabase;
import com.example.contacts.models.Contact;
import com.example.contacts.presenters.ContactViewPresenter;
import com.example.contacts.presenters.NewContactPresenter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class ContactViewActivity extends AppCompatActivity implements ContactViewPresenter.MVPView{
    ContactViewPresenter presenter;
    private String name;
    private String email;
    private String phoneNumber;
    private String imageUri;
    private final int REQUEST_PHONE_PERMISSIONS = 0;
    private final int REQUESTION_MESSAGE_PERMISSIONS = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ContactViewPresenter(this);

        // Grabs data needed for Contact view.
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        email = intent.getStringExtra("email");
        phoneNumber = intent.getStringExtra("phoneNumber");
        imageUri = intent.getStringExtra("imageUri");

        // Creates image view
        AppCompatImageView imageView = new AppCompatImageView(this);
        if (!imageUri.equals("")) {
            imageView.setImageURI(Uri.parse(imageUri));
        }
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        FrameLayout imageLayout = new FrameLayout(this);
        imageLayout.addView(imageView);
        imageLayout.setBackgroundColor(Color.DKGRAY);
        FrameLayout.LayoutParams imageParams = new FrameLayout.LayoutParams(825, 825);
        imageView.setLayoutParams(imageParams);

        // Creates name header.
        MaterialTextView nameHeader = new MaterialTextView(this);
        nameHeader.setTextSize(36);
        nameHeader.setText(name);
        nameHeader.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        nameHeader.setPadding(0, 10, 0, 10);
        nameHeader.setTextColor(Color.WHITE);
        nameHeader.setBackgroundColor(Color.rgb(153, 144, 144));

        // Creates phone text.
        LinearLayout.LayoutParams infoParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        infoParams.setMargins(16, 80, 80, 0);
        MaterialTextView phoneText = new MaterialTextView(this);
        phoneText.setText("  Mobile - " + phoneNumber);
        phoneText.setTextSize(24);
        phoneText.setTextColor(Color.BLACK);
        phoneText.setLayoutParams(infoParams);

        // Creates email text.
        MaterialTextView emailText = new MaterialTextView(this);
        emailText.setText("  Email - " + email);
        emailText.setTextSize(24);
        emailText.setTextColor(Color.BLACK);
        emailText.setLayoutParams(infoParams);


        // Creates call, email, and message buttons
        LinearLayout actionLayout = new LinearLayout(this);
        actionLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams actionLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        actionLayoutParams.setMargins(24, 0, 24, 0);
        actionLayout.setLayoutParams(actionLayoutParams);

        FloatingActionButton callFab = new FloatingActionButton(this);
        callFab.setImageResource(R.drawable.ic_baseline_call_24);
        LinearLayout.LayoutParams actionButtonParams  = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        actionButtonParams.setMargins(26, 26, 26, 26);
        callFab.setLayoutParams(actionButtonParams);
        callFab.setOnClickListener((view) -> {
            presenter.handleCallPress(phoneNumber);
        });

        FloatingActionButton emailFab = new FloatingActionButton(this);
        emailFab.setImageResource(R.drawable.ic_baseline_email_24);
        emailFab.setLayoutParams(actionButtonParams);
        emailFab.setOnClickListener((view) -> {
            presenter.handleEmailPress(email);
        });

        FloatingActionButton messageFab = new FloatingActionButton(this);
        messageFab.setImageResource(R.drawable.ic_baseline_message_24);
        messageFab.setLayoutParams(actionButtonParams);
        messageFab.setOnClickListener((view) -> {
            presenter.handleMessagePress(phoneNumber);
        });

        FloatingActionButton moreFab = new FloatingActionButton(this);
        moreFab.setImageResource(R.drawable.ic_baseline_more_vert_24);
        moreFab.setLayoutParams(actionButtonParams);
        PopupMenu popupMenu = new PopupMenu(this, moreFab);
        popupMenu.getMenu().add("Delete");
        popupMenu.getMenu().add("Edit");
        moreFab.setOnClickListener((view) -> {
            popupMenu.show();
        });

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getTitle().toString().equals("Delete")) {
                    presenter.deleteContact(name);
                    goBackToMainPage();
                }
                if (item.getTitle().toString().equals("Edit")) {
                    presenter.goToContactsPage();
                    goBackToMainPage();

                }
                return false;
            }
        });

        actionLayout.addView(callFab);
        actionLayout.addView(emailFab);
        actionLayout.addView(messageFab);
        actionLayout.addView(moreFab);
        actionLayout.setGravity(Gravity.RIGHT | Gravity.TOP);

        // Linear layout for the view.
        FrameLayout mainLayout = new FrameLayout(this);
        FrameLayout actionButtonLayout = new FrameLayout(this);
        LinearLayout infoLayout = new LinearLayout(this);
        ScrollView scrollView = new ScrollView(this);

        infoLayout.setOrientation(LinearLayout.VERTICAL);
        infoLayout.addView(imageLayout);
        imageLayout.addView(actionButtonLayout);
        infoLayout.addView(nameHeader);
        infoLayout.addView(phoneText);
        infoLayout.addView(emailText);
        actionButtonLayout.addView(actionLayout);

        mainLayout.addView(scrollView);
        scrollView.addView(infoLayout);

        // Sets texts views in correct positions.
        setContentView(mainLayout);
    }

    @Override
    public AppDatabase getContextDatabase() {
        return Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "contact-database").build();
    }

    @Override
    public void goBackToMainPage() {
        finish();
    }

    /**
     * Sends user to the new Contact page.
     */
    @Override
    public void goToContactsPage() {
        Intent intent = new Intent(this, ContactActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("email", email);
        intent.putExtra("phoneNumber", phoneNumber);
        intent.putExtra("imageUri", imageUri);
        intent.putExtra("status", true);

        startActivity(intent);
    }

    @Override
    public void makeCall(String phoneNumber) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(callIntent);
        }
        else {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_PERMISSIONS);
            makeCall(phoneNumber);
        }
    }

    @Override
    public void sendMessage(String phoneNumber) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            Intent messageIntent = new Intent(Intent.ACTION_VIEW);
            messageIntent.setData(Uri.parse("sms:" + phoneNumber));
            startActivity(messageIntent);
        }
        else {
            requestPermissions(new String[]{Manifest.permission.SEND_SMS}, REQUESTION_MESSAGE_PERMISSIONS);
            sendMessage(phoneNumber);
        }
    }

    @Override
    public void sendEmail(String email) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + email));
        startActivity(emailIntent);
    }
}
