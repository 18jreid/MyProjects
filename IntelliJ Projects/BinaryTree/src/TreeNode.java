public class TreeNode<E> {
    protected E element;
    protected TreeNode<E> leftSubTree;
    protected TreeNode<E> rightSubTree;

    public TreeNode() {
    }

    public TreeNode(E element) {
        this.element = element;
    }
}
