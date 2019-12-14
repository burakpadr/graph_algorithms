package main.graph;

import main.collections.List;
import main.collections.Dictionary;
import main.graph.DepthFirstOrder;

import java.util.Iterator;

public class Component {

    private List<Integer> marked;
    private Dictionary<Integer, Integer> id;
    private int component_size;

    public Component(DirectedGraph graph){
        marked = new List<>();
        id = new Dictionary<>();

        Iterator<Integer> order = new DepthFirstOrder(graph.reverse()).reversePost();

        while (order.hasNext()){
            int node = order.next();

            if (!marked.contains(node))
                component_size++;

                dfs(graph, node);
        }
    }

    private void dfs(DirectedGraph graph, int source){
        marked.add(source);
        id.put(source, component_size);

        Iterator<Edge> neighbors = graph.neighbors(source);

        while (neighbors.hasNext()){
            int neighbor = neighbors.next().toHere();

            if (!marked.contains(neighbor))
                dfs(graph, neighbor);
        }
    }

    public boolean stronglyConnected(int first_node, int second_node){
        return id.get(first_node).equals(id.get(second_node));
    }

    public int getComponent(int node){
        return id.get(node);
    }

    public int getComponentSize(){
        return component_size;
    }
}
