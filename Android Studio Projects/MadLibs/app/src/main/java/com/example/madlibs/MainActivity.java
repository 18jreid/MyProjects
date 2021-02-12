package com.example.madlibs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        // Name editText
        AppCompatTextView nameTextView = new AppCompatTextView(this);
        final AppCompatEditText nameEditText = new AppCompatEditText(this);

        nameTextView.setText("Enter a name");
        nameEditText.setLayoutParams(params);

        layout.addView(nameTextView);
        layout.addView(nameEditText);

        // Adjective editText
        AppCompatTextView adjectiveText = new AppCompatTextView(this);
        final AppCompatEditText adjectiveEditText = new AppCompatEditText(this);

        adjectiveText.setText("Enter a adjective");
        adjectiveEditText.setLayoutParams(params);

        layout.addView(adjectiveText);
        layout.addView(adjectiveEditText);

        // Occupation editText
        AppCompatTextView occupationText = new AppCompatTextView(this);
        final AppCompatEditText occupationEditText = new AppCompatEditText(this);

        occupationText.setText("Enter a occupation");
        occupationEditText.setLayoutParams(params);

        layout.addView(occupationText);
        layout.addView(occupationEditText);

        // Place editText
        AppCompatTextView placeText = new AppCompatTextView(this);
        final AppCompatEditText placeEditText = new AppCompatEditText(this);

        placeText.setText("Enter a place");
        placeEditText.setLayoutParams(params);

        layout.addView(placeText);
        layout.addView(placeEditText);

        // Second name editText
        AppCompatTextView secNameText = new AppCompatTextView(this);
        final AppCompatEditText secNameEditText = new AppCompatEditText(this);

        secNameText.setText("Enter a name");
        secNameEditText.setLayoutParams(params);

        layout.addView(secNameText);
        layout.addView(secNameEditText);

        // Place second editText
        AppCompatTextView secPlaceText = new AppCompatTextView(this);
        final AppCompatEditText secPlaceEditText = new AppCompatEditText(this);

        secPlaceText.setText("Enter a place");
        secPlaceEditText.setLayoutParams(params);

        layout.addView(secPlaceText);
        layout.addView(secPlaceEditText);

        // Thing editText
        AppCompatTextView thingText = new AppCompatTextView(this);
        final AppCompatEditText thingEditText = new AppCompatEditText(this);

        thingText.setText("Enter a thing");
        thingEditText.setLayoutParams(params);

        layout.addView(thingText);
        layout.addView(thingEditText);

        layout.setOrientation(LinearLayout.VERTICAL);
        setContentView(layout);

        // MadLib text
        final AppCompatTextView madLibTextView = new AppCompatTextView(this);
        madLibTextView.setTextColor(Color.BLACK);
        madLibTextView.setTextSize(1,22);

        // Generate MadLib button
        AppCompatButton button = new AppCompatButton(this);
        button.setText("GENERATE MAD LIB");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstName = nameEditText.getText().toString();
                String adjective = adjectiveEditText.getText().toString();
                String occupation = occupationEditText.getText().toString();
                String firstPlace = placeEditText.getText().toString();
                String secName = secNameEditText.getText().toString();
                String secPlace = secPlaceEditText.getText().toString();
                String thing = thingEditText.getText().toString();

                madLibTextView.setText(firstName + " is a " + occupation + ". He/She is very " + adjective + " and loves " + thing + ". " +
                        firstName + " is best friends with " + secName + ". They both live in " + firstPlace + " and love" +
                        " to eat at " + secPlace + "!");
            }
        });

        layout.addView(button);
        layout.addView(madLibTextView);
    }
}