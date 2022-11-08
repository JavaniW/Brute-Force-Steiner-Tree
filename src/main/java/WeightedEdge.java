public class WeightedEdge implements Comparable<WeightedEdge>{
//    one of the nodes that make up the edge
    int node1;
//    one of the nodes that make up the edge
    int node2;
//    weight of the edge
    int weight;

    /**
     * creates an edge with specified endpoints and weight
     *
     * @param node1 first node of the edge
     * @param node2 second node of the edge
     * @param weight weight of the edge
     */
    public WeightedEdge(int node1, int node2, int weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }

    /**
     * creates an edge with specified endpoints and a weight of 0
     *
     * @param node1 first node of the edge
     * @param node2 second node of the edge
     */
    public WeightedEdge(int node1, int node2) {
        this.node1 = node1;
        this.node2 = node2;
        weight = 0;
    }

    @Override
    public String toString() {
        return String.format("[ %d - %d | weight: %d]", node1, node2, weight);
    }

    /**
     * determines if this object and another are equal
     *
     * @param o object that this object is being compared to
     * @return true if the objects are equivalent
     */
    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof WeightedEdge that)) return false;

        if (node1 != that.node1 && node1 != that.node2) return false;
        if (node2 != that.node2 && node2 != that.node1) return false;
        return weight == that.weight;
    }

    /**
     * creates and returns hashcode of this object
     *
     * @return hashcode of the edge
     */
    @Override
    public int hashCode() {
        int result = (node1 + node2);
        result = 31 * result + (node2 + node1);
        result = 31 * result + weight;
        return result;
    }

    @Override
    public int compareTo(WeightedEdge o) {
        return this.weight - o.weight;
    }
}
