import java.util.*;

public class MinimalSpanningTree {

    /**
     * uses Kruskal's algorithm to generate the minimal spanning tree of an unweighted graph
     *
     * @param graph the graph in which the minimal spanning tree will be found
     * @return a new graph which is the minimal spanning tree
     */
    public static UndirectedGraph MST(UndirectedGraph graph) {
        if (graph.getCount() < 1)
            return graph;
        if (!graph.isConnected())
            return graph;

//        creates a new empty undirected graph
        UndirectedGraph minimumTree = new UndirectedGraph();
//        retrieves all the edges from the original graph
        List<WeightedEdge> allEdges = new ArrayList<>(graph.getGraphEdges());
//        sorts the edges by weight
        Collections.sort(allEdges);

//        initializes a counter variable to hold the position of the next smallest edge in the list of all edges
        int nextSmallestEdgeIdx = 0;
//        loop until all nodes from the original graph are included in the minimal spanning tree graph
        while (minimumTree.getCount() < graph.getCount()) {
//            uses the counter variable to retrieve the next smallest edge from the list of all edges
            WeightedEdge nextSmallestEdge = allEdges.get(nextSmallestEdgeIdx++);
//            adds the next smallest edge to the minimal spanning tree graph
            minimumTree.addEdge(nextSmallestEdge);

//            if the minimal spanning tree has a cycle, remove the last added edge
            if (minimumTree.hasCycle())
                minimumTree.removeEdge(nextSmallestEdge);
        }
//        return the minimal spanning tree graph
        return minimumTree;
    }

 /*   /**
     * retrieves all the edges found in a graph
     *
     * @param graph the graph in which the edges will be retrieved
     * @return a list of all the edges in the graph with no duplicates
     *
    public static List<WeightedEdge> getAllEdges(UndirectedGraph graph) {
//        creates a hashset to hold the edges (hashsets only hold distinct objects, no duplicates)
        HashSet<WeightedEdge> allEdges = new HashSet<>();

//        loops over each node in the graph
        for (Integer node : graph.getNodes()) {
//            loops over each edge in the edge list corresponding to the node and adds it to the set of all edges
            for (WeightedEdge edge : graph.getNodeEdges(node)) {
                allEdges.add(edge);
            }
        }
//        returns the set of all edges converted into a list
        return new ArrayList<>(allEdges);
    }*/
}
