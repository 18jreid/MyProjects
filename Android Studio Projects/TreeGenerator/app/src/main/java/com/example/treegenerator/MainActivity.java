package com.example.treegenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout mainLayout = new LinearLayout(this);

        final CustomInputView maxLength = new CustomInputView("Max Length: ", this);
        final CustomInputView minLength = new CustomInputView("Min Length: ", this);

        final CustomInputView maxAngle = new CustomInputView("Max Angle: ", this);
        final CustomInputView minAngle = new CustomInputView("Min Angle: ", this);

        final CustomInputView maxWidth = new CustomInputView("Max Width: ", this);
        final CustomInputView minWidth = new CustomInputView("Min Width: ", this);

        AppCompatButton generateButton = new AppCompatButton(this);
        generateButton.setText("Generate");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        generateButton.setLayoutParams(params);

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GenerationActivity.class);

                if (!maxLength.getText().equals("")) {
                    intent.putExtra("maxLength", maxLength.getText());
                }
                else {
                    intent.putExtra("maxLength", "200");
                }

                if (!minLength.getText().equals("")) {
                    intent.putExtra("minLength", minLength.getText());
                }
                else {
                    intent.putExtra("minLength", "200");
                }

                if (!maxAngle.getText().equals("")) {
                    intent.putExtra("maxAngle", maxAngle.getText());
                }
                else {
                    intent.putExtra("maxAngle", "50");
                }

                if (!minAngle.getText().equals("")) {
                    intent.putExtra("minAngle", minAngle.getText());
                }
                else {
                    intent.putExtra("minAngle", "-50");
                }

                if (!maxWidth.getText().equals("")) {
                    intent.putExtra("maxWidth", maxWidth.getText());
                }
                else {
                    intent.putExtra("maxWidth", "50");
                }

                if (!minWidth.getText().equals("")) {
                    intent.putExtra("minWidth", minWidth.getText());
                }
                else {
                    intent.putExtra("minWidth", "50");
                }

                startActivity(intent);
            }
        });

        AppCompatButton clearButton = new AppCompatButton(this);
        clearButton.setText("Clear");
        clearButton.setLayoutParams(params);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maxLength.setText("");
                minLength.setText("");
                maxAngle.setText("");
                minAngle.setText("");
                maxWidth.setText("");
                minWidth.setText("");
            }
        });


        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.addView(maxLength);
        mainLayout.addView(minLength);
        mainLayout.addView(maxAngle);
        mainLayout.addView(minAngle);
        mainLayout.addView(maxWidth);
        mainLayout.addView(minWidth);
        mainLayout.addView(generateButton);
        mainLayout.addView(clearButton);
        setContentView(mainLayout);
    }
}