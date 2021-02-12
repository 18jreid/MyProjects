public class MaxFlow {
    /**
     * Finds shortest min-cost paths from source to sink
     * @param graph The graph of nodes
     * @param source The source node
     */
    public void FordFulkerson(FlowGraph graph, GraphNode source) {
        BellmanFord myBellmanFord = new BellmanFord();

        while(myBellmanFord.bellmanFord(graph, source)) {
            int availFlow = 9999;
            myBellmanFord.printShortestPath(graph);

            for (int i = 0; i < myBellmanFord.shortestPath.size() - 1; i++) {
                if (myBellmanFord.shortestPath.get(i + 1) != null) {
                    EdgeInfo edge = myBellmanFord.getEdgeTo(myBellmanFord.shortestPath.get(i), myBellmanFord.shortestPath.get(i + 1));
                    availFlow = Math.min(availFlow, edge.residualEdge);
                }
            }


            for (int i = 0; i < myBellmanFord.shortestPath.size() - 1; i++) {
                GraphNode node = myBellmanFord.shortestPath.get(i);
                GraphNode successorNode = myBellmanFord.shortestPath.get(i + 1);

                if (successorNode != null) {
                    EdgeInfo edge = myBellmanFord.getEdgeTo(node, successorNode);
                    if (edge != null) {
                        myBellmanFord.addResidualFlow(edge, availFlow);
                    }
                }
            }
        }
    }
}
