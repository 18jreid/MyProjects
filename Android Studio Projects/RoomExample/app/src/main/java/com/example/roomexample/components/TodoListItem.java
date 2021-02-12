package com.example.roomexample.components;

import android.content.Context;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.roomexample.models.Todo;

public class TodoListItem extends LinearLayout {
    public interface OnCheckChange {
        void call();
    }

    public TodoListItem(Context context, Todo todo, OnCheckChange onChange) {
        super(context);
        AppCompatCheckBox checkBox = new AppCompatCheckBox(context);
        checkBox.setChecked(todo.isComplete);
        checkBox.setOnCheckedChangeListener((view, newValue) -> {
            todo.isComplete = newValue;
            onChange.call();
        });

        AppCompatTextView textView = new AppCompatTextView(context);
        textView.setText(todo.contents);

        addView(checkBox);
        addView(textView);
    }
}
