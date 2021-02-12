package com.example.treegenerator;

public class Tree {
    TreeNode root;

    int maxLength = 200;
    int minLength = 200;
    int maxWidth = 50;
    int minWidth = 50;
    int maxAngle = -50;
    int minAngle = 50;

    int width;

    public Tree(int maxLength, int minLength, int maxWidth, int minWidth, int maxAngle, int minAngle) {
        this.maxLength = maxLength;
        this.minLength = minLength;
        this.maxWidth = maxWidth;
        this.minWidth = minWidth;
        this.maxAngle = maxAngle;
        this.minAngle = minAngle;
        this.width = (int) ((Math.random() * (maxWidth - minWidth)) + minWidth);

        generate(6);
    }

    public void generate(int maxDepth) {
        root = new TreeNode(maxLength, minLength, width, maxAngle, minAngle, 0);
        generate(root, 0, maxDepth, width);
    }

    private void generate(TreeNode node, int currDepth, int maxDepth, int width) {
        if (currDepth >= maxDepth) {return;}
        node.left = new TreeNode(maxLength, minLength, width, maxAngle, minAngle, currDepth + 1);
        node.left.parent = node;
        node.right = new TreeNode(maxLength, minLength, width, maxAngle, minAngle, currDepth + 1);
        node.left.parent = node;

        generate(node.left, currDepth + 1, maxDepth, width - (width / 3));
        generate(node.right, currDepth + 1, maxDepth, width - (width / 3));
    }

    public void print() {
        print(root, 0);
    }

    private void print(TreeNode node, int depth) {
        if (node == null) {return;}
        StringBuilder spaces = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            spaces.append("     ");
        }

        print(node.left, depth + 1);
        System.out.println(spaces + "Length: " + node.length + ", Width: " + node.width + ", Angle: " + node.angle + "|  " + node.depth);
        print(node.right, depth +  1);
    }
}
