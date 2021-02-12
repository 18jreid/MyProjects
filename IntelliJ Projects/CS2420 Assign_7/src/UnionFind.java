public class UnionFind {
    public UnionFind(int arraySize) {
        myArray = new Node[arraySize];

        for (int i = 0; i < myArray.length; i++) {
            Node node = new Node(i + 1);
            myArray[i] = node;
        }
    }

    /**
     * Finds root of any node given
     * @param node node to be found
     * @return returns the root the node given
     */
    public Node find(Node node) {
        if(node.parent == null) {
            return node;
        }
        return node.parent = find(node.parent);
    }

    /**
     * Evaluates size of two nodes, and merges them together
     * @param root1 root of first node
     * @param root2 root of second node
     */
    public void union(Node root1, Node root2) {
        if (root1.size < root2.size) {
            root2.parent = root1;
            root1.size += root2.size;
        }

        else if (root1.size > root2.size) {
            root1.parent = root2;
            root2.size += root1.size;
        }

        else {
            root2.size += root1.size;
            root1.parent = root2;
        }
    }

    /**
     * prints the entire array in terms of size and parents
     */
    public void printArray() {
        System.out.print("[ ");
        for (Node node : myArray) {
            if (node.parent != null) { System.out.print(node.parent.id + " "); }
            else { System.out.print(node.size + " "); }
        }
        System.out.println("]\n");
    }

    /**
     * Prints all groups of parties, where group number is the root
     */
    public void printGroups() {
        for (Node node : myArray) {
            if (node.parent == null) {
                System.out.println("Group " + find(node).id + " has " + Math.abs(node.size) + " members");
            }
        }
    }

    /**
     * finds the biggest sized root
     * @return returns the size of the biggest size root
     */
    public int findBiggest() {
        int max = -1;
        for (Node node : myArray) {
            if (node.size < max) {
                max = node.size;
            }
        }
        return Math.abs(max);
    }

    public Node[] myArray; // Array of nodes
}
