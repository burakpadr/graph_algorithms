package test;

import main.graph.Graph;
import main.graph.Edge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);

        System.out.print("File Path: ");
        String file_path = input.nextLine();

        File file = new File(file_path);

        Graph graph = new Graph(file);


    }
}
