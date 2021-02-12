public class EdgeInfo {

    public EdgeInfo(int from, int to, int capacity, int cost){
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;
        this.residualEdge = this.capacity - this.flow;
        this.cost = cost;
    }
    public String toString(){
        return "Edge " + from + "->" + to + " ";
    }

    int from;        // source of edge
    int to;          // destination of edge
    int capacity;    // capacity of edge
    int cost;        // cost of edge
    int flow;
    int residualEdge;
}
