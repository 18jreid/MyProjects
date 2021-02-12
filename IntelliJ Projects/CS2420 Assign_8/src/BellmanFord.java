import java.util.ArrayList;

public class BellmanFord {
    private static final int INF = 9999;
    public ArrayList<GraphNode> shortestPath;

    /**
     * Finds the shortest cost path from source node to sink node
     * @param graph The graph of nodes
     * @param source the source node of the path needed to be found
     * @return
     */
    public boolean bellmanFord(FlowGraph graph, GraphNode source) {
        for (GraphNode node : graph.G) {
            node.distance = INF;
            node.prevNode = -1;
            node.visited = false;
        }
        source.distance = 0;
        source.visited = true;

        for (int i = 0; i < graph.vertexCt - 1; i++) {
            for (EdgeInfo edge : graph.G[i].succ) {
                GraphNode u = graph.G[edge.from];
                GraphNode v = graph.G[edge.to];

                u.visited = true;

                    if ((edge.residualEdge > 0) && (u.distance + edge.cost < v.distance)) {
                        v.distance = u.distance + edge.cost;
                        v.prevNode = u.nodeID;
                        v.visited = true;
                    }
            }
        }

        for (int i = 0; i < graph.getVertexCt() - 1; i++) {
            for (EdgeInfo edge : graph.G[i].succ) {
                GraphNode from = graph.G[edge.from];
                GraphNode to = graph.G[edge.to];

                if ((edge.residualEdge > 0) && (from.distance + edge.cost < to.distance)) {
                    System.out.println("Error: negative cost cycle");
                    break;
                }
            }
        }

        makeShortestPathArray(graph, graph.G[graph.vertexCt - 1]);
        return graph.G[graph.vertexCt - 1].visited;
    }

    /**
     * Fills the 'shortestPath' array with the nodes found from bellman fords shortest path
     * @param graph
     * @param sink
     */
    private void makeShortestPathArray(FlowGraph graph, GraphNode sink) {
        shortestPath = new ArrayList<>();

        if (sink.prevNode != -1) {
            makeShortestPathArray(graph, graph.G[sink.prevNode]);
        }

        shortestPath.add(sink);
    }

    /**
     * Prints the shortest path found by bellman ford
     * @param graph the graph of nodes
     */
    public void printShortestPath(FlowGraph graph) {
        GraphNode sink = graph.G[graph.vertexCt - 1];
        GraphNode predecessor = shortestPath.get(shortestPath.size() - 2);
        EdgeInfo edge = getEdgeTo(predecessor, sink);
        int flow = edge.flow;
        System.out.print("\nBellman Ford found flow " + flow + ": ");
        for (GraphNode node : shortestPath) {
            System.out.print(node.nodeID + " ");
        }
        System.out.println("");
    }

    /**
     * Returns edge from a specific node to it's next (if it exists)
     * @param source the 'from' node
     * @param destination the 'to' node
     * @return the edge from source to destination
     */
    public EdgeInfo getEdgeTo(GraphNode source, GraphNode destination) {
        for (EdgeInfo edge : source.succ) {
            if  (edge.to == destination.nodeID) {
                return edge;
            }
        }

        return null;
    }

    /**
     * Adds updates residual flow on specific edge
     * @param edge the edge to be updated
     * @param flow the amount of flow the edge will be updated to
     */
    public void addResidualFlow(EdgeInfo edge, int flow) {
        edge.flow = flow;
        edge.residualEdge -= flow;
        System.out.println(edge + " assigned " + flow + " of " + edge.capacity + " at cost " + findCost(0));
    }

    /**
     * Finds the cost of the shortest path found
     * @param cost temporary variable to hold value needed to be returned
     * @return the cost of the shortest path
     */
    private int findCost(int cost) {
        for (int i = 0; i < shortestPath.size() - 1; i++) {
            GraphNode node = shortestPath.get(i);
            for (EdgeInfo edge : node.succ) {
                if  (shortestPath.get(i + 1) != null) {
                    if (edge.to == shortestPath.get(i + 1).nodeID) {
                        cost += edge.cost;
                    }
                }
            }
        }

        return cost;
    }
}
