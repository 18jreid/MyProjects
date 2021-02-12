package com.example.telephone;

public class PhoneButtonData {
    public String text;
    public int row;
    public int column;
    public int colSpan;
    public ButtonType type;

    public enum ButtonType  {
        INPUT,
        CALL,
        CLEAR,
    }

    public PhoneButtonData(String text, int row, int column, int colSpan, ButtonType type) {
        this.text = text;
        this.row = row;
        this.column = column;
        this.colSpan = colSpan;
        this.type = type;
    }

    public PhoneButtonData(String text, int row, int column, int colSpan) {
        this.text = text;
        this.row = row;
        this.column = column;
        this.colSpan = colSpan;
        this.type = ButtonType.INPUT;
    }
}
