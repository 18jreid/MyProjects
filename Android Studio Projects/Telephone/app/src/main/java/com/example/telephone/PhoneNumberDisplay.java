package com.example.telephone;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatTextView;

public class PhoneNumberDisplay extends AppCompatTextView {
    public String phoneNumber = "";

    public PhoneNumberDisplay(Context context) {
        super(context);
        setGravity(Gravity.CENTER);
        setPadding(24, 64, 24, 64);
        setTextSize(24);

    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.setText(phoneNumber);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
