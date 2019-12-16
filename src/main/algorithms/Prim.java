package main.algorithms;

import main.collections.List;
import main.collections.MinPQ;
import main.collections.Queue;
import main.graph.Component;
import main.graph.DirectedGraph;
import main.graph.Edge;

import java.util.Iterator;

public class Prim implements Iterable<Edge>{

    private List<Integer> marked;
    private MinPQ<Edge> edges;
    private Queue<Edge> mst;

    public Prim(DirectedGraph graph) throws Exception {
        Component component_finder = new Component(graph);

        if (component_finder.getComponentSize() > 1)
            throw new Exception("This Graph Has A Multiple Component!");


        marked = new List<>();
        edges = new MinPQ<>();
        mst = new Queue<>();

        dfs(graph, graph.nodes().next());

        while (!edges.isEmpty()) {
            Edge edge = edges.popMin();

            if (!marked.contains(edge.toHere())) {
                mst.enqueue(edge);

                dfs(graph, edge.toHere());
            }
        }
    }

    private void dfs(DirectedGraph graph, int source) {
        marked.add(source);

        Iterator<Edge> neighbors = graph.neighbors(source);

        while (neighbors.hasNext()) {
            Edge neighbor = neighbors.next();

            if (!marked.contains(neighbor.toHere())) {
                edges.enqueue(neighbor);
            }
        }
    }

    public Iterator<Edge> iterator(){
        return mst.iterator();
    }
}
