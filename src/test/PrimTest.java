package test;

import main.algorithms.Prim;
import main.graph.DirectedGraph;
import main.graph.Edge;

import java.io.File;
import java.util.Scanner;

public class PrimTest {

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        System.out.print("File Path: ");
        String file_path = input.nextLine();

        File file = new File(file_path);

        DirectedGraph graph = new DirectedGraph(file);

        Prim mst_finder = new Prim(graph);

        for (Edge edge : mst_finder)
            System.out.println(edge);
    }
}
