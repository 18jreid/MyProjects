public class MyLinkedList<E> {
    Node head;
    String listText = "";

    public void addFirst(EdgeInfo edgeInfo) {
        Node node = new Node();
        node.data = edgeInfo.to;
        node.next = null;

        if (head == null) {
            head = node;
        }
        else {
            Node n = head;
            while(n.next != null) {
                n = n.next;
            }
            n.next = node;
        }
    }

    public void printList() {
        Node marker = head;
        listText = marker.printNode();
        while (marker.next != null) {
            marker = marker.next;
            listText += " " + marker.printNode();
        }
    }
}
