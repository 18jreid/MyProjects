package com.example.contacts.presenters;

import android.net.Uri;
import android.provider.ContactsContract;

import com.example.contacts.database.AppDatabase;
import com.example.contacts.models.Contact;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NewContactPresenter {
    private MVPView view;
    private AppDatabase database;

    public interface MVPView {
        AppDatabase getContextDatabase();
        void goBackToMainPage();
        void goToPhotos();
        void displayImage(String uri);
        void takePicture();
    }

    public NewContactPresenter(MVPView view) {
        this.view = view;
        database = view.getContextDatabase();
    }

    /**
     * Creates Contact
     * @param name: Name of contact.
     * @param email: Email of contact.
     * @param phoneNumber: Phone number of contact.
     */
    public void createContact(String name, String email, String phoneNumber, String imageUri) {
        // Check to make sure contents is not empty
        new Thread(() -> {
            Contact contact = new Contact();
            contact.name = name;
            contact.email = email;
            contact.phoneNumber = phoneNumber;
            contact.isComplete = false;
            contact.imageUri = imageUri;
            database.getContactDao().insert(contact);
            view.goBackToMainPage();
        }).start();
    }

    public void cancel() {
        view.goBackToMainPage();
    }

    public void selectPicturePress() {
        view.goToPhotos();
    }

    public void handlePictureSelected(String uri) {
        view.displayImage(uri);
    }

    /**
     * Deletes contact from database.
     * @param name: name of contact to delete.
     */
    public void deleteContact(String name) {
        new Thread(() -> {
            ArrayList<Contact> contacts = (ArrayList<Contact>) database.getContactDao().getAllContacts();
            Contact deleteContact = null;
            for (Contact contact : contacts) {
                if (contact.name.equals(name)) {
                    deleteContact = contact;
                }
            }
            database.getContactDao().delete(deleteContact);
        }).start();
    }

    public void updateContact(String contactToBeUpdated, String newName, String newEmail, String newPhoneNumber, String newImageUri) {
        new Thread(() -> {
            ArrayList<Contact> contacts = (ArrayList<Contact>) database.getContactDao().getAllContacts();
            for (Contact contact : contacts) {
                if (contact.name.equals(contactToBeUpdated)) {
                    contact.name = newName;
                    contact.email = newEmail;
                    contact.phoneNumber = newPhoneNumber;
                    contact.imageUri = newImageUri;
                    database.getContactDao().update(contact);
                }
            }
        }).start();
    }

    public void handleTakePicturePress() {
        view.goToPhotos();
    }
}
