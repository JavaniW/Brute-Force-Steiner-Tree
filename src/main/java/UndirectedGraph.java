import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UndirectedGraph {

//    implements graph using Adjacency Edge List
    private HashMap<Integer, List<WeightedEdge>> adjList;
//    count of nodes in the graph
    private int count;

    /**
     * returns the edge adjacency lists
     *
     * @return all nodes' adjacency lists
     */
    public HashMap<Integer, List<WeightedEdge>> getAdjList() {
        return adjList;
    }

    /**
     * returns the edge list of the specified node
     *
     * @param node node that you want to retrieve
     * @return the edge list associate with the node
     */
    public List<WeightedEdge> getNodeEdges(int node) {
        return adjList.get(node);
    }

    /**
     * returns a list of nodes that are in the graph
     *
     * @return a list of nodes represented as Integers
     */
    public List<Integer> getNodes() {
        return new ArrayList<>(adjList.keySet());
    }

    /**
     * returns the count of nodes in the graph
     *
     * @return the number of nodes in the graph
     */
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
        adjList = new HashMap<>();
//        loops the amount of times specified by count and initializes each node to an empty list
        for (int i = 0; i < count; i++) {
            adjList.put(i, new ArrayList<>());
        }
    }

    public UndirectedGraph() {
        adjList = new HashMap<>();
        count = 0;
    }

    public boolean addNode(int node) {
        if (adjList.containsKey(node))
            return false;
        adjList.put(node, new ArrayList<>());
        return true;
    }

    public void addEdge(WeightedEdge edge) {
        addEdge(edge.node1, edge.node2, edge.weight);
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
        if (!adjList.containsKey(node1)) {
            adjList.put(node1, new ArrayList<>());
            count++;
        }
        if (!adjList.containsKey(node2)) {
            adjList.put(node2, new ArrayList<>());
            count++;
        }
//        adds edge to node 1's edge list
        adjList.get(node1).add(new WeightedEdge(node1, node2, weight));
//        adds edge to node 2's edge list
        adjList.get(node2).add(new WeightedEdge(node2, node1, weight));
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
        if (adjList.get(node1).remove(targetEdge))
            return adjList.get(node2).remove(targetEdge);
//        otherwise, return false
        return false;
    }

    public boolean removeEdge(WeightedEdge targetEdge) {
        return removeEdge(targetEdge.node1, targetEdge.node2, targetEdge.weight);
    }

    /**
     * uses the DFS function to return a boolean value corresponding to if the graph has a cycle or not
     *
     * @return true if the graph has a cycle
     */
    public boolean hasCycle() {
//        creates an int list which will contain the keys of all the nodes that have been visited
        List<Integer> visited = new ArrayList<>();
//        calls DFS method starting from a random node
        List<Integer> allKeys = new ArrayList<>(adjList.keySet());
        return DFS(allKeys.get(0), visited, -1);
    }

    /**
     * performs Depth First Search (DFS) on a graph and determines if it has a cycle of not
     *
     * @param node node of a graph
     * @param visited array of all the nodes that have been visited through Depth First Search (DFS)
     * @param parent parent node of the node
     * @return true if a cycle exists
     */
    public boolean DFS(int node, List<Integer> visited, int parent) {
//        adds the node to the visited list
        visited.add(node);
//        loops over each edge connected to the current node
        for (WeightedEdge weightedEdge : adjList.get(node)) {
//            if the node the edge connects to have not been visited, recursively call DFS method
//            passing it the connecting node and the current node as the parent
            if (!visited.contains(weightedEdge.node2)) {
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
            for (WeightedEdge weightedEdge : adjList.get(i)) {
//                print the edge
                System.out.println(String.format("--> %s", weightedEdge));
            }
            System.out.println();
        }
    }
}
