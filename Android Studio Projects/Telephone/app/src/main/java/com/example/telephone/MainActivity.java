package com.example.telephone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<PhoneButtonData> phoneButtonData = new ArrayList<PhoneButtonData>() {
        {
            add(new PhoneButtonData("1", 0, 0, 1));
            add(new PhoneButtonData("2", 0, 1, 1));
            add(new PhoneButtonData("3", 0, 2, 1));
            add(new PhoneButtonData("4", 1, 0, 1));
            add(new PhoneButtonData("5", 1, 1, 1));
            add(new PhoneButtonData("6", 1, 2, 1));
            add(new PhoneButtonData("7", 2, 0, 1));
            add(new PhoneButtonData("8", 2, 1, 1));
            add(new PhoneButtonData("9", 2, 2, 1));
            add(new PhoneButtonData("*", 3, 0, 1));
            add(new PhoneButtonData("0", 3, 1, 1));
            add(new PhoneButtonData("#", 3, 2, 1));
            add(new PhoneButtonData("CALL", 4, 0, 2, PhoneButtonData.ButtonType.CALL));
            add(new PhoneButtonData("CLEAR", 4, 2, 1, PhoneButtonData.ButtonType.CLEAR));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        final GridLayout phoneButtonsLayout = new GridLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        phoneButtonsLayout.setLayoutParams(params);
        phoneButtonsLayout.setColumnCount(3);

        final PhoneNumberDisplay phoneNumberDisplay = new PhoneNumberDisplay(this);

        for (final PhoneButtonData data: phoneButtonData) {
            PhoneButton button = new PhoneButton(this, data, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (data.type == PhoneButtonData.ButtonType.CALL) {
                        // make the phone call
                        Intent phoneIntent = new Intent(Intent.ACTION_CALL);
                        phoneIntent.setData(Uri.parse("tel:" + phoneNumberDisplay.getPhoneNumber()));
                        startActivity(phoneIntent);
                    }
                    else if (data.type  == PhoneButtonData.ButtonType.CLEAR) {
                        phoneNumberDisplay.setPhoneNumber("");
                    }
                    else {
                        phoneNumberDisplay.setPhoneNumber(phoneNumberDisplay.getPhoneNumber() + data.text);
                    }
                }
            });
            phoneButtonsLayout.addView(button);
        }

        mainLayout.addView(phoneNumberDisplay);
        mainLayout.addView(phoneButtonsLayout);

        setContentView(mainLayout);
    }
}