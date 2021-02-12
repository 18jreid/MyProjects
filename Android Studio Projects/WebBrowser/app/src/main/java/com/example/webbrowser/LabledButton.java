package com.example.webbrowser;

import android.content.Context;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatButton;

public class LabledButton extends LinearLayout {
    AppCompatButton button;

    public LabledButton(String label, int size, Context context) {
        super(context);
        button = new AppCompatButton(context);
        button.setText(label);
        addView(button);

        LayoutParams params = new LayoutParams(size, size);
        button.setLayoutParams(params);
    }
}
