public class Main {
    public static void main(String[] args) {
        Integer[] objects = {2, 4, 3, 1, 8, 5, 6, 7};

        Tree<Integer> tree1 = new Tree<Integer>(objects);
        tree1.printTree();
    }
}
