package com.example.treegenerator;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

public class CustomInputView extends LinearLayout {
    private AppCompatTextView header;
    private AppCompatEditText editText;

    public CustomInputView(String headerText, Context context) {
        super(context);

        AppCompatTextView header = new AppCompatTextView(context);
        header.setText(headerText);
        header.setTextColor(Color.BLACK);

        editText = new AppCompatEditText(context);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        setLayoutParams(params);
        setOrientation(VERTICAL);
        addView(header);
        addView(editText);
    }

    public String getText() {
        return editText.getText().toString();
    }

    public void setText(String string) {
        editText.setText(string);
    }
}
