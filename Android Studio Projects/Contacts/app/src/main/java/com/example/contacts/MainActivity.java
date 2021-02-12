package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.contacts.Components.ContactItem;
import com.example.contacts.database.AppDatabase;
import com.example.contacts.models.Contact;
import com.example.contacts.presenters.MainPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainPresenter.MVPView {
    MainPresenter presenter;
    LinearLayout mainLayout;
    LinearLayout contactsLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MainPresenter(this);

        // Create new contact button
        FloatingActionButton newContactButton = new FloatingActionButton(this);
        newContactButton.setImageResource(R.drawable.ic_baseline_add_24);
        newContactButton.setOnClickListener(view -> {
            presenter.goToContactsPage();
        });
        FrameLayout.LayoutParams newContactParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        newContactButton.setLayoutParams(newContactParams);
        newContactButton.setX(900);
        newContactButton.setY(1400);

        // Create layouts
        FrameLayout mainLayout = new FrameLayout(this);
        ScrollView scrollView = new ScrollView(this);
        FrameLayout scrollFrameLayout = new FrameLayout(this);
        contactsLayout = new LinearLayout(this);

        scrollView.addView(scrollFrameLayout);
        scrollFrameLayout.addView(contactsLayout);
        contactsLayout.setOrientation(LinearLayout.VERTICAL);

        // Set widget position on screen
        mainLayout.addView(scrollView);
        mainLayout.addView(newContactButton);

        setContentView(mainLayout);
    }

    /**
     * Puts all Contacts on the layout.
     * @param contacts: ArrayList of type contacts (from database).
     */
    @Override
    public void renderContacts(ArrayList<Contact> contacts) {
        runOnUiThread(() -> {
            contactsLayout.removeAllViews();
            contacts.forEach(contact -> {
                ContactItem contactItem = new ContactItem(this, contact);
                contactsLayout.addView(contactItem);
                contactItem.setOnClickListener(view -> {
                    presenter.goToContactsViewPage(contact);
                });
            });
        });
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
     * Sends user to the new Contact page.
     */
    @Override
    public void goToContactsPage() {
        Intent intent = new Intent(this, ContactActivity.class);
        startActivity(intent);
    }

    /**
     * Sends user to a specific Contact view page.
     * @param contact: The contact the user wants to access.
     */
    @Override
    public void goToContactsViewPage(Contact contact) {
        Intent intent = new Intent(this, ContactViewActivity.class);
        intent.putExtra("name", contact.name);
        intent.putExtra("email", contact.email);
        intent.putExtra("phoneNumber", contact.phoneNumber);
        intent.putExtra("imageUri", contact.imageUri);
        intent.putExtra("status", false);
        startActivity(intent);
    }

    /**
     * When the user returns from any other activity, refresh the contacts.
     */
    @Override
    protected void onResume() {
        super.onResume();
        presenter.refreshContacts();
    }
}