package com.example.treegenerator;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import java.util.ArrayList;

public class GenerationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        String maxLengthString = intent.getStringExtra("maxLength");
        String minLengthString = intent.getStringExtra("minLength");
        String maxAngleString = intent.getStringExtra("maxAngle");
        String minAngleString = intent.getStringExtra("minAngle");
        String maxWidthString = intent.getStringExtra("maxWidth");
        String minWidthString = intent.getStringExtra("minWidth");

        assert maxLengthString != null;
        int maxLength = Integer.parseInt(maxLengthString);
        assert minLengthString != null;
        int minLength = Integer.parseInt(minLengthString);
        assert maxAngleString != null;
        int maxAngle = Integer.parseInt(maxAngleString);
        assert minAngleString != null;
        int minAngle = Integer.parseInt(minAngleString);
        assert maxWidthString != null;
        int maxWidth = Integer.parseInt(maxWidthString);
        assert minWidthString != null;
        int minWidth = Integer.parseInt(minWidthString);

        Tree tree = new Tree(maxLength, minLength, maxWidth, minWidth, maxAngle, minAngle);
        tree.print();

        DrawingView drawingView = new DrawingView(this, tree);
        drawingView.setBackgroundResource(R.drawable.bg);

        setContentView(drawingView);
    }

    private void generateList(TreeNode node, ArrayList<TreeNode> list) {
        if (node == null) {return;}
        generateList(node.left, list);
        list.add(node.left);
        generateList(node.right, list);
    }
}
