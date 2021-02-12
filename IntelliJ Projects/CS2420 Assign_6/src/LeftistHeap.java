public class LeftistHeap<E extends Comparable<E>>{
    public Node<E> root;

    /**
     * Simple Node class that provides the information needed for the Leftist MaxHeap
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
            npl = 0;
        }

        E element;
        Node<E> left;
        Node<E> right;
        int npl;
    }

    /**
     * Merges two different Leftist MaxHeap trees
     * @param: the node of the current Leftist MaxHeap
     * @param: the node of the Leftist MaxHeap tree to be attached
     * @returns: the new root of the combined Leftist MaxHeap Tree
     */
    private Node<E> merge(Node<E> t1, Node<E> t2) {
        Node<E> big;
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        if (t1.element.compareTo(t2.element) > 0) {
            t1.right = merge(t1.right, t2);
            big = t1;
        }
        else {
            t2.right = merge(t2.right, t1);
            big = t2;
        }
        if (getNpl(big.left) < getNpl(big.right)) swapKids(big);
        setNullPathLength(big);
        return big;
    }

    /**
     * Returns the Null Path Length of a specific node
     * @param: the node you want the null path length of
     * @returns: Null Path Length of node
     */
    private int getNpl(Node<E> t) {
        if (t == null) return -1;
        return t.npl;
    }

    /**
     * Calculates the Null Path Length of a specific node
     * @param: the node you want to determine the Null Path Length of
     */
    private void setNullPathLength(Node<E> t) {
        if (t.left == null || t.right == null) {
            t.npl = 0;
        }
        else {
            t.npl = Math.min(t.left.npl, t.right.npl) + 1;
        }
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
     * Inserts new element into the Leftist MaxHeap
     * @param: the element you want to insert
     */
    public void insert(E theElement) {
        Node<E> newNode = new Node<E>(theElement);

        root = merge(root, newNode);
    }

    /**
     * Removes maximum value from the Leftist MaxHeap
     * @returns: the element removed
     */
    public E removeMax() {
        Node<E> maxValue = root;
        root = merge(root.left, root.right);

        return maxValue.element;
    }

    /**
     * Calculates the size of the Leftist MaxHeap
     * @returns: the size of the Leftist MaxHeap
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
     * Prints the entire Leftist MaxHeap Tree
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
