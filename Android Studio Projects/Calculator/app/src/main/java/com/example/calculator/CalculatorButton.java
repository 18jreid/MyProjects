package com.example.calculator;

import android.content.Context;
import android.graphics.Color;
import android.widget.GridLayout;

import androidx.appcompat.widget.AppCompatButton;

public class CalculatorButton extends AppCompatButton {
    public CalculatorButton(Context context, CalculatorButtonData data, OnClickListener onClickListener) {
        super(context);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.rowSpec = GridLayout.spec(data.row, 1, 1);
        params.columnSpec = GridLayout.spec(data.col, data.colSpan, 1);
        params.setMargins(7, 7,7, 7);
        setLayoutParams(params);
        setText(data.text);
        setOnClickListener(onClickListener);

        setBackgroundColor(data.color);
        setTextColor(Color.WHITE);


        setTextSize(24);
    }
}
