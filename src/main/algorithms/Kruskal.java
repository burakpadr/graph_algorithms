package main.algorithms;

import main.collections.MinPQ;
import main.collections.Queue;
import main.collections.UnionFind;
import main.graph.Component;
import main.graph.DirectedGraph;
import main.graph.Edge;

import java.util.Iterator;

public class Kruskal implements Iterable<Edge>{

    Queue<Edge> mst;

    public Kruskal(DirectedGraph graph) throws Exception {
        Component component_finder = new Component(graph);

        if (component_finder.getComponentSize() > 1)
            throw new Exception("This Graph Has A Multiple Component!");

        mst = new Queue<>();

        MinPQ<Edge> edges = new MinPQ<>();

        Iterator<Integer> nodes = graph.nodes();

        while (nodes.hasNext()){
            Iterator<Edge> neighbors = graph.neighbors(nodes.next());

            while (neighbors.hasNext())
                edges.enqueue(neighbors.next());
        }

        UnionFind union_find = new UnionFind();

        while (!edges.isEmpty() && mst.getSize() < graph.getNodeSize()){
            Edge edge = edges.popMin();

            if (!union_find.connected(edge.fromHere(), edge.toHere())) {
                mst.enqueue(edge);

                union_find.union(edge.fromHere(), edge.toHere());
            }
        }
    }

    public Iterator<Edge> iterator(){
        return mst.iterator();
    }
}
