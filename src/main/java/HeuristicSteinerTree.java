import java.util.ArrayList;
import java.util.List;

public class HeuristicSteinerTree {

    /**
     * uses a Heuristic algorithm created by Dr. George Markowsky to derive the Steiner tree of a graph
     *
     * @param graph the graph in which the minimal Steiner tree will be derived
     * @param terminalPoints the terminal points used to find the Steiner tree
     * @return an undirected graph that is the Steiner tree
     */
    public static UndirectedGraph steinerTree(UndirectedGraph graph, List<Integer> terminalPoints) {

//        Step I. Construct the complete undirected distance graph G1 =(V1,E1,d1) from G and S.
//        creates a new undirected graph which will eventually become the graph g1 outlined in the algorithm
        UndirectedGraph G1 = new UndirectedGraph();

//        loops through all nodes in the list of terminal points
        for (Integer node1 : terminalPoints) {
//            uses Dijkstra's algorithm to find the shortest path from a source
            int [][] costAndParent = graph.shortestPath(node1);

//            loops through each node1 in the list of terminal points that is different from the current node1
            for (Integer node2 : terminalPoints) {
//                if the node1 is the same as the one in the outer loop, contine
                if (node1.equals(node2))
                    continue;

//                initialize a cost value equal to the cost of the shortest path from node1 to node2
                int cost = costAndParent[0][node2];

//                create an edge that combines all the edges from the shortest path from node1 to node2 and
//                add it to the complete graph if it is not already contained
                if (!G1.getGraphEdges().contains(new WeightedEdge(node1, node2, cost)))
                    G1.addEdge(node1, node2, cost);
            }
        }

//        Step 2. Find the minimal spanning tree, T1, of G1. (If there are several minimal spanning trees, pick an arbitrary one.)
//        find the minimal spanning tree of this complete graph
        UndirectedGraph T1 = MinimalSpanningTree.MST(G1);

//        Step 3. Construct the subgraph, Gs, of G by replacing each edge in T1 by its corresponding shortest path m G. (If there are several shortest paths, pick an arbitrary one.)
//        create an empty graph that will eventually become the sub graph of the original graph created
//        from the complete graph
        UndirectedGraph GS = new UndirectedGraph();

//        loop through each edge in the mst of the complete graph
        for (WeightedEdge edge1 : T1.getGraphEdges()) {
//            loop through each edge in the corresponding shortest path from edge1 node1 to node2
            for (WeightedEdge edge2 : graph.shortestPath(edge1.node1, edge1.node2)) {
//                if the graph does not already contain the edge then add it
                if (!GS.getGraphEdges().contains(edge2))
                    GS.addEdge(edge2);
            }
        }

//        Step 4. Find the minimal spanning tree, Ts, of Gs. (If there are several minimal spanning trees, pick an arbitrary one.)
//        find the minimal spanning tree of the subgraph gs
        UndirectedGraph TS = MinimalSpanningTree.MST(GS);


//        Step 5. Construct a Steiner tree, TH, from Ts by deleting edges in Ts, if necessary, so that all the leaves in TH are Steiner points.
        List<Integer> originalNodesOfGraphGs = TS.getNodes();

//        loops through all the nodes of the graph gs
        for (int node : originalNodesOfGraphGs) {
//            if the node only has one edge, i.e., if it is a leaf node, then remove it by removing the edge
            if (TS.getNodeEdges(node).size() == 1 && !terminalPoints.contains(node)) {
                TS.removeEdgeCascade(TS.getNodeEdges(node).get(0));
            }
        }

        return TS;
    }

}
