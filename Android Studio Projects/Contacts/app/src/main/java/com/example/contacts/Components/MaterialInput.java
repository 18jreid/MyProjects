package com.example.contacts.Components;

import android.content.Context;
import android.text.Editable;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MaterialInput extends TextInputLayout {
    private TextInputEditText editText;
    public MaterialInput(Context context, String hint) {
        super(context);
        setHint(hint);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(48, 24, 48, 24);
        editText = new TextInputEditText(getContext());
        addView(editText);
    }

    public Editable getText() { return editText.getText(); }

    public void setText(String text) {
        this.editText.setText(text);
    }
}