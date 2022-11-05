public class WeightedEdge {
    int node1;
    int node2;
    int weight;

    public WeightedEdge(int node1, int node2, int weight) {
        this.node1 = node1;
        this.node2 = node2;
        this.weight = weight;
    }

    public WeightedEdge(int node1, int node2) {
        this.node1 = node1;
        this.node2 = node2;
        weight = 0;
    }

    @Override
    public String toString() {
        return String.format("[ %d - %d | weight: %d]", node1, node2, weight);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof WeightedEdge that)) return false;

        if (node1 != that.node1 && node1 != that.node2) return false;
        if (node2 != that.node2 && node2 != that.node1) return false;
        return weight == that.weight;
    }

    @Override
    public int hashCode() {
        int result = (node1 + node2);
        result = 31 * result + (node2 + node1);
        result = 31 * result + weight;
        return result;
    }
}
