package main.graph;

import main.collections.List;
import main.collections.Queue;
import main.collections.Stack;

import java.util.Iterator;

public class DepthFirstOrder {

    private List<Integer> marked;

    private Queue<Integer> pre;
    private Queue<Integer> post;
    private Stack<Integer> reverse_post;

    public DepthFirstOrder(DirectedGraph graph){
        marked = new List<>();

        pre = new Queue<>();
        post = new Queue<>();
        reverse_post = new Stack<>();

        Iterator<Integer> nodes = graph.nodes();

        while (nodes.hasNext()){
            int node = nodes.next();

            if (!marked.contains(node))
                dfs(graph, node);
        }
    }

    private void dfs(DirectedGraph graph, int source){
        marked.add(source);
        pre.enqueue(source);

        Iterator<Edge> neighbors = graph.neighbors(source);

        while (neighbors.hasNext()) {
            int neighbor = neighbors.next().toHere();

            if (!marked.contains(neighbor))
                dfs(graph, neighbor);
        }

        post.enqueue(source);
        reverse_post.push(source);
    }

    public Iterator<Integer> pre(){
        return pre.iterator();
    }

    public Iterator<Integer> post(){
        return post.iterator();
    }

    public Iterator<Integer> reversePost(){
        return reverse_post.iterator();
    }
}
