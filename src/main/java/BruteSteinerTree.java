import java.util.ArrayList;
import java.util.List;

public class BruteSteinerTree {
    public static UndirectedGraph steinerTree(UndirectedGraph graph, List<Integer> terminalPoints) {

        UndirectedGraph mstOfGraph = MinimalSpanningTree.MST(graph);
        List<WeightedEdge> edgesOfMst = new ArrayList<>(mstOfGraph.getGraphEdges());

        for (WeightedEdge edge : edgesOfMst) {
//            if (terminalPoints.contains(edge.node1) && terminalPoints.contains(edge.node2))
//                continue;
            mstOfGraph.removeEdgeCascade(edge);

            if (!mstOfGraph.isConnected() || !mstOfGraph.containsNodes(terminalPoints))
                mstOfGraph.addEdge(edge);
        }

        return mstOfGraph;
    }
        /*UndirectedGraph steinerTree = new UndirectedGraph(terminalPoints.get(0));
//        int minWeight = Integer.MAX_VALUE;
//        int edgeWithMinWeight;

        while (!steinerTree.containsNodes(terminalPoints)) {
            int minWeight = Integer.MAX_VALUE;
            WeightedEdge edgeWithMinWeight;
            for (int node : steinerTree.getNodes()) {
                for (WeightedEdge edge : graph.getNodeEdges(node)) {
                    if (edge.weight <= minWeight) {
                        minWeight = edge.weight;
                        edgeWithMinWeight = edge;
                    }
                }
            }

        }
        for (WeightedEdge edge : )*/
}
