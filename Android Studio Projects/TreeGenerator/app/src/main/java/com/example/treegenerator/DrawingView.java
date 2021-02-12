package com.example.treegenerator;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;

import java.util.ArrayList;

public class DrawingView extends View {
    Tree tree;

    public DrawingView(Context context, Tree treeInfo) {
        super(context);
        this.tree = treeInfo;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        Paint paint = new Paint();
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(550, 1300, 200, paint);

        paint.setColor(Color.rgb(0,100,0));
        paint.setStrokeWidth(550);
        canvas.drawLine(0, 1600, 1250, 1600, paint);
        canvas.translate(550, 1450);
        drawTree(tree.root, canvas, 0, 0);
    }

    private void drawTree(TreeNode node, Canvas canvas, int startX, int startY) {
        if (node == null) {return;}
        if (node.depth == 0) {
            node.angle = 0;
        }

        Paint paint = new Paint();
        paint.setStrokeWidth(node.width);
        paint.setColor(Color.rgb(02, 51, 0));

        canvas.rotate(node.angle);
        canvas.save();
        canvas.drawLine(startX, startY, startX, startY - node.length, paint);
        canvas.translate(startX, startY -  node.length);

        drawTree(node.left, canvas, startX, startY);
        canvas.restore();
        canvas.translate(startX, startY -  node.length);
        drawTree(node.right, canvas, startX, startY);
    }
}
