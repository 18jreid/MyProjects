import java.util.ArrayList;

public class Node {
    public Node parent = null;
    public final int id;
    public int size = -1;
    public ArrayList<Node> opponents = new ArrayList<Node>();

    public Node(int id) {
        this.id = id;
    }
}
