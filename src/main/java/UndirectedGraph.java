import java.util.*;

public class UndirectedGraph {

//    implements graph using Adjacency Edge List
    private HashMap<Integer, List<WeightedEdge>> adjList;
//    count of nodes in the graph
    private int count;

//    set that will hold all edges in the graph
    private HashSet<WeightedEdge> graphEdges;

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
     * returns the set of all weighted edges in the graph
     *
     * @return set of weighted edges
     */
    public HashSet<WeightedEdge> getGraphEdges() {
        return graphEdges;
    }

    /**
     * creates a graph with a specific number of nodes
     *
     * @param count number of nodes the graph can hold
     */
    public UndirectedGraph(int count) {
//        initializes set from edges
        graphEdges = new HashSet<>();
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
//        initializes set from edges
        graphEdges = new HashSet<>();
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
//        adds edge to the set of edges
        graphEdges.add(new WeightedEdge(node1, node2, weight));
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
//        returns true if target edge is found in both node1 and node2 and also removes edge from those nodes
        if (adjList.get(node1).remove(targetEdge)) {
            graphEdges.remove(targetEdge);
            return adjList.get(node2).remove(targetEdge);
        }
//        otherwise, return false
        return false;
    }

    /**
     * removes a specific edge from the graph. If the edge is the last edge in the edge list of
     * either node then that node will also be removed from the graph.
     *
     * @param node1 first node of the edge
     * @param node2 second node of the edge
     * @param weight weight of the edge
     * @return boolean value corresponding to if the removal was successful
     */
    public boolean removeEdgeCascade(int node1, int node2, int weight) {
//        creates a mock of the target edge
        WeightedEdge targetEdge = new WeightedEdge(node1, node2, weight);

//        removes the edges from both nodes if it is found. Checks if the resulting nodes has any edges. If not,
//        then those nodes are also removed from the graph
        if (adjList.get(node1).remove(targetEdge)) {
            if (adjList.get(node2).remove(targetEdge)) {
                if (getNodeEdges(node1).isEmpty())
                    getAdjList().remove(node1);
                if (getNodeEdges(node2).isEmpty())
                    getAdjList().remove(node2);
                graphEdges.remove(targetEdge);
                return true;
            }
        }
        return false;
    }

    public boolean removeEdgeCascade(WeightedEdge targetEdge) {
        return removeEdgeCascade(targetEdge.node1, targetEdge.node2, targetEdge.weight);
    }

    public boolean removeEdge(WeightedEdge targetEdge) {
        return removeEdge(targetEdge.node1, targetEdge.node2, targetEdge.weight);
    }

    /**
     * uses the isCyclic function to return a boolean value corresponding to if the graph has a cycle or not
     *
     * @return true if the graph has a cycle
     */
    public boolean hasCycle() {
//        creates an int list which will contain the keys of all the nodes that have been visited
        List<Integer> visited = new ArrayList<>();
//        calls isCyclic method starting from a random node
        List<Integer> allKeys = new ArrayList<>(adjList.keySet());
        return isCyclic(allKeys.get(0), visited, -1);
    }

    /**
     * performs Depth First Search (isCyclic) on a graph and determines if it has a cycle of not
     *
     * @param node node of a graph
     * @param visited array of all the nodes that have been visited through Depth First Search (isCyclic)
     * @param parent parent node of the node
     * @return true if a cycle exists
     */
    public boolean isCyclic(int node, List<Integer> visited, int parent) {
//        adds the node to the visited list
        visited.add(node);
//        loops over each edge connected to the current node
        for (WeightedEdge weightedEdge : adjList.get(node)) {
//            if the node the edge connects to have not been visited, recursively call isCyclic method
//            passing it the connecting node and the current node as the parent
            if (!visited.contains(weightedEdge.node2)) {
//                if isCyclic on the new node returns true, then return true
                if (isCyclic(weightedEdge.node2, visited, node)) {
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
     * uses Breadth First Search to determine if the graph is connected
     *
     * @return true if the graph is connected
     */
    public boolean isConnected() {
//        creates a set to hold the nodes that have already been visited
        HashSet<Integer> visited = new HashSet<>();
//        creates a linked list that will act as a queue for BFS
        LinkedList<Integer> queue = new LinkedList<>();
//        sets the starting node as random node in the graph
        int startingNode = new ArrayList<>(adjList.keySet()).get(0);
//        adds the startingNode to the queue
        queue.add(startingNode);

//        loops as long as the queue is not empty
        while (!queue.isEmpty()) {
//            sets the node as the node dequeued
            int node = queue.poll();
//            adds the node to the visited array
            visited.add(node);

//            adds every connected node in the current node's adj list to the queue unless it has already been visited
            for (WeightedEdge edge : adjList.get(node)) {
//                if the node has already been visited, dismiss it and don't add it to the queue
                if (visited.contains(edge.node2))
                    continue;
//                otherwise, add it to the queue
                queue.add(edge.node2);
            }
        }
//        if the visited array does not contain all nodes in the graph, then every node has not been visited
//        and therefore the graph is not connected, so return false
        return visited.containsAll(getNodes());
    }

    /**
     * prints the graph to the console
     */
    public void printGraph() {
//        loop over each node in the graph
        for (int node : getNodes()) {
//            print the node
            System.out.print(String.format("Node %d:", node));
//            loop over each edge of the node
            for (WeightedEdge weightedEdge : getNodeEdges(node)) {
//                print the edge
                System.out.println(String.format("--> %s", weightedEdge));
            }
        }
 /*       for (int i = 0; i < count; i++) {
//            print the node
            System.out.print(String.format("Node %d:", i));
//            loop over each edge of the node
            for (WeightedEdge weightedEdge : adjList.get(i)) {
//                print the edge
                System.out.println(String.format("--> %s", weightedEdge));
            }
            System.out.println();
        }*/
    }

    /**
     * returns a boolean value representing if all the target nodes are in the graph
     *
     * @param targetNodes the array of nodes to search for
     * @return false if all the target nodes are not a part of the graph
     */
    public boolean containsNodes(List<Integer> targetNodes) {
        return getNodes().containsAll(targetNodes);
    }

}
