package main.graph;

import java.util.Objects;

public class Edge implements Comparable<Edge>{

    private int from_here;
    private int to_here;
    private double weight;

    public Edge(int from_here, int to_here, double weight){
        this.from_here = from_here;
        this.to_here = to_here;
        this.weight = weight;
    }

    public int fromHere(){
        return from_here;
    }

    public int toHere(){
        return to_here;
    }

    public double weight(){
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return from_here == edge.from_here &&
                to_here == edge.to_here &&
                weight == edge.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(from_here, to_here, weight);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from_here=" + from_here +
                ", to_here=" + to_here +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Edge edge){
        return Double.compare(this.weight, edge.weight);
    }

}
