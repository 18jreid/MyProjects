// ******************ERRORS********************************
// Throws UnderflowException as appropriate

import javax.swing.tree.TreeNode;
import java.util.*;

class UnderflowException extends RuntimeException {
    /**
     * Construct this exception object.
     *
     * @param message the error message.
     */
    public UnderflowException(String message) {
        super(message);
    }
}

public class Tree<E extends Comparable<? super E>> {
    final String ENDLINE = "\n";
    private BinaryNode<E> root;  // Root of tree
    private BinaryNode<E> curr;  // Last node accessed in tree
    private String treeName;     // Name of tree

    /**
     * Create an empty tree
     *
     * @param label Name of tree
     */
    public Tree(String label) {
        treeName = label;
        root = null;
    }

    /**
     * Create BST from Array
     *
     * @param arr   List of elements to be added
     * @param label Name of  tree
     */
    public Tree(E[] arr, String label) {
        root = null;
        treeName = label;
        for (int i = 0; i < arr.length; i++) {
            bstInsert(arr[i]);
        }
    }

    public static void getLeaves(Integer[] preorder, int beg, int end, ArrayList<Integer> leaves) {
    }



    /**
     * Create Tree By Level.  Parents are set.
     * This runs in  O(n)
     * @param arr   List of elements to be added
     * @param label Name of tree
     */
    public void createTreeByLevel(E[] arr, String label) {
        treeName = label;
        if (arr.length <= 0) {
            root = null;
            return;
        }

        ArrayList<BinaryNode<E>> nodes = new ArrayList<BinaryNode<E>>();
        root = new BinaryNode<E>(arr[0]);
        nodes.add(root);
        BinaryNode<E> newr = null;
        for (int i = 1; i < arr.length; i += 2) {
            BinaryNode<E> curr = nodes.remove(0);
            BinaryNode<E> newl = new BinaryNode<E>(arr[i], null, null, curr);
            nodes.add(newl);
            newr = null;
            if (i + 1 < arr.length) {
                newr = new BinaryNode<E>(arr[i + 1], null, null, curr);
                nodes.add(newr);
            }

            curr.left = newl;
            curr.right = newr;
        }
    }

    /**
     * Change name of tree
     *
     * @param name new name of tree
     */
    public void changeName(String name) {
        this.treeName = name;
    }

    /**
     * Return a string displaying the tree contents as a tree with one node per line
     */
    public String toString() {
        if (root == null)
            return (treeName + " Empty tree\n");
        else
            return toString(root);
    }

    // O(n)
    private String toString(BinaryNode<E> root) {
        StringBuilder indent = new StringBuilder();

        for (int i = 0; i < findLevel(root,  0); i++) {
            indent.append("        ");
        }

        if (root.right != null) {
            toString(root.right);
        }

        System.out.print(indent +  "<|");
        System.out.print(root.element);

        if (root.parent == null) {
            System.out.println("[ROOT]");
        }
        else {
            System.out.println("[" + root.parent.element + "]");
        }

        if (root.left != null) {
            toString(root.left);
        }
        return "\n-------------------------------------------\n" + toString2();
    }

    private int findLevel(BinaryNode<E> root, int count) {
        if (root.parent != null) {
            return findLevel(root.parent, count + 1);
        }
        else {
            return count;
        }
    }

    /**
     * This runs in  O(n)
     * Return a string displaying the tree contents as a single line
     */
    public String toString2() {
        if (root == null)
            return treeName + " Empty tree";
        else
            return treeName + " " + toString2(root);
    }

    /**
     * Find successor of "curr" node in tree
     *
     * @return String representation of the successor
     */
    // log(n)
    public String successor() {
        if (curr == null) curr = root;
        curr = successor(curr);
        if (curr == null) return "null";
        else return curr.toString();
    }

    private BinaryNode<E> successor(BinaryNode<E> node) {
        if (node.right != null) {
            return findMin(node.right);
        }

        else {
            return findMax(node, node.element);
        }
    }

    private BinaryNode<E> findMax(BinaryNode<E> node, E data) {
        if (node.parent.element.compareTo(data) > 0) {
            return node.parent;
        }
        else {
            return findMax(node.parent, data);
        }
    }

    private BinaryNode<E> findMin(BinaryNode<E> node) {
        if (node.left != null) {
            return findMin(node.left);
        }

        else {
            return node;
        }
    }

    /**
     * Print all paths from root to leaves
     */
    public void printAllPaths() {
        printAllPaths(root, "");
    }

    // O(n)
    private void printAllPaths(BinaryNode<E> root, String path) {
        path += root.element.toString() + " ";
        if (root.left != null) {
            printAllPaths(root.left, path);
        }

        if (root.right != null) {
            printAllPaths(root.right, path);
        }

        if (root.left == null && root.right == null) {
            System.out.println(path);
        }
    }

    /**
     * Counts all non-null binary search trees embedded in tree
     *
     * @return Count of embedded binary search trees
     */
    public Integer countBST() {
        return countBST(root, 0);
    }

    // O(n)
    private Integer countBST(BinaryNode<E> node, int count) {
        if (node.left != null) {
            if (isBST(node.left)) {
                count += 1;
            }
            count = countBST(node.left, count);
        }

        if (node.right != null) {
            if (isBST(node.right)) {
                count += 1;
            }
            count = countBST(node.right, count);
        }

        return count;
    }

    private boolean isBST(BinaryNode<E> node) {
        if (node == null) {
            return false;
        }

        int left = findHeight(node.left);
        int right = findHeight(node.right);
        int val = Math.abs(left - right);

        return val <= 1;
    }

    private int findHeight(BinaryNode<E> node) {
        if (node == null) {
            return -1;
        }

        int left = 1 + findHeight(node.left);
        int right = 1 + findHeight(node.right);

        return Math.max(left, right);
    }

    /**
     * Insert into a bst tree; duplicates are allowed
     *
     * @param x the item to insert.
     */
    public void bstInsert(E x) {

        root = bstInsert(x, root, null);
    }

    /**
     * Determines if item is in tree
     *
     * @param item the item to search for.
     * @return true if found.
     */
    public boolean contains(E item) {

        return bstContains(item, root);
    }

    /**
     * Remove all paths from tree that sum to less than given value
     *
     * @param sum: minimum path sum allowed in final tree
     */
    public void pruneK(Integer sum) {
        pruneK(root, sum, 0);
    }

    // O(logn)
    private void pruneK(BinaryNode<E> node, Integer sum, int count) {
        String dataStr  = node.element.toString();
        int data = Integer.parseInt(dataStr);

        count += data;

        if (node.left != null) {
            pruneK(node.left, sum, count);
        }

        if (node.right != null) {
            pruneK(node.right, sum, count);
        }

        if (node.left == null && node.right == null) {
            if (sum > count) {
                if (node.parent.left == node) {
                    node.parent.left = null;
                }

                if (node.parent.right == node) {
                    node.parent.right = null;
                }
            }
        }
    }

    /**
     * Find the least common ancestor of two nodes
     *
     * @param a first node
     * @param b second node
     * @return String representation of ancestor
     */
    public String lca(E a, E b) {
        return lca(findNode(a, root), findNode(b, root));
    }

    // O(logn)
    private String lca(BinaryNode<E> a, BinaryNode<E> b) {
        int aLevel = findLevel(a, 0);
        int bLevel = findLevel(b, 0);

        if (aLevel == bLevel) {
            if (a.element == b.element) {
                return a.element.toString();
            }

            else {
                return lca(a.parent, b.parent);
            }
        }

        if (aLevel > bLevel) {
            return lca(a.parent, b);
        }

        else {
            return lca(a, b.parent);
        }
    }

    public BinaryNode<E> findNode(E data, BinaryNode<E> node) {
        if (data == node.element) {
            return node;
        }

        if (data.compareTo(node.element) < 0){
            return findNode(data, node.left);
        }

        else {return findNode(data, node.right);}
    }

    /**
     * Balance the tree
     */
    public void balanceTree() {
        root = balanceTree(root);
    }

    // O(logn)
    private BinaryNode<E> balanceTree(BinaryNode<E> root) {
        if (findHeight(root.left) > findHeight(root.right)) {
            BinaryNode<E> newRoot = root.left;
            root.left = null;
            root.parent = newRoot;

            newRoot.parent = null;
            newRoot.right = root;

            return newRoot;
        }

        else {
            return root.right;
        }
    }

    /**
     * In a BST, keep only nodes between range
     *
     * @param a lowest value
     * @param b highest value
     */
    public void keepRange(E a, E b) {
        keepRange(a, b, root);
        fixParents(root);
    }

    // O(logn)
    private BinaryNode<E> keepRange(E low, E high, BinaryNode<E> node) {
        if (node == null) {
            return null;
        }

        node.left = keepRange(low, high, node.left);
        node.right = keepRange(low, high, node.right);

        if (node.element.compareTo(low) < 0) {
            return node.right;
        }
        else if (node.element.compareTo(high) > 0) {
            return node.left;
        }

        return node;
    }

    private void fixParents(BinaryNode<E> node) {
        if (node.left != null) {
            fixParents(node.left);
            if (node.left.parent != node) {
                node.left.parent = node;
            }
        }
        if (node.right != null) {
            fixParents(node.right);
            if (node.right.parent != node) {
                node.right.parent = node;
            }
        }
    }

    /**
     * @return for the level with maximum sum, print the sum of the nodes
     */
    // O(n)
    public int maxLevelSum() {
        ArrayList<BinaryNode<E>> myList = levelOrderTraversal(root);
        ArrayList<Integer> sumList = new ArrayList<>();

        int height = findHeight(root);
        for (int i = 0; i <= height; i++) {
            int sum = 0;
            for (BinaryNode<E> node : myList) {
                if (findLevel(node, 0) == i) {
                    int num = Integer.parseInt(node.element.toString());
                    sum = sum + num;
                }
            }
            sumList.add(sum);
        }

        return findMaxFromList(sumList);
    }

    private int findMaxFromList(ArrayList<Integer> list) {
        int max = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > max) {
                max = list.get(i);
            }
        }

        return max;
    }

    // O(n)
    private ArrayList<BinaryNode<E>> levelOrderTraversal(BinaryNode<E> root) {
        if (root == null) {
            return null;
        }

        ArrayList<BinaryNode<E>> list = new ArrayList<>();

        Queue<BinaryNode<E>> queue = new LinkedList<>();

        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryNode<E> current = queue.peek();
            list.add(current);
            if (current.left != null) {queue.add(current.left);}
            if (current.right != null) {queue.add(current.right);}
            queue.poll();
        }

        return list;
    }

    /**
     * @return the sum of the maximum path between any two leaves
     */
    public Integer maxPath() {
        return -999;
    }

    /**
     * @param preorder traversal of a BST,
     *                 print the leaves (without creating the tree)
     */
    public void printLeaves(E[] preorder) {
    }

    /**
     * @return true if the tree is a Sum Tree (every non-leaf node is sum of nodes in subtree)
     */
    public boolean isSum() {
        return false;

    }

    /**
     * @return largest path value for any path between two leaves
     */
    public int maxPathSum() {
        //return maxPathSum(root);
        return 0;
    }

    //PRIVATE

    /**
     * @param preorderT  preorder traversal of tree
     * @param postorderT postorder traversal of tree
     * @param name       of tree
     *                   create a full tree (every node has 0 or 2 children) from its traversals.  This is not a BST.
     */

    public void createTreeTraversals(E[] preorderT, E[] postorderT, String name) {
        this.treeName = "Need to Write ";

    }


    /**
     * Internal method to insert into a subtree.
     * In tree is balanced, this routine runs in O(log n)
     *
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<E> bstInsert(E x, BinaryNode<E> t, BinaryNode<E> parent) {
        if (t == null)
            return new BinaryNode<>(x, null, null, parent);

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0) {
            t.left = bstInsert(x, t.left, t);
        } else {
            t.right = bstInsert(x, t.right, t);
        }

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * This routine runs in O(log n) as there is only one recursive call that is executed and the work
     * associated with a single call is independent of the size of the tree: a=1, b=2, k=0
     *
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     *          SIDE EFFECT: Sets local variable curr to be the node that is found
     * @return node containing the matched item.
     */
    private boolean bstContains(E x, BinaryNode<E> t) {
        curr = null;
        if (t == null)
            return false;

        int compareResult = x.compareTo(t.element);

        if (compareResult < 0)
            return bstContains(x, t.left);
        else if (compareResult > 0)
            return bstContains(x, t.right);
        else {
            curr = t;
            return true;    // Match
        }
    }

    /**
     * Internal method to return a string of items in the tree in order
     * This routine runs in O(??)
     *
     * @param t the node that roots the subtree.
     */
    private String toString2(BinaryNode<E> t) {
        if (t == null) return "";
        StringBuilder sb = new StringBuilder();
        sb.append(toString2(t.left));
        sb.append(t.element.toString() + " ");
        sb.append(toString2(t.right));
        return sb.toString();
    }

    /**
     * Internal method to return a string of items in the tree in order
     * This routine runs in O(??)
     *
     * @param t the node that roots the subtree.
     */
    private String toString(BinaryNode<E> t, String indent) {
        return "Need print pretty";
    }

    private Integer isSum(BinaryNode<E> curr) {
        return -1;
    }

    private static class BinaryNode<AnyType> {
        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
        BinaryNode<AnyType> parent; //  Parent node

        // Constructors
        BinaryNode(AnyType theElement) {
            this(theElement, null, null, null);
        }

        BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt, BinaryNode<AnyType> pt) {
            element = theElement;
            left = lt;
            right = rt;
            parent = pt;
        }


        public String toString() {
            StringBuilder sb = new StringBuilder();
            //sb.append("Node:");
            sb.append(element);
            if (parent == null) {
                sb.append("[]");
            } else {
                sb.append("[");
                sb.append(parent.element);
                sb.append("]");
            }

            return sb.toString();
        }

    }

    // Test program
    public static void main(String[] args) {

        final String ENDLINE = "\n";


        // Assignment Problem 1
        Integer[] v1 = {25, 10, 60, 55, 58, 56, 14, 63, 8, 50, 6, 9, 61};
        System.out.println("-------------------------------------------" +
                "\n              PROBLEM 1");
        Tree<Integer> tree1 = new Tree<Integer>(v1, "Tree1:");
        System.out.println("-------------------------------------------");
        System.out.println(tree1.toString());
        System.out.println("-------------------------------------------\n");


        // Assignment Problem 2
        long seed = 436543;
        Random generator = new Random(seed);  // Don't use a seed if you want the numbers to be different each time
        int val = 60;
        final int SIZE = 8;

        List<Integer> v2 = new ArrayList<Integer>();
        for (int i = 0; i < SIZE * 2; i++) {
            int t = generator.nextInt(200);
            v2.add(t);
        }
        v2.add(val);
        Integer[] v = v2.toArray(new Integer[v2.size()]);
        Tree<Integer> tree2 = new Tree<Integer>(v, "Tree2");
        System.out.println("-------------------------------------------" +
                "\n             PROBLEM 2");
        System.out.println("-------------------------------------------\n");
        System.out.println(tree2.toString());
        tree2.contains(val);  //Sets the current node inside the tree class.
        int succCount = 5;  // how many successors do you want to see?
        System.out.println("In Tree2, starting at " + val + ENDLINE);
        for (int i = 0; i < succCount; i++) {
            System.out.println("The next successor is " + tree2.successor());
        }

        System.out.println("-------------------------------------------\n");

        // Assignment Problem 3
        System.out.println("-------------------------------------------" +
                "\n             PROBLEM 3");
        System.out.println("-------------------------------------------\n");
        System.out.println(tree1.toString());
        System.out.println("All paths from tree1");
        tree1.printAllPaths();
        System.out.println("-------------------------------------------\n");


        // Assignment Problem 4
        Integer[] v4 = {66, 75, -15, 3, 65, -83, 83, -10, 16, -7, 70, 200, 71, 90};
        Tree<Integer> treeA = new Tree<Integer>("TreeA");
        treeA.createTreeByLevel(v4, "TreeA");
        System.out.println("-------------------------------------------" +
                "\n             PROBLEM 4");
        System.out.println("-------------------------------------------\n");
        System.out.println(treeA.toString());
        System.out.println("treeA Contains BST: " + treeA.countBST());
        System.out.println("-------------------------------------------\n");

        Integer[] a = {21, 8, 5, 6, 7, 19, 10, 40, 43, 52, 12, 60};
        Tree<Integer> treeB = new Tree<Integer>("TreeB");
        treeB.createTreeByLevel(a, "TreeB");
        System.out.println(treeB.toString());
        System.out.println("treeB Contains BST: " + treeB.countBST());
        System.out.println("-------------------------------------------\n");

        // Assignment Problem 5

        System.out.println("-------------------------------------------" +
                "\n             PROBLEM 5");
        System.out.println("-------------------------------------------\n");
        treeB.pruneK(60);
        treeB.changeName("treeB after pruning 60:");
        System.out.println(treeB.toString());
        System.out.println("-------------------------------------------\n");
        treeA.pruneK(200);
        treeA.changeName("treeA after pruning 200:");
        System.out.println(treeA.toString());
        System.out.println("-------------------------------------------\n");

        // Assignment Problem 6

        System.out.println("-------------------------------------------" +
                "\n             PROBLEM 6");
        System.out.println("-------------------------------------------\n");
        System.out.println(tree1.toString());
        System.out.println("tree1 Least Common Ancestor of (56,61) " + tree1.lca(56, 61) + ENDLINE);
        System.out.println("-------------------------------------------\n");
        System.out.println(tree1.toString());
        System.out.println("tree1 Least Common Ancestor of (6,25) " + tree1.lca(6, 25) + ENDLINE);
        System.out.println("-------------------------------------------\n");

        // Assignment Problem 7
        Integer[] v7 = {20, 15, 10, 5, 8, 2, 100, 28, 42};
        Tree<Integer> tree7 = new Tree<>(v7, "Tree7:");

        System.out.println("-------------------------------------------" +
                "\n             PROBLEM 7");
        System.out.println("-------------------------------------------\n");
        System.out.println(tree7.toString());
        tree7.balanceTree();
        tree7.changeName("tree7 after balancing");
        System.out.println("-------------------------------------------\n");
        System.out.println(tree7.toString());

        // Assignment Problem 8
        System.out.println("-------------------------------------------" +
                "\n             PROBLEM 8");
        System.out.println("-------------------------------------------\n");
        System.out.println(tree1.toString());
        tree1.keepRange(10, 50);
        tree1.changeName("tree1 after keeping only nodes between 10 and 50: ");
        System.out.println(tree1.toString());
        System.out.println("-------------------------------------------\n");
        tree7.changeName("Tree 7");
        System.out.println(tree7.toString());
        tree7.keepRange(8, 85);
        tree7.changeName("tree7 after keeping only nodes between 8  and 85: ");
        System.out.println(tree7.toString());
        System.out.println("-------------------------------------------\n");

        // Assignment Problem 9
        System.out.println("-------------------------------------------" +
                "\n             PROBLEM 9");
        System.out.println("-------------------------------------------\n");
        Integer[] v9 = {66, -15, -83, 3, -10, -7, 65, 16, 75, 70, 71, 83, 200, 90};
        Tree<Integer> tree4 = new Tree<Integer>(v9, "Tree4:");
        System.out.println(tree4.toString());
        System.out.println("The maxlevel sum of tree4 is: " + tree4.maxLevelSum());

        // Assignment Problem 10
        System.out.println("-----------------------------------------------------------------------------------------------------------------" +
                "\n                         EVERYTHING BELOW THIS POINT WAS NOT FINISHED :/");
        System.out.println("-----------------------------------------------------------------------------------------------------------------\n");
        ArrayList<Integer> leaves = new ArrayList<Integer>();
        Integer[] preorder1 = {10, 3, 1, 7, 18, 13};

        getLeaves(preorder1, 0, preorder1.length - 1, leaves);
        System.out.print("Leaves are ");
        for (int leaf : leaves) {
            System.out.print(leaf + " ");
        }
        System.out.println();

        leaves = new ArrayList<Integer>();
        Integer[] preorder2 = {66, -15, -83, 3, -10, -7, 65, 16, 75, 70, 71, 83, 200, 90};

        getLeaves(preorder2, 0, preorder2.length - 1, leaves);
        System.out.print("Leaves are ");
        for (int leaf : leaves) {
            System.out.print(leaf + " ");
        }
        System.out.println();
        System.out.println("-------------------------------------------\n");

        // Assignment Problem 11
        Tree<Integer> treeC = new Tree<Integer>("TreeC");
        Integer[] data = {44, 9, 13, 4, 5, 6, 7};
        treeC.createTreeByLevel(data, "Sum Tree1 ?");
        if (treeC.isSum()) {
            System.out.println(treeC.toString() + " is Sum Tree");
        } else {
            System.out.println(treeC.toString() + " is NOT a Sum Tree");
        }
        Integer[] data1 = {52, 13, 13, 4, 5, 6, 7, 0, 4};
        treeC.createTreeByLevel(data1, "Sum Tree2 ?");
        if (treeC.isSum()) {
            System.out.println(treeC.toString() + " is Sum Tree");
        } else {
            System.out.println(treeC.toString() + " is NOT a Sum Tree");
        }
        Integer[] data2 = {44, 13, 13, 4, 5, 6, 7, 1, 4};
        treeC.createTreeByLevel(data2, "Sum Tree3?");
        if (treeC.isSum()) {
            System.out.println(treeC.toString() + " is Sum Tree");
        } else {
            System.out.println(treeC.toString() + " is NOT a Sum Tree");
        }

        // Assignment Problem 12
        treeC.changeName("Tree12");
        System.out.println(treeC.toString() + "MaxPath=" + treeC.maxPath());


        Integer[] data12 = {1, 3, 2, 5, 6, -3, -4, 7, 8};
        treeC.createTreeByLevel(data12, "Another Tree");
        System.out.println(treeC.toString() + "MaxPath=" + treeC.maxPath());


        // Assignment Problem 13
        Integer[] preorderT = {1, 2, 4, 5, 3, 6, 8, 9, 7};
        Integer[] postorderT = {4, 5, 2, 8, 9, 6, 7, 3, 1};
        tree1.createTreeTraversals(preorderT, postorderT, "Tree13 from preorder & postorder");
        System.out.println(tree1.toString());
        Integer[] preorderT2 = {5, 10, 25, 1, 57, 6, 15, 20, 3, 9, 2};
        Integer[] postorderT2 = {1, 57, 25, 6, 10, 20, 9, 2, 3, 15, 5};
        tree1.createTreeTraversals(preorderT2, postorderT2, "Tree from preorder & postorder");
        System.out.println(tree1.toString());
    }
}
