package main.graph;

import collections.Dictionary;
import collections.List;
import main.graph.Edge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class Graph {

    private Dictionary<Integer, List<Edge>> neighbors;
    private int edge_size;


    public Graph(){
        neighbors = new Dictionary<>();
    }

    public Graph(File file) throws FileNotFoundException {
        neighbors = new Dictionary<>();

        Scanner file_scanner = new Scanner(file);

        while (file_scanner.hasNext()){
            String line = file_scanner.nextLine();

            String[] split = line.split("\\s+");

            int from_here = Integer.parseInt(split[0]);
            int to_here = Integer.parseInt(split[1]);
            double weight = Double.parseDouble(split[2]);

            Edge edge = new Edge(from_here, to_here, weight);

            addEdge(edge);
        }
    }

    public void addEdge(Edge edge){
        int from_here = edge.fromHere();

        List<Edge> list;

        if (!neighbors.contains(from_here)) {
            list = new List<>();
            list.add(edge);

            neighbors.put(from_here, list);

            edge_size++;
        }
        else{
            list = neighbors.get(from_here);

            if (!list.contains(edge)){
                list.add(edge);

                neighbors.update(from_here, list);

                edge_size++;
            }
        }
    }



    public int getNodeSize(){
        return neighbors.getSize();
    }

    public int getEdgeSize() {
        return edge_size;
    }

    public Iterator<Integer> nodes(){
        return neighbors.keys();
    }

    public Iterator<Edge> neighbors(int node){
        return neighbors.get(node).iterator();
    }
}
