import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    static Graph graph1;

    @BeforeAll
    static void beforeAll() {
        graph1 = new Graph(5);
        graph1.addEdge(0, 1, 15);
        graph1.addEdge(0, 2, 10);
        graph1.addEdge(1, 4, 3);
        graph1.addEdge(1, 3, 6);
        graph1.addEdge(2, 3, 7);
    }

    @Test
    void addEdge() {
    }

    @Test
    void hasCycle() {
        assertTrue(graph1.hasCycle());
        graph1.removeEdge(2, 3, 7);
        assertFalse(graph1.hasCycle());
    }

    @Test
    void printGraph() {
        graph1.printGraph();
    }

    @Test
    void hashCodeTest() {
        Graph graph2 = new Graph(4);
        graph2.addEdge(0, 1, 15);
        graph2.addEdge(2, 3, 17);

        assertEquals(graph2.nodes.get(0).get(0), graph2.nodes.get(1).get(0));
        assertEquals(graph2.nodes.get(2).get(0), graph2.nodes.get(3).get(0));
        assertEquals(graph2.nodes.get(0).get(0).hashCode(), graph2.nodes.get(1).get(0).hashCode());
        assertEquals(graph2.nodes.get(2).get(0).hashCode(), graph2.nodes.get(3).get(0).hashCode());

    }
}