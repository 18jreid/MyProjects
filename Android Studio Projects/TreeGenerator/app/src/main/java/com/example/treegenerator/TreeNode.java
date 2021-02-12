package com.example.treegenerator;

public class TreeNode {
    TreeNode left = null;
    TreeNode right = null;
    TreeNode parent = null;

    int depth;
    int length;
    int width;
    int angle;

    public TreeNode(int maxLength, int minLength, int width, int maxAngle, int minAngle, int depth) {
        this.length = getRandomLength(minLength, maxLength);
        this.width = width;
        this.angle = getRandomAngle(minAngle, maxAngle);
        this.depth = depth;
    }

    private int getRandomLength(int minLength, int maxLength) {
        return (int) ((Math.random() * (maxLength - minLength)) + minLength);
    }

    private int getRandomWidth(int minWidth, int maxWidth) {
        return (int) ((Math.random() * (maxWidth - minWidth)) + minWidth);
    }

    private int getRandomAngle(int minAngle, int maxAngle) {
        return (int) ((Math.random() * (maxAngle - minAngle)) + minAngle);
    }
}