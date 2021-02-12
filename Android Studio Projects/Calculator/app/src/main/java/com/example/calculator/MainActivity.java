package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<CalculatorButtonData> calculatorButtonData = createButtonDataArray();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        final GridLayout calculatorButtonsLayout = new GridLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        calculatorButtonsLayout.setLayoutParams(params);
        calculatorButtonsLayout.setColumnCount(4);

        final InputDisplay inputDisplay = new InputDisplay(this);

        for (final CalculatorButtonData data: calculatorButtonData) {
            CalculatorButton button = new CalculatorButton(this, data, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (data.type == CalculatorButtonData.ButtonType.INPUT) {
                        inputDisplay.setExpression(inputDisplay.getExpression() + data.text);
                    }

                    if (data.type == CalculatorButtonData.ButtonType.DIVIDE) {
                        inputDisplay.addSpace();
                        inputDisplay.setExpression(inputDisplay.getExpression() + "/");
                        inputDisplay.addSpace();
                    }

                    if (data.type == CalculatorButtonData.ButtonType.MULTIPLY) {
                        inputDisplay.addSpace();
                        inputDisplay.setExpression(inputDisplay.getExpression() + "*");
                        inputDisplay.addSpace();
                    }

                    if (data.type == CalculatorButtonData.ButtonType.SUBTRACT) {
                        inputDisplay.addSpace();
                        inputDisplay.setExpression(inputDisplay.getExpression() + "-");
                        inputDisplay.addSpace();
                    }

                    if (data.type == CalculatorButtonData.ButtonType.ADD) {
                        inputDisplay.addSpace();
                        inputDisplay.setExpression(inputDisplay.getExpression() + "+");
                        inputDisplay.addSpace();
                    }

                    if (data.type == CalculatorButtonData.ButtonType.CLEAR) {
                        inputDisplay.setExpression("");
                    }

                    if (data.type == CalculatorButtonData.ButtonType.EQUALS) {
                        double output = Calculator.evaluate(inputDisplay.getExpression());
                        inputDisplay.setExpression(Double.toString(output));
                    }
                }
            });

            calculatorButtonsLayout.addView(button);
        }

        LinearLayout displayRow = new LinearLayout(this);
        displayRow.addView(inputDisplay);
        mainLayout.addView(displayRow);
        mainLayout.addView(calculatorButtonsLayout);

        setContentView(mainLayout);
    }

    private ArrayList<CalculatorButtonData> createButtonDataArray() {
        final int primary = Color.BLACK;
        final int primaryDark = Color.DKGRAY;

        return new ArrayList<CalculatorButtonData>() {
            {
                add(new CalculatorButtonData("7", 0, 0, 1, primary, CalculatorButtonData.ButtonType.INPUT));
                add(new CalculatorButtonData("8", 0, 1, 1, primary, CalculatorButtonData.ButtonType.INPUT));
                add(new CalculatorButtonData("9", 0, 2, 1, primary, CalculatorButtonData.ButtonType.INPUT));
                add(new CalculatorButtonData("/", 0, 3, 1, primaryDark, CalculatorButtonData.ButtonType.DIVIDE));

                add(new CalculatorButtonData("4", 1, 0, 1, primary, CalculatorButtonData.ButtonType.INPUT));
                add(new CalculatorButtonData("5", 1, 1, 1, primary, CalculatorButtonData.ButtonType.INPUT));
                add(new CalculatorButtonData("6", 1, 2, 1, primary, CalculatorButtonData.ButtonType.INPUT));
                add(new CalculatorButtonData("*", 1, 3, 1, primaryDark, CalculatorButtonData.ButtonType.MULTIPLY));

                add(new CalculatorButtonData("1", 2, 0, 1, primary, CalculatorButtonData.ButtonType.INPUT));
                add(new CalculatorButtonData("2", 2, 1, 1, primary, CalculatorButtonData.ButtonType.INPUT));
                add(new CalculatorButtonData("3", 2, 2, 1, primary, CalculatorButtonData.ButtonType.INPUT));
                add(new CalculatorButtonData("-", 2, 3, 1, primaryDark, CalculatorButtonData.ButtonType.SUBTRACT));

                add(new CalculatorButtonData("0", 3, 0, 2, primary, CalculatorButtonData.ButtonType.INPUT));
                add(new CalculatorButtonData(".", 3, 2, 1, primary, CalculatorButtonData.ButtonType.INPUT));
                add(new CalculatorButtonData("+", 3, 3, 1, primaryDark, CalculatorButtonData.ButtonType.ADD));

                add(new CalculatorButtonData("C", 4, 0, 1, primaryDark, CalculatorButtonData.ButtonType.CLEAR));
                add(new CalculatorButtonData("=", 4, 1, 3, primaryDark, CalculatorButtonData.ButtonType.EQUALS));
            }
        };
    }
}