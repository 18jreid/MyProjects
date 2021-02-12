package com.example.contacts.presenters;

import android.content.Intent;

import com.example.contacts.ContactActivity;
import com.example.contacts.database.AppDatabase;
import com.example.contacts.models.Contact;

import java.util.ArrayList;

public class ContactViewPresenter {
    public interface MVPView {
        public AppDatabase getContextDatabase();
        public void goBackToMainPage();
        public void goToContactsPage();
        public void makeCall(String phoneNumber);
        public void sendMessage(String phoneNumber);
        public void sendEmail(String email);
    }

    private MVPView view;
    private AppDatabase database;
    private ArrayList<Contact> contacts;

    public ContactViewPresenter(MVPView view) {
        this.view = view;
        database = view.getContextDatabase();
    }

    /**
     * Deletes contact from database.
     * @param name: name of contact to delete.
     */
    public void deleteContact(String name) {
        new Thread(() -> {
            contacts = (ArrayList<Contact>) database.getContactDao().getAllContacts();
            Contact deleteContact = null;
            for (Contact contact : contacts) {
                if (contact.name.equals(name)) {
                    deleteContact = contact;
                }
            }
            database.getContactDao().delete(deleteContact);
        }).start();
    }

    public void goToContactsPage() {
        view.goToContactsPage();
    }

    public void handleCallPress(String phoneNumber) {
        view.makeCall(phoneNumber);
    }

    public void handleMessagePress(String phoneNumber) {
        view.sendMessage(phoneNumber);
    }

    public void handleEmailPress(String email) {
        view.sendEmail(email);
    }
}
