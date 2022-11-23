import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UndirectedGraphTest {

    static UndirectedGraph undirectedGraph1;
    static UndirectedGraph undirectedGraph1Mst;
    static UndirectedGraph undirectedGraph2;
    static UndirectedGraph testGraphEmpty;
    static UndirectedGraph testGraph1;
    static UndirectedGraph testGraph2;
    static UndirectedGraph testGraph3;
    static UndirectedGraph testGraph4;

    @BeforeAll
    static void beforeAll() {
//        creating undirectedGraph1
        undirectedGraph1 = new UndirectedGraph(5);
        undirectedGraph1.addEdge(0, 1, 15);
        undirectedGraph1.addEdge(0, 2, 10);
        undirectedGraph1.addEdge(1, 4, 3);
        undirectedGraph1.addEdge(1, 3, 6);
        undirectedGraph1.addEdge(2, 3, 7);

//        using default constructor
        undirectedGraph2 = new UndirectedGraph();

//        creating testGraphEmpty
        testGraphEmpty = new UndirectedGraph();

//        creating testGraph1 (has cycle)
        testGraph1 = new UndirectedGraph(6);
        testGraph1.addEdge(0, 5, 14);
        testGraph1.addEdge(0, 2, 9);
        testGraph1.addEdge(0, 1, 7);
        testGraph1.addEdge(1, 2, 10);
        testGraph1.addEdge(1, 3, 15);
        testGraph1.addEdge(2, 5, 2);
        testGraph1.addEdge(2, 3, 11);
        testGraph1.addEdge(3, 4, 6);
        testGraph1.addEdge(4, 5, 9);

//        creating testGraph2 (has cycle)
        testGraph2 = new UndirectedGraph(9);
        testGraph2.addEdge(0, 1, 4);
        testGraph2.addEdge(0, 7, 8);
        testGraph2.addEdge(1, 7, 11);
        testGraph2.addEdge(1, 2, 8);
        testGraph2.addEdge(2, 8, 2);
        testGraph2.addEdge(2, 5, 4);
        testGraph2.addEdge(2, 3, 7);
        testGraph2.addEdge(3, 5, 14);
        testGraph2.addEdge(3, 4, 9);
        testGraph2.addEdge(4, 5, 10);
        testGraph2.addEdge(5, 6, 2);
        testGraph2.addEdge(6, 8, 6);
        testGraph2.addEdge(6, 7, 1);
        testGraph2.addEdge(7, 8, 7);

//        creating testGraph3 (has cycle)
        testGraph3 = new UndirectedGraph(6);
        testGraph3.addEdge(0, 1, 4);
        testGraph3.addEdge(0, 4, 4);
        testGraph3.addEdge(0, 2, 3);
        testGraph3.addEdge(1, 3, 2);
        testGraph3.addEdge(1, 4, 4);
        testGraph3.addEdge(1, 2, 5);
        testGraph3.addEdge(2, 3, 7);
        testGraph3.addEdge(3, 4, 2);
        testGraph3.addEdge(4, 5, 6);

//        creating testGraph4 (has cycle)
        testGraph4 = new UndirectedGraph(6);
        testGraph4.addEdge(0, 1, 3);
        testGraph4.addEdge(0, 2, 10);
        testGraph4.addEdge(1, 3, 7);
        testGraph4.addEdge(2, 3, 1);
        testGraph4.addEdge(2, 4, 5);
        testGraph4.addEdge(3, 5, 1);
        testGraph4.addEdge(4, 5, 8);

    }


    @Test
    void addEdge() {
        assertTrue(undirectedGraph1.getAdjList().get(0).contains(new WeightedEdge(0, 1, 15)));
        assertTrue(undirectedGraph1.getAdjList().get(0).contains(new WeightedEdge(0, 2, 10)));
        assertTrue(undirectedGraph1.getAdjList().get(1).contains(new WeightedEdge(1, 4, 3)));
        assertTrue(undirectedGraph1.getAdjList().get(1).contains(new WeightedEdge(1, 3, 6)));
        assertTrue(undirectedGraph1.getAdjList().get(2).contains(new WeightedEdge(2, 3, 7)));

//        testing that other node also containsNodes edge
        assertTrue(undirectedGraph1.getAdjList().get(1).contains(new WeightedEdge(0, 1, 15)));
        assertTrue(undirectedGraph1.getAdjList().get(2).contains(new WeightedEdge(0, 2, 10)));
        assertTrue(undirectedGraph1.getAdjList().get(4).contains(new WeightedEdge(1, 4, 3)));
        assertTrue(undirectedGraph1.getAdjList().get(3).contains(new WeightedEdge(1, 3, 6)));
        assertTrue(undirectedGraph1.getAdjList().get(3).contains(new WeightedEdge(2, 3, 7)));

//        testing using default
        undirectedGraph2.addEdge(0, 1, 15);
        undirectedGraph2.addEdge(5, 7, 10);
        assertNotNull(undirectedGraph2.getAdjList().get(0));
        assertNotNull(undirectedGraph2.getAdjList().get(1));
        assertNotNull(undirectedGraph2.getAdjList().get(5));
        assertNotNull(undirectedGraph2.getAdjList().get(7));
        assertNull(undirectedGraph2.getAdjList().get(8));

    }

    @Test
    void hasCycle() {
        assertTrue(undirectedGraph1.hasCycle());
        undirectedGraph1.removeEdge(2, 3, 7);
        assertFalse(undirectedGraph1.hasCycle());
    }

    @Test
    void getAllEdges() {
        ArrayList<WeightedEdge> listOfEdges = new ArrayList<>(undirectedGraph1.getGraphEdges());
        for (WeightedEdge edge : listOfEdges) {
            System.out.printf("Edge: %d — %d, weight: %d || ", edge.node1, edge.node2,edge.weight);
        }
    }

    @Test
    void MST() {
        undirectedGraph1Mst = MinimalSpanningTree.MST(undirectedGraph1);
        undirectedGraph1Mst.printGraph();
        for (WeightedEdge edge : undirectedGraph1Mst.getGraphEdges()) {
            System.out.printf("Edge: %d — %d, weight: %d || ", edge.node1, edge.node2,edge.weight);
        }
        System.out.println();
        System.out.println("-------TEST GRAPH 1-------");
        UndirectedGraph mstOfTestGraph1 = MinimalSpanningTree.MST(testGraph1);
        System.out.println("mst count: " + mstOfTestGraph1.getCount());
        System.out.println("OG count: " + testGraph1.getCount());
        for (WeightedEdge edge : mstOfTestGraph1.getGraphEdges()) {
            System.out.printf("Edge: %d — %d, weight: %d || ", edge.node1, edge.node2,edge.weight);
        }
    }

    @Test
    void isConnected() {
        UndirectedGraph undirectedGraph3 = new UndirectedGraph();
        undirectedGraph3.addEdge(0, 1, 10);
        undirectedGraph3.addEdge(0, 2, 7);
        undirectedGraph3.addEdge(2, 3, 8);
        assertTrue(undirectedGraph3.isConnected());
//        assertTrue(undirectedGraph1Mst.isConnected());

        assertTrue(testGraph1.isConnected());
        testGraph1.removeEdge(4, 5, 9);
        testGraph1.removeEdge(2, 3, 11);
        testGraph1.removeEdge(1, 3, 15);
        assertFalse(testGraph1.isConnected());

        assertTrue(testGraph2.isConnected());
        testGraph2.removeEdge(2, 3, 7);
        testGraph2.removeEdge(6, 5, 2);
        testGraph2.removeEdge(2, 5, 4);
        assertFalse(testGraph2.isConnected());

        assertTrue(testGraph3.isConnected());
        testGraph3.removeEdge(1, 4, 4);
        testGraph3.removeEdge(0, 4, 4);
        testGraph3.removeEdge(2, 3, 7);
        testGraph3.removeEdge(1, 3, 2);
        assertFalse(testGraph3.isConnected());

        assertTrue(testGraph4.isConnected());
        testGraph4.removeEdge(1, 3, 7);
        testGraph4.removeEdge(2, 3, 1);
        testGraph4.removeEdge(2, 4, 5);
        assertFalse(testGraph4.isConnected());
    }

    @Test
    void contains() {
//        testing when all nodes are in the graph
        assertTrue(testGraph1.containsNodes(new ArrayList<Integer>(List.of(0, 1, 2, 3, 4, 5))));
        assertTrue(testGraph2.containsNodes(new ArrayList<Integer>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8))));
        assertTrue(testGraph3.containsNodes(new ArrayList<Integer>(List.of(0, 1, 2, 3, 4, 5))));
        assertTrue(testGraph4.containsNodes(new ArrayList<Integer>(List.of(0, 1, 2, 3, 4, 5))));

//        testing when all nodes are in the graph except one
        assertFalse(testGraph1.containsNodes(new ArrayList<Integer>(List.of(0, 1, 2, 3, 4, 5, 10))));
        assertFalse(testGraph2.containsNodes(new ArrayList<Integer>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 10))));
        assertFalse(testGraph3.containsNodes(new ArrayList<>(List.of(1, 2, 3, 4, 5, 10))));
        assertFalse(testGraph4.containsNodes(new ArrayList<>(List.of(1, 2, 3, 4, 5, 10))));

//        testing when neither nodes are in the graph
        assertFalse(testGraph1.containsNodes(new ArrayList<>(List.of(10, 11, 12, 13, 14))));
        assertFalse(testGraph2.containsNodes(new ArrayList<>(List.of(10, 11, 12, 13, 14, 15, 16, 17, 18))));
        assertFalse(testGraph3.containsNodes(new ArrayList<>(List.of(10, 11, 12, 13, 14))));
        assertFalse(testGraph4.containsNodes(new ArrayList<>(List.of(10, 11, 12, 13, 14))));
    }

    @Test
    void printGraph() {
        undirectedGraph1.printGraph();
    }

    @Test
    void removeEdgeCascade() {
//        test for graph 1
        assertTrue(testGraph1.containsNodes(new ArrayList<>(List.of(0,1, 2, 3, 4, 5))));
        assertTrue(testGraph1.removeEdgeCascade(4, 3, 6));
        assertTrue(testGraph1.removeEdgeCascade(2, 3, 11));
        assertTrue(testGraph1.removeEdgeCascade(1, 3, 15));
        assertFalse(testGraph1.containsNodes(new ArrayList<>(List.of(0, 1, 2, 3, 4, 5))));
        System.out.println(testGraph1.getNodes());
        assertTrue(testGraph1.containsNodes(new ArrayList<>(List.of(0, 1, 2, 4, 5))));

//        test for graph 2
        assertTrue(testGraph2.containsNodes(new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8))));
        assertTrue(testGraph2.removeEdgeCascade(1, 2, 8));
        assertTrue(testGraph2.removeEdgeCascade(2, 3, 7));
        assertTrue(testGraph2.removeEdgeCascade(8, 2, 2));
        assertTrue(testGraph2.removeEdgeCascade(2, 5, 4));
        assertFalse(testGraph2.containsNodes(new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8))));
        assertTrue(testGraph2.containsNodes(new ArrayList<>(List.of(0, 1, 3, 4, 5, 6, 7, 8))));

//        test for graph 3
        assertTrue(testGraph3.containsNodes(new ArrayList<>(List.of(0, 1, 2, 3, 4, 5))));
        assertTrue(testGraph3.removeEdgeCascade(0, 2, 3));
        assertTrue(testGraph3.removeEdgeCascade(1, 2, 5));
        assertTrue(testGraph3.removeEdgeCascade(2, 3, 7));
        assertFalse(testGraph3.containsNodes(new ArrayList<>(List.of(0, 1, 2, 3, 4, 5))));
        assertTrue(testGraph3.containsNodes(new ArrayList<>(List.of(0, 1, 3, 4, 5))));

//        test for graph 4
        assertTrue(testGraph4.containsNodes(new ArrayList<>(List.of(0, 1, 3, 2, 4, 5))));
        assertTrue(testGraph4.removeEdgeCascade(0, 1, 3));
        assertTrue(testGraph4.removeEdgeCascade(1, 3, 7));
        assertFalse(testGraph4.containsNodes(new ArrayList<>(List.of(0, 2, 3, 4, 5))));
        assertTrue(testGraph4.containsNodes(new ArrayList<>(List.of(0, 2, 3, 4, 5))));
    }

    @Test
    void steinerTree() {
        UndirectedGraph steinerTree1 = BruteSteinerTree.steinerTree(testGraph1, List.of(1, 3));
        testGraph1.printGraph();
        System.out.println("------- MINIMAL SPANNING TREE----------");
        UndirectedGraph MST = MinimalSpanningTree.MST(testGraph1);
        MST.printGraph();
        System.out.println("------- STEINER TREE----------");
        steinerTree1.printGraph();
        System.out.println(steinerTree1.getNodes());
    }

    @Test
    void hashCodeTest() {
        UndirectedGraph undirectedGraph2 = new UndirectedGraph(4);
        undirectedGraph2.addEdge(0, 1, 15);
        undirectedGraph2.addEdge(2, 3, 17);

        assertEquals(undirectedGraph2.getAdjList().get(0).get(0), undirectedGraph2.getAdjList().get(1).get(0));
        assertEquals(undirectedGraph2.getAdjList().get(2).get(0), undirectedGraph2.getAdjList().get(3).get(0));
        assertEquals(undirectedGraph2.getAdjList().get(0).get(0).hashCode(), undirectedGraph2.getAdjList().get(1).get(0).hashCode());
        assertEquals(undirectedGraph2.getAdjList().get(2).get(0).hashCode(), undirectedGraph2.getAdjList().get(3).get(0).hashCode());

    }

    @Test
    void shortestPath() {
//        testGraph 1
        int [][] costsAndParents = testGraph1.shortestPath(0);
        for (int i = 0; i < costsAndParents.length; i++) {
            for (int j = 0; j < costsAndParents[i].length; j++) {
                if (i == 0)
                    System.out.printf("Cost to node %d: %d", j, costsAndParents[i][j]);
                System.out.println();
                if (i == 1)
                    System.out.printf("Parent of node %d: %d", j, costsAndParents[i][j]);
                System.out.println();
            }
        }
    }

    @Test
    void generateAllCombos() {
        List<int []> combos = BruteSteinerTree.generateAllSubGraphs(testGraph1);

        for (int [] combo: combos) {
            for (int i = 0; i < combo.length; i++) {
                System.out.print(combo[i] + " | ");
            }
            System.out.println();
        }
    }
}