import java.util.ArrayList;
import java.util.List;

public class BruteSteinerTree {

    /**
     *
     * @param graph the graph in which the minimal Steiner tree will be derived
     * @param terminalPoints the terminal points used to derive the Steiner tree
     * @return
     */
    public static UndirectedGraph steinerTree(UndirectedGraph graph, List<Integer> terminalPoints) {

//        creates a list which contains all the edges of the graph
        List<WeightedEdge> graphEdges = new ArrayList<>(graph.getGraphEdges());
//        uses generateAllSubGraphs() method to store a list of all sub graphs
        List<int []> allCombinationsOfEdges = generateAllSubGraphs(graph);
//        initializes an initial minimal graph weight of the Integer max value
        int minGraphWeight = Integer.MAX_VALUE;
//        initializes the minimal graph to null
        UndirectedGraph minGraph = null;

//        uses a loop to translate the binary array into an undirected graph object
        for (int [] combo : allCombinationsOfEdges) {
//            initializes a new undirected graph
            UndirectedGraph currGraph = new UndirectedGraph();
//            loops through each binary digit in the array
            for (int i = 0; i < combo.length; i++) {
//                if the element in the array is 1, add the corresponding edge to the undirected graph object
                if (combo[i] == 1) {
                    currGraph.addEdge(graphEdges.get(i));
                }
            }

//            if the current graph does not have at least 1 node, continue
            if (currGraph.getNodes().size() < 1)
                continue;

//            if the graph is connected, contains all the terminal points, and does not have a cycle, it satisfies
//            the conditions of a steiner tree so calculate the weight of it
            if (currGraph.isConnected() && currGraph.containsNodes(terminalPoints) && !currGraph.hasCycle()) {
                int weight = 0;
//                loop through each edge in the graph and sum its weight
                for (WeightedEdge edge : currGraph.getGraphEdges()) {
                    weight += edge.weight;
                }
//                if the weight of the tree is less than the minimal weight of the previous steiner trees,
//                set this graph as the new minimal steiner tree
                if (weight < minGraphWeight) {
                    minGraphWeight = weight;
                    minGraph = currGraph;
                }
            }
        }
//        return the minimal steiner tree
        return minGraph;

    }

    /**
     *
     * @param graph the graph in which all sub graphs will be derived
     * @return a list of all possible sub graphs of the graph represented as a binary array
     */
    public static List<int []> generateAllSubGraphs(UndirectedGraph graph) {

//        retrieves the number of edges in the graph
        int numOfGraphEdges = graph.getGraphEdges().size();

//        creates a list of arrays that will hold each combination of edges
        List<int []> allPossibleSubGraphs = new ArrayList<>();
//        uses generateAllSubGraphs() method to get all the possible sub graphs
        generateAllSubGraphs(allPossibleSubGraphs, numOfGraphEdges, new int[numOfGraphEdges], 0);
        return allPossibleSubGraphs;
    }

    /**
     *
     * @param listOfSubGraphs list to store all the sub graphs of the graph
     * @param numOfEdges the number of total edges in the original graph
     * @param currentSubGraph array representation of the current sub graph that is being created
     * @param index index counter of the array
     */
    private static void generateAllSubGraphs(List<int []> listOfSubGraphs, int numOfEdges, int [] currentSubGraph, int index) {
//        base case: if index is equal to array size, simply add the combo to the list
        if (index == numOfEdges) {
//            make a copy of the current combination and add it to the list
            int [] copyArr = new int[numOfEdges];
            System.arraycopy(currentSubGraph, 0, copyArr, 0, numOfEdges);
            listOfSubGraphs.add(copyArr);
            return;
        }

//        set current index to 0 and then recursively find all other combinations for remaining positions
        currentSubGraph[index] = 0;
        generateAllSubGraphs(listOfSubGraphs, numOfEdges, currentSubGraph, index + 1);
//        set current index to 1 and then recursively find all other combinations for remaining positions
        currentSubGraph[index] = 1;
        generateAllSubGraphs(listOfSubGraphs, numOfEdges, currentSubGraph, index + 1);
    }
}
