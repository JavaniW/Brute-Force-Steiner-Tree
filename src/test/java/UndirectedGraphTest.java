import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UndirectedGraphTest {

    static UndirectedGraph undirectedGraph1;
    static UndirectedGraph undirectedGraph2;

    @BeforeAll
    static void beforeAll() {
        undirectedGraph1 = new UndirectedGraph(5);
        undirectedGraph1.addEdge(0, 1, 15);
        undirectedGraph1.addEdge(0, 2, 10);
        undirectedGraph1.addEdge(1, 4, 3);
        undirectedGraph1.addEdge(1, 3, 6);
        undirectedGraph1.addEdge(2, 3, 7);

//        using default constructor
        undirectedGraph2 = new UndirectedGraph();
    }

    @Test
    void addEdge() {
        assertTrue(undirectedGraph1.getAdjList().get(0).contains(new WeightedEdge(0, 1, 15)));
        assertTrue(undirectedGraph1.getAdjList().get(0).contains(new WeightedEdge(0, 2, 10)));
        assertTrue(undirectedGraph1.getAdjList().get(1).contains(new WeightedEdge(1, 4, 3)));
        assertTrue(undirectedGraph1.getAdjList().get(1).contains(new WeightedEdge(1, 3, 6)));
        assertTrue(undirectedGraph1.getAdjList().get(2).contains(new WeightedEdge(2, 3, 7)));

//        testing that other node also contains edge
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
        ArrayList<WeightedEdge> listOfEdges = new ArrayList<>(MinimalSpanningTree.getAllEdges(undirectedGraph1));
        for (WeightedEdge edge : listOfEdges) {
            System.out.printf("Edge: %d — %d, weight: %d || ", edge.node1, edge.node2,edge.weight);
        }
    }

    @Test
    void MST() {
        UndirectedGraph mst = MinimalSpanningTree.MST(undirectedGraph1);
        mst.printGraph();
//        MinimalSpanningTree.getAllEdges(mst);
        ArrayList<WeightedEdge> listOfEdges = new ArrayList<>(MinimalSpanningTree.getAllEdges(mst));
        for (WeightedEdge edge : listOfEdges) {
            System.out.printf("Edge: %d — %d, weight: %d || ", edge.node1, edge.node2,edge.weight);
        }
    }

    @Test
    void printGraph() {
        undirectedGraph1.printGraph();
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
}