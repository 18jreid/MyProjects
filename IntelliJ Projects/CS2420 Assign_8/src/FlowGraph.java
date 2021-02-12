import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class FlowGraph {
    int vertexCt;  // Number of vertices in the graph.
    int edgeCt; // Number of edges in the graph.
    GraphNode[] G;  // Adjacency list for graph.
    ArrayList<EdgeInfo> E = new ArrayList<>();
    String graphName;  //The file from which the graph was created.
    int maxFlowFromSource;
    int maxFlowIntoSink;


    public FlowGraph() {
        this.vertexCt = 0;
        this.graphName = "";
        this.maxFlowFromSource = 0;
        this.maxFlowIntoSink = 0;
    }

    /**
     * create a graph with vertexCt nodes
     * @param vertexCt
     */
    public FlowGraph(int vertexCt) {
        this.vertexCt = vertexCt;
        G = new GraphNode[vertexCt];
        for (int i = 0; i < vertexCt; i++) {
            G[i] = new GraphNode(i);
        }
        this.maxFlowFromSource = 0;
        this.maxFlowIntoSink = 0;
    }

    public static void main(String[] args) {
        FlowGraph graph1 = new FlowGraph();
        graph1.makeGraph("group0.txt");
        System.out.println(graph1.toString());
    }

    public int getVertexCt() {
        return vertexCt;
    }

    public int getMaxFlowFromSource() {
        return maxFlowFromSource;
    }

    public int getMaxFlowIntoSink() {
        return maxFlowIntoSink;
    }

    /**
     * @param source
     * @param destination
     * @param cap         capacity of edge
     * @param cost        cost of edge
     * @return create an edge from source to destination with capacation
     */
    public boolean addEdge(int source, int destination, int cap, int cost) {
        System.out.println("addEdge " + source + "->" + destination + "(" + cap + ", " + cost + ")");
        if (source < 0 || source >= vertexCt) return false;
        if (destination < 0 || destination >= vertexCt) return false;
        //add edge
        G[source].addEdge(source, destination, cap, cost);
        edgeCt += 1;
        return true;
    }

    /**
     * @param source
     * @param destination
     * @return return the capacity between source and destination
     */
    public int getCapacity(int source, int destination) {
        return G[source].getCapacity(destination);
    }

    /**
     * @param source
     * @param destination
     * @return the cost of the edge from source to destination
     */
    public int getCost(int source, int destination) {
        return G[source].getCost(destination);
    }

    /**
     * @return string representing the graph
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("The Graph " + graphName + " \n");
        sb.append("Total input " + maxFlowIntoSink + " :  Total output " + maxFlowFromSource + "\n");

        for (int i = 0; i < vertexCt; i++) {
            sb.append(G[i].toString());
        }
        return sb.toString();
    }

    /**
     * Builds a graph from filename.  It automatically inserts backward edges needed for mincost max flow.
     * @param filename
     */
    public void makeGraph(String filename) {
        try {
            graphName = filename;
            Scanner reader = new Scanner(new File(filename));
            vertexCt = reader.nextInt();
            G = new GraphNode[vertexCt];
            for (int i = 0; i < vertexCt; i++) {
                G[i] = new GraphNode(i);
            }
            while (reader.hasNextInt()) {
                int v1 = reader.nextInt();
                int v2 = reader.nextInt();
                int cap = reader.nextInt();
                int cost = reader.nextInt();
                G[v1].addEdge(v1, v2, cap, cost);
                G[v2].addEdge(v2, v1, 0, -cost);
                if (v1 == 0) maxFlowFromSource += cap;
                if (v2 == vertexCt - 1) maxFlowIntoSink += cap;
            }

            getEdges();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getEdges() {
        for (int i = 0; i  < G.length; i++) {
            E.addAll(G[i].succ);
        }
    }
}