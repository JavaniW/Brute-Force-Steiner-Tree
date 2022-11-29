import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class EmpiricalAnalysisProgram {



    public static void main (String [] args) throws InterruptedException {

//        TEST GRAPHS

        UndirectedGraph testGraph1 = new UndirectedGraph();
        UndirectedGraph testGraph2 = new UndirectedGraph();
        UndirectedGraph testGraph3 = new UndirectedGraph();
        UndirectedGraph testGraph4 = new UndirectedGraph();
        UndirectedGraph testGraph5 = new UndirectedGraph();
        UndirectedGraph testGraph6 = new UndirectedGraph();


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

//        undirected graph1
//        creating testGraph5
        testGraph5 = new UndirectedGraph(5);
        testGraph5.addEdge(0, 1, 15);
        testGraph5.addEdge(0, 2, 10);
        testGraph5.addEdge(1, 4, 3);
        testGraph5.addEdge(1, 3, 6);
        testGraph5.addEdge(2, 3, 7);

        //        marko graph
        testGraph6 = new UndirectedGraph();
        testGraph6.addEdge(0, 1, 20);
        testGraph6.addEdge(0, 8, 2);
        testGraph6.addEdge(1, 2, 16);
        testGraph6.addEdge(1, 5, 2);
        testGraph6.addEdge(2, 4, 4);
        testGraph6.addEdge(2, 3, 18);
        testGraph6.addEdge(3, 4, 4);
        testGraph6.addEdge(4, 8, 2);
        testGraph6.addEdge(5, 4, 2);
        testGraph6.addEdge(5, 6, 2);
        testGraph6.addEdge(6, 7, 1);
        testGraph6.addEdge(7, 8, 1);


        List<UndirectedGraph> allTestGraphs = new ArrayList<>();
        allTestGraphs.add(testGraph1);
        allTestGraphs.add(testGraph2);
        allTestGraphs.add(testGraph3);
        allTestGraphs.add(testGraph4);
        allTestGraphs.add(testGraph5);
        allTestGraphs.add(testGraph6);

//   ------------- BRUTE FORCE TESTS -------------
        System.out.printf("BRUTE FORCE STEINER TREE TESTS\n");

        float sumOfRunTimes = 0;
        float avgTime = 0f;
        long start, end;

        /*
        TEST USING TEST GRAPH 1 | Terminal Points: 0, 2, 4
        V = {0, 1, 2, 3, 4, 5}
         */
        for (int i = 0; i < 100; i++) {
            start = System.currentTimeMillis();
            BruteSteinerTree.steinerTree(testGraph1, List.of(0, 2, 4));
            end = System.currentTimeMillis();
            sumOfRunTimes += (end - start);
        }
        avgTime = sumOfRunTimes / 100;
        System.out.printf("Approximate avg execution time of Brute Force Steiner tree algorithm for test graph 1" +
                " using points 0, 2, 4: %1.3f\n", avgTime);
        Thread.sleep(1000);

        /*
        TEST USING TEST GRAPH 2 | Terminal Points: 7, 4, 2, 8
        V = {0, 1, 2, 3, 4, 5, 6, 7, 8}
         */
        sumOfRunTimes = 0;
        for (int i = 0; i < 100; i++) {
            start = System.currentTimeMillis();
            BruteSteinerTree.steinerTree(testGraph2, List.of(7, 4, 2, 8));
            end = System.currentTimeMillis();
            sumOfRunTimes += (end - start);
        }
        avgTime = sumOfRunTimes / 100;
        System.out.printf("Approximate avg execution time of Brute Force Steiner tree algorithm for test graph 2" +
                " using points 7, 4, 2, 8: %1.3f\n", avgTime);
        Thread.sleep(1000);


        /*
        TEST USING TEST GRAPH 3 | Terminal Points: 0, 3, 4
        V = {0, 1, 2, 3, 4, 5}
         */
        sumOfRunTimes = 0;
        for (int i = 0; i < 100; i++) {
            start = System.currentTimeMillis();
            BruteSteinerTree.steinerTree(testGraph3, List.of(0, 3, 4));
            end = System.currentTimeMillis();
            sumOfRunTimes += (end - start);
        }
        avgTime = sumOfRunTimes / 100;
        System.out.printf("Approximate avg execution time of Brute Force Steiner tree algorithm for test graph 3" +
                " using points 0, 3, 4: %1.3f\n", avgTime);
        Thread.sleep(1000);


        /*
        TEST USING TEST GRAPH 4 | Terminal Points: 0, 4, 5
        V = {0, 1, 2, 3, 4, 5}
         */
        sumOfRunTimes = 0;
        for (int i = 0; i < 100; i++) {
            start = System.currentTimeMillis();
            BruteSteinerTree.steinerTree(testGraph4, List.of(0, 4, 5));
            end = System.currentTimeMillis();
            sumOfRunTimes += (end - start);
        }
        avgTime = sumOfRunTimes / 100;
        System.out.printf("Approximate avg execution time of Brute Force Steiner tree algorithm for test graph 4" +
                " using points 0, 4, 5: %1.3f\n", avgTime);
        Thread.sleep(1000);


        /*
        TEST USING TEST GRAPH 5 | Terminal Points: 0, 4, 3
        V = {0, 1, 2, 3, 4}
         */
        sumOfRunTimes = 0;
        for (int i = 0; i < 100; i++) {
            start = System.currentTimeMillis();
            BruteSteinerTree.steinerTree(testGraph5, List.of(0, 4, 3));
            end = System.currentTimeMillis();

            sumOfRunTimes += (end - start);
        }
        avgTime = sumOfRunTimes / 100;
        System.out.printf("Approximate avg execution time of Brute Force Steiner tree algorithm for test graph 5" +
                " using points 0, 4, 3: %1.3f\n", avgTime);
        Thread.sleep(1000);

        /*
        TEST USING TEST GRAPH 6 | Terminal Points: 0, 1, 2, 3
        V = {0, 1, 2, 3, 4, 5, 6, 7, 8}
         */
        sumOfRunTimes = 0;
        for (int i = 0; i < 100; i++) {
            start = System.currentTimeMillis();
            BruteSteinerTree.steinerTree(testGraph6, List.of(0, 1, 2, 3));
            end = System.currentTimeMillis();
            sumOfRunTimes += (end - start);
        }
        avgTime = sumOfRunTimes / 100;
        System.out.printf("Approximate avg execution time of Brute Force Steiner tree algorithm for test graph 6" +
                " using points 0, 1, 2, 3: %1.3f\n\n", avgTime);

        Thread.sleep(1000);

//        ----------- HEURISTIC TESTS -----------
        System.out.printf("HEURISTIC STEINER TREE TESTS\n");
        /*
        TEST USING TEST GRAPH 1 | Terminal Points: 0, 2, 4
        V = {0, 1, 2, 3, 4, 5}
         */
        for (int i = 0; i < 100; i++) {
            start = System.currentTimeMillis();
            HeuristicSteinerTree.steinerTree(testGraph1, List.of(0, 2, 4));
            end = System.currentTimeMillis();
            sumOfRunTimes += (end - start);
        }
        avgTime = sumOfRunTimes / 100;
        System.out.printf("Approximate avg execution time of heuristic Steiner tree algorithm for test graph 1" +
                " using points 0, 2, 4: %1.3f\n", avgTime);
        Thread.sleep(1000);


        /*
        TEST USING TEST GRAPH 2 | Terminal Points: 7, 4, 2, 8
        V = {0, 1, 2, 3, 4, 5, 6, 7, 8}
         */
        sumOfRunTimes = 0;
        for (int i = 0; i < 100; i++) {
            start = System.currentTimeMillis();
            HeuristicSteinerTree.steinerTree(testGraph2, List.of(7, 4, 2, 8));
            end = System.currentTimeMillis();
            sumOfRunTimes += (end - start);
        }
        avgTime = sumOfRunTimes / 100;
        System.out.printf("Approximate avg execution time of heuristic Steiner tree algorithm for test graph 2" +
                " using points 7, 4, 2, 8: %1.3f\n", avgTime);
        Thread.sleep(1000);


        /*
        TEST USING TEST GRAPH 3 | Terminal Points: 0, 3, 4
        V = {0, 1, 2, 3, 4, 5}
         */
        sumOfRunTimes = 0;
        for (int i = 0; i < 100; i++) {
            start = System.currentTimeMillis();
            HeuristicSteinerTree.steinerTree(testGraph3, List.of(0, 3, 4));
            end = System.currentTimeMillis();
            sumOfRunTimes += (end - start);
        }
        avgTime = sumOfRunTimes / 100;
        System.out.printf("Approximate avg execution time of heuristic Steiner tree algorithm for test graph 3" +
                " using points 0, 3, 4: %1.3f\n", avgTime);
        Thread.sleep(1000);


        /*
        TEST USING TEST GRAPH 4 | Terminal Points: 0, 4, 5
        V = {0, 1, 2, 3, 4, 5}
         */
        sumOfRunTimes = 0;
        for (int i = 0; i < 100; i++) {
            start = System.currentTimeMillis();
            HeuristicSteinerTree.steinerTree(testGraph4, List.of(0, 4, 5));
            end = System.currentTimeMillis();
            sumOfRunTimes += (end - start);
        }
        avgTime = sumOfRunTimes / 100;
        System.out.printf("Approximate avg execution time of heuristic Steiner tree algorithm for test graph 4" +
                " using points 0, 4, 5: %f\n", avgTime);
        Thread.sleep(1000);


        /*
        TEST USING TEST GRAPH 5 | Terminal Points: 0, 4, 3
        V = {0, 1, 2, 3, 4}
         */
        sumOfRunTimes = 0;
        for (int i = 0; i < 100; i++) {
            start = System.currentTimeMillis();
            HeuristicSteinerTree.steinerTree(testGraph5, List.of(0, 4, 3));
            end = System.currentTimeMillis();

            sumOfRunTimes += (end - start);
        }
        avgTime = sumOfRunTimes / 100;
        System.out.printf("Approximate avg execution time of heuristic Steiner tree algorithm for test graph 5" +
                " using points 0, 4, 3: %1.3f\n", avgTime);
        Thread.sleep(1000);

        /*
        TEST USING TEST GRAPH 6 | Terminal Points: 0, 1, 2, 3
        V = {0, 1, 2, 3, 4, 5, 6, 7, 8}
         */
        sumOfRunTimes = 0;
        for (int i = 0; i < 100; i++) {
            start = System.currentTimeMillis();
            HeuristicSteinerTree.steinerTree(testGraph6, List.of(0, 1, 2, 3));
            end = System.currentTimeMillis();
            sumOfRunTimes += (end - start);
        }
        avgTime = sumOfRunTimes / 100;
        System.out.printf("Approximate avg execution time of heuristic Steiner tree algorithm for test graph 6" +
                " using points 0, 1, 2, 3: %1.3f\n", avgTime);
        Thread.sleep(1000);
    }
}
