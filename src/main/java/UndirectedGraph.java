import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UndirectedGraph {

//    implements graph using Adjacency Edge List
    private HashMap<Integer, List<WeightedEdge>> nodes;
//    count of nodes in the graph
    private int count;

    /**
     * returns the nodes' edges lists
     *
     * @return all nodes in the graph
     */
    public HashMap<Integer, List<WeightedEdge>> getNodes() {
        return nodes;
    }

    /**
     * returns the edge list of the specified node
     *
     * @param node node that you want to retrieve
     * @return the edge list associate with the node
     */
    public List<WeightedEdge> getNode(int node) {
        return nodes.get(node);
    }

    public int getCount() {
        return count;
    }


    /**
     * creates a graph with a specific number of nodes
     *
     * @param count number of nodes the graph can hold
     */
    public UndirectedGraph(int count) {
//        assigns count value
        this.count = count;
//        initialize the nodes list
        nodes = new HashMap<>();
//        loops the amount of times specified by count and initializes each node to an empty list
        for (int i = 0; i < count; i++) {
            nodes.put( i,new ArrayList<>());
        }
    }

    public UndirectedGraph() {
        nodes = new HashMap<>();
        count = 0;
    }

    /**
     * adds an edge to the graph
     *
     * @param node1 starting node of an edge
     * @param node2 ending node of an edge
     * @param weight weight of the edge
     */
    public void addEdge(int node1, int node2, int weight) {
//        in the case in which the default constructor is used, add the specified nodes to the graph first
        if (!nodes.containsKey(node1))
            nodes.put(node1, new ArrayList<>());
        if (!nodes.containsKey(node2))
            nodes.put(node2, new ArrayList<>());
//        adds edge to node x's edge list
        nodes.get(node1).add(new WeightedEdge(node1, node2, weight));
//        adds edge to node y's edge list
        nodes.get(node2).add(new WeightedEdge(node2, node1, weight));
    }

    /**
     * removes a specific edge from the graph
     *
     * @param node1 first node of the edge
     * @param node2 second node of the edge
     * @param weight weight of the edge
     * @return boolean value corresponding to if the removal was successful
     */
    public boolean removeEdge(int node1, int node2, int weight) {
//        creates a mock of the target edge
        WeightedEdge targetEdge = new WeightedEdge(node1, node2, weight);
//        returns true if target edge is found in both node1 and node2 and also removes edge from thos nodes
        if (nodes.get(node1).remove(targetEdge))
            return nodes.get(node2).remove(targetEdge);
//        otherwise, return false
        return false;
    }

    /**
     * uses the DFS function to return a boolean value corresponding to if the graph has a cycle or not
     *
     * @return true if the graph has a cycle
     */
    public boolean hasCycle() {
//        creates a boolean array of size count
        boolean [] visited = new boolean[count];
//        calls DFS method starting from node 0
        return DFS(0, visited, -1);
    }

    /**
     * performs Depth First Search (DFS) on a graph and determines if it has a cycle of not
     *
     * @param node node of a graph
     * @param visited array of all the nodes that have been visited through Depth First Search (DFS)
     * @param parent parent node of the node
     * @return true if a cycle exists
     */
    public boolean DFS(int node, boolean [] visited, int parent) {
//        sets visited as true for the current node
        visited[node] = true;
//        loops over each edge connected to the current node
        for (WeightedEdge weightedEdge : nodes.get(node)) {
//            if the node the edge connects to have not been visited, recursively call DFS method
//            passing it the connecting node and the current node as the parent
            if (!visited[weightedEdge.node2]) {
//                if DFS on the new node returns true, then return true
                if (DFS(weightedEdge.node2, visited, node)) {
                    return true;
                }
            }
//            otherwise, if the node the edge connects to is not the parent node, return true
            else if (weightedEdge.node2 != parent) {
                return true;
            }
        }
//        return false
        return false;
    }

    /**
     * prints the graph to the console
     */
    public void printGraph() {
//        loop over each node in the graph
        for (int i = 0; i < count; i++) {
//            print the node
            System.out.print(String.format("Node %d:", i));
//            loop over each edge of the node
            for (WeightedEdge weightedEdge : nodes.get(i)) {
//                print the edge
                System.out.println(String.format("--> %s", weightedEdge));
            }
            System.out.println();
        }
    }
}
