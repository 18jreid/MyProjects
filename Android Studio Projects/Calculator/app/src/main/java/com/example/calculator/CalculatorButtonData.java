package com.example.calculator;

import android.graphics.Color;

public class CalculatorButtonData {
    public String text;
    public int row;
    public int col;
    public int colSpan;
    public ButtonType type;
    public int color = Color.BLACK;

    public enum ButtonType  {
        INPUT,
        EQUALS,
        CLEAR,
        DIVIDE,
        MULTIPLY,
        SUBTRACT,
        ADD
    }

    public CalculatorButtonData(String text, int row, int col, int colSpan, int color) {
        this.text = text;
        this.row = row;
        this.col = col;
        this.colSpan = colSpan;
    }

    public CalculatorButtonData(String text, int row, int col, int colSpan, int color, ButtonType buttonType) {
        this.text = text;
        this.row = row;
        this.col = col;
        this.colSpan = colSpan;
        this.type = buttonType;
        setColor(color);
    }

    private void setColor(int color) {
        this.color = color;
    }
}
