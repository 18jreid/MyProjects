package com.example.contacts.presenters;

import com.example.contacts.database.AppDatabase;
import com.example.contacts.models.Contact;

import java.util.ArrayList;

public class MainPresenter {
    private MVPView view;
    private ArrayList<Contact> contacts = new ArrayList<>();
    private AppDatabase database;

    public interface MVPView {
        public void renderContacts(ArrayList<Contact> contacts);
        public AppDatabase getContextDatabase();
        public void goToContactsPage();
        public void goToContactsViewPage(Contact contact);
    }

    public MainPresenter(MVPView view) {
        this.view = view;
        database = view.getContextDatabase();
        refreshContacts();
    }

    /**
     * Sends user to the new Contact page.
     */
    public void goToContactsPage() {
        view.goToContactsPage();
    }

    /**
     * Sends user to the specific Contact view.
     * @param contact: Specific contact wanting to be viewed.
     */
    public void goToContactsViewPage(Contact contact) {
        view.goToContactsViewPage(contact);
    }

    /**
     * Updates the contact into the database.
     * @param contact: Specific contact to be updated.
     */
    public void updateContact(Contact contact) {
        new Thread(() -> {
            database.getContactDao().update(contact);
        }).start();
    }

    /**
     * Refreshes all contacts into the view.
     */
    public void refreshContacts() {
        new Thread(() -> {
            contacts = (ArrayList<Contact>)database.getContactDao().getAllContacts();
            view.renderContacts(contacts);
        }).start();
    }
}
