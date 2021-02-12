package com.example.roomexample;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.room.Room;

import com.example.roomexample.database.AppDatabase;
import com.example.roomexample.presenters.NewTodoPresenter;

public class NewTodoActivity extends AppCompatActivity implements NewTodoPresenter.MVPView {
    NewTodoPresenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new NewTodoPresenter(this);

        AppCompatTextView textView = new AppCompatTextView(this);
        textView.setText("Enter todo information: ");
        AppCompatEditText editText = new AppCompatEditText(this);
        AppCompatButton button = new AppCompatButton(this);
        button.setText("Save");

        button.setOnClickListener(view -> {
            presenter.createTodo(editText.getText().toString());
        });

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.addView(textView);
        mainLayout.addView(editText);
        mainLayout.addView(button);
        setContentView(mainLayout);
    }

    @Override
    public AppDatabase getContextDatabase() {
        return Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "todos-database").build();
    }

    @Override
    public void goBackToTodosPage() {
        finish();
    }
}
