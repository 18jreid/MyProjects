package com.example.contacts.Components;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.contacts.R;
import com.example.contacts.models.Contact;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textview.MaterialTextView;

public class ContactItem extends MaterialCardView {

    public ContactItem(Context context, Contact contact) {
        super(context);
        // Creates text views for buttons to be viewed.
        LinearLayout contentsView = new LinearLayout(context);
        contentsView.setPadding(32,  32, 32,32);
        MaterialTextView name = new MaterialTextView(context);
        MaterialTextView email = new MaterialTextView(context);
        MaterialTextView phoneNumber = new MaterialTextView(context);

        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(300, 300);
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(imageParams);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if (!contact.imageUri.equals(""))  {
            imageView.setImageURI(Uri.parse(contact.imageUri));
        }
        else {
            imageView.setImageResource(R.drawable.ic_baseline_photo_240);

        }
        addView(contentsView);

        // Sets specific size, padding, and texts.
        LinearLayout.LayoutParams contentParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        contentParams.setMargins(24, 0, 24, 0);

        name.setText(contact.name);
        name.setTextSize(36);
        name.setTextColor(Color.BLACK);
        name.setLayoutParams(contentParams);

        email.setText(contact.email);
        email.setTextSize(15);
        email.setLayoutParams(contentParams);

        phoneNumber.setText(contact.phoneNumber);
        phoneNumber.setTextSize(15);
        phoneNumber.setLayoutParams(contentParams);

        LinearLayout infoLayout = new LinearLayout(context);
        infoLayout.setOrientation(LinearLayout.VERTICAL);

        // Shows name on contacts page
        contentsView.addView(imageView);
        infoLayout.addView(name);
        infoLayout.addView(phoneNumber);
        infoLayout.addView(email);
        contentsView.addView(infoLayout);

    }
}
