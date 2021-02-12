public class GraphNode {

    public GraphNode( ){
        this.nodeID = 0;
        this.succ = new MyLinkedList<EdgeInfo>();
        this.nodeName="";
        this.succCt=0;
     }

    public GraphNode(  int nodeID){
        this.nodeID = nodeID;
        this.succ = new MyLinkedList<EdgeInfo>();
        this.nodeName="noName";
        this.succCt=0;
      }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(nodeID + ": " + nodeName + " ");
        succ.printList();
        sb.append(succ.listText + "\n");
        return sb.toString();
    }

    public void addEdge(int v1, int v2){
        //System.out.println("GraphNode.addEdge " + v1 + "->" + v2 + "(" + capacity + ")");
        succ.addFirst( new EdgeInfo(v1,v2) );
        succCt++;
    }


    public int nodeID;
    public int succCt;
    public String nodeName;
    public MyLinkedList<EdgeInfo> succ;
   // boolean holdsSupply;
}
