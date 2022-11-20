import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteSteinerTree {
    public static UndirectedGraph steinerTree(UndirectedGraph graph, List<Integer> terminalPoints) {

        List<WeightedEdge> graphEdges = new ArrayList<>(graph.getGraphEdges());
        List<int []> allCombinationsOfEdges = generateAllCombos(graph);
        int minGraphWeight = Integer.MAX_VALUE;
        UndirectedGraph minGraph = null;

        for (int [] combo : allCombinationsOfEdges) {
            UndirectedGraph currGraph = new UndirectedGraph();
            for (int i = 0; i < combo.length; i++) {
                if (combo[i] == 1) {
                    currGraph.addEdge(graphEdges.get(i));
                }
            }

            if (currGraph.getNodes().size() < 1)
                continue;

            if (currGraph.isConnected() && currGraph.containsNodes(terminalPoints)) {
                int weight = 0;
                for (WeightedEdge edge : currGraph.getGraphEdges()) {
                    weight += edge.weight;
                }

                if (weight < minGraphWeight) {
                    minGraphWeight = weight;
                    minGraph = currGraph;
                }
            }
        }
        return minGraph;



        /*UndirectedGraph mstOfGraph = MinimalSpanningTree.MST(graph);
        List<WeightedEdge> edgesOfMst = new ArrayList<>(mstOfGraph.getGraphEdges());

        for (WeightedEdge edge : edgesOfMst) {
//            if (terminalPoints.contains(edge.node1) && terminalPoints.contains(edge.node2))
//                continue;
            mstOfGraph.removeEdgeCascade(edge);

            if (!mstOfGraph.isConnected() || !mstOfGraph.containsNodes(terminalPoints))
                mstOfGraph.addEdge(edge);
        }

        return mstOfGraph;*/
    }


    public static List<int []> generateAllCombos(UndirectedGraph graph) {

//        retrieves the number of edges in the graph
        int numOfGraphEdges = graph.getGraphEdges().size();

//        creates a list of arrays that will hold each combination of edges
        List<int []> allPossibleCombos = new ArrayList<>();

        generateAllCombos(allPossibleCombos, numOfGraphEdges, new int[numOfGraphEdges], 0);
        return allPossibleCombos;
    }

    static void printTheArray(int arr[], int n)
    {
        for (int i = 0; i < n; i++)
        {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
    private static void generateAllCombos(List<int []> listOfCombos, int n, int [] currentCombo, int index) {
//        base case: if index is equal to array size, simply add the combo to the list
        if (index == n) {
//            make a copy of the current combination and add it to the list
            int [] copyArr = new int[n];
            System.arraycopy(currentCombo, 0, copyArr, 0, n);
            listOfCombos.add(copyArr);

//            printTheArray(currentCombo, n);
            return;
        }

//        set current index to 0 and then recursively find all other combinations for remaining positions
        currentCombo[index] = 0;
        generateAllCombos(listOfCombos, n, currentCombo, index + 1);
//        set current index to 1 and then recursively find all other combinations for remaining positions
        currentCombo[index] = 1;
        generateAllCombos(listOfCombos, n, currentCombo, index + 1);
    }
}
