import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<File> myFiles  = new ArrayList<>();
        myFiles.add(new File("data/bellman0.txt"));
        myFiles.add(new File("data/group0.txt"));
        myFiles.add(new File("data/group1.txt"));
        myFiles.add(new File("data/group4.txt"));
        myFiles.add(new File("data/group5.txt"));
        myFiles.add(new File("data/group6.txt"));
        myFiles.add(new File("data/group7.txt"));
        myFiles.add(new File("data/group8.txt"));

        System.out.println("    Max-Flow Program");
        System.out.println("Pick a file: ");
        System.out.println("1) bellman0.txt");
        System.out.println("2) group0.txt");
        System.out.println("3) group1.txt");
        System.out.println("4) group4.txt");
        System.out.println("5) group5.txt");
        System.out.println("6) group6.txt");
        System.out.println("7) group7.txt");
        System.out.println("8) group8.txt");
        System.out.println("9) ALL FILES");

        Scanner inputScanner = new Scanner(System.in);  // Create a Scanner object
        int userFileIdInput = inputScanner.nextInt();
        if (userFileIdInput != 9) {
            System.out.println("\n" + myFiles.get(userFileIdInput - 1).getName());
            FlowGraph myGraph = new FlowGraph();
            myGraph.makeGraph("data/" + myFiles.get(userFileIdInput - 1).getName());

            MaxFlow maxFlow = new MaxFlow();
            maxFlow.FordFulkerson(myGraph, myGraph.G[0]);
        }
        else {
            for (File file : myFiles) {
                System.out.println("\n" + file.getName());
                FlowGraph myGraph = new FlowGraph();
                myGraph.makeGraph("data/" + file.getName());

                MaxFlow maxFlow = new MaxFlow();
                maxFlow.FordFulkerson(myGraph, myGraph.G[0]);
            }
        }
    }
}
