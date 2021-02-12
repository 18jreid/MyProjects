public class Tree<E> {
    protected TreeNode<E> root;

    public Tree() {
        this.root = new TreeNode<E>();
    }

    public Tree(E root) {
        this.root = new TreeNode<E>(root);
    }

    public Tree(E[] objectsArray) {
        this.root = new TreeNode<E>(objectsArray[0]);

        for (int i = 1; i < objectsArray.length; i++) {
            insert(objectsArray[i]);
        }
    }

    public void printTree() {
        printTree(this.root);
    }

    private void printTree(TreeNode<E> node) {
        if (node.element != null) {
            System.out.print(node.element + " ");

            if (node.leftSubTree != null) {
                printTree(node.leftSubTree);
            }

            if (node.rightSubTree != null) {
                printTree(node.rightSubTree);
            }
        }
        else {
            System.out.println("The tree is empty.");
        }
    }

    public boolean insert(E data) {
        return insert(data, root);
    }

    private boolean insert(E data, TreeNode<E> root) {
        if (root.leftSubTree == null) {
            root.leftSubTree = new TreeNode<E>(data);
            return true;
        }

        else if (root.rightSubTree == null) {
            root.rightSubTree = new TreeNode<E>(data);
            return true;
        }

        else {
            insert(data, root.leftSubTree);
            return true;
        }
    }
}
