package test;

import main.algorithms.Kruskal;
import main.graph.DirectedGraph;
import main.graph.Edge;

import java.io.File;
import java.util.Scanner;

public class KruskalTest {

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        System.out.print("File Path: ");
        String file_path = input.nextLine();

        File file = new File(file_path);

        DirectedGraph graph = new DirectedGraph(file);

        Kruskal mst_finder = new Kruskal(graph);

        for (Edge edge : mst_finder)
            System.out.println(edge);
    }
}
