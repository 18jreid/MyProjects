package com.example.calculator;

import android.content.Context;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatTextView;

public class InputDisplay extends AppCompatTextView {
    public String expression = "";

    public InputDisplay(Context context) {
        super(context);
        setGravity(Gravity.CENTER);
        setPadding(24, 64, 24, 64);
        setTextSize(24);
    }

    public void setExpression(String expression) {
        this.expression = expression;
        this.setText(expression);
    }

    public String getExpression() {
        return expression;
    }

    public void addSpace() {
        setExpression(this.expression + " ");
    }
}
