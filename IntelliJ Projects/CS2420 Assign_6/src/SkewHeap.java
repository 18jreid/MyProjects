public class SkewHeap<E extends Comparable<E>> {
    public Node<E> root;

    /**
     * Simple Node class that provides the information needed for the Skew MinHeap
     * @param: the element of the node.
     */
    private static class Node<E> {
        Node(E theElement) {
            this.element = theElement;
            this.left = null;
            this.right = null;
        }
        Node(E theElement, Node<E> lt, Node<E> rt) {
            this.element = theElement;
            this.left = lt;
            this.right = rt;
        }

        E element;
        Node<E> left;
        Node<E> right;
    }

    /**
     * Merges two different Skew MinHeap trees
     * @param: the node of the current Skew MinHeap
     * @param: the node of the Skew MinHeap tree to be attached
     * @returns: the new root of the combined Skew MinHeap Tree
     */
    private Node<E> merge(Node<E> t1, Node<E> t2) {
        Node<E> small;
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        if (t1.element.compareTo(t2.element) < 0) {
            t1.right = merge(t1.right, t2);
            small = t1;
        }
        else {
            t2.right = merge(t2.right, t1);
            small = t2;
        }

        swapKids(small);
        return small;
    }

    /**
     * Swaps children nodes of a specific node
     * @param: the parent node of the children you want swa[[ed
     */
    private void swapKids(Node<E> node) {
        Node<E> tmp = node.right;
        node.right = node.left;
        node.left = tmp;
    }

    /**
     * Inserts new element into the Skew MinHeap
     * @param: the element you want to insert
     */
    public void insert(E theElement) {
        Node<E> newNode = new Node<E>(theElement);

        root = merge(root, newNode);
    }

    /**
     * Removes minimum value from the Skew MinHeap
     * @returns: the element removed
     */
    public E removeMin() {
        Node<E> minValue = root;
        root = merge(root.left, root.right);

        return minValue.element;
    }

    /**
     * Calculates the size of the Skew MinHeap
     * @returns: the size of the Skew MinHeap
     */
    public int sizeOf() {
        return sizeOf(root);
    }
    private int sizeOf(Node<E> node)
    {
        if (node == null)
            return 0;
        else
            return(sizeOf(node.left) + 1 + sizeOf(node.right));
    }

    /**
     * Prints the entire Skew MinHeap Tree
     */
    public void printTree() {
        printTree(root, "");
    }
    private void printTree(Node<E> t, String indent) {
        if (t != null) {
            printTree(t.right, indent + "     ");
            System.out.println(indent + "<:" + t.element);
            printTree(t.left, indent + "     ");
        }
    }
}
