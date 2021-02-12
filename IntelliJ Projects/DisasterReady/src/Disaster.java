import java.util.Scanner;

public class Disaster {
    // Find city connected to most cities
    private static int findGreedyIndex(Graph graph, SupplySolution solution) {
        int highestConnections = 0;
        int location = 0;

        for (int i = 0; i < graph.vertexCt; i++) {
            if (!solution.supplies[i]) {
                if (graph.G[i].succCt >= highestConnections) {
                    highestConnections = graph.G[i].succCt;
                    location = i;
                }
            }
        }

        return location;
    }

    // Place supplies at location (FOR GREEDY SOLUTION)
    private static void greedyPlaceSupplies(Graph graph, SupplySolution solution) {
        int supplyLocation = findGreedyIndex(graph, solution);

        solution.supplies[supplyLocation] = true;
        solution.supplyCt += 1;
        connectSupplies(solution, graph.G[supplyLocation].succ.head);
    }

    // Returns the supply count at the most connected city
    private static int amountOfSuppliesFromGreedy(Graph graph) {
        SupplySolution solution = new SupplySolution(graph.vertexCt);

        while (!allSupplied(solution)) {
            greedyPlaceSupplies(graph, solution);
        }

        findNeedToCover(solution);

        return solution.supplyCt;
    }

    // Cover supplies from a supplied city
    private static void connectSupplies(SupplySolution solution, Node head) {
        solution.covered[head.data] = true;

        if (head.next != null) {
            connectSupplies(solution, head.next);
        }
    }

    // Check if all cities are supplied
    private static boolean allSupplied(SupplySolution solution) {
        for (int i = 0; i < solution.vertexCt; i++) {
            if (!solution.covered[i]) {
                return false;
            }
        }

        return true;
    }

    // Check how many cities are needed to be covered
    private static void findNeedToCover(SupplySolution solution) {
        int needtoCover = solution.vertexCt;
        for (int i = 0; i < solution.vertexCt; i++) {
            if (solution.supplies[i] || solution.covered[i]) {
                needtoCover -= 1;
            }
        }
        solution.needToCover = needtoCover;
    }

    // Recursive function that checks every possibility 2^n
    private static SupplySolution getBestSupplySolution(Graph graph, int nodeId, int allowed, SupplySolution partialSolution) {
        if (nodeId >= partialSolution.vertexCt) return partialSolution;
        if (allowed <= 0) {return partialSolution;}

        SupplySolution useIt = getBestSupplySolution(graph, nodeId + 1, allowed - 1, recursiveAddSupply(graph, partialSolution, nodeId));
        SupplySolution dontUse = getBestSupplySolution(graph, nodeId + 1, allowed, partialSolution);

        return betterOfSupplies(useIt, dontUse);
    }

    // Returns the better of two solutions, whichever has most covered and least supplied
    private static SupplySolution betterOfSupplies(SupplySolution useIt, SupplySolution dontUse) {
        if (useIt.needToCover < dontUse.needToCover) {
            return useIt;
        }
        else if (dontUse.needToCover < useIt.needToCover){
            return dontUse;
        }
        else {
            if (useIt.supplyCt < dontUse.supplyCt) {
                return useIt;
            }
            else {
                return dontUse;
            }
        }
    }

    // Adds supplies to a specific node (FOR RECURSIVE SOLUTION)
    private static SupplySolution recursiveAddSupply(Graph graph, SupplySolution partialSolution, int nodeId) {
        SupplySolution solution = new SupplySolution(partialSolution);
        solution.supplies[nodeId] = true;
        solution.covered[nodeId] = true;
        solution.supplyCt += 1;
        connectSupplies(solution, graph.G[nodeId].succ.head);
        findNeedToCover(solution);

        return solution;
    }

    public static void main(String[] args) {
        // List of all files available to user
        String[] files  = {"Tester.txt", "Tester2.txt","WesternUS.txt","BritishIsles.txt","NortheastUS.txt", "CentralEurope.txt", "IberianPeninsula.txt",
                "SouthernNigeria.txt", "SouthernSouthKorea.txt", "NortheastUS2.txt", "SouthernUS.txt"};

        // Ask user for file name, print out all available files
        Scanner userInput = new Scanner(System.in);
        System.out.println("Which area would you like to see a disaster plan for? (Enter file name, Example: BritishIsles.txt)\n");

        // Print out list (I couldn't figure it out with a loop so I gave up)
        System.out.print(files[0] + " ");
        System.out.print(files[1] + " ");
        System.out.print(files[2] + "\n");

        System.out.print(files[3] + " ");
        System.out.print(files[4] + " ");
        System.out.print(files[5] + "\n");

        System.out.print(files[6] + " ");
        System.out.print(files[7] + " ");
        System.out.print(files[8] + "\n");

        System.out.print(files[9] + " ");
        System.out.print(files[10] + "\n\n");

        String fileName = userInput.nextLine();
        System.out.println();

        // Ask user which solution type to use
        System.out.println("Greedy Solution (1), or Best Solution (2) (Best solution takes more time depending on how many cities)");
        int algorithmChoice = userInput.nextInt();

        Graph graph1 = new Graph();
        graph1.makeGraph(fileName);

        System.out.println(graph1.toString());
        System.out.println();

        // Executes if user wants greedy solution
        if (algorithmChoice == 1) {
            long startTime = System.nanoTime();
            System.out.println("-------------------------------------");
            System.out.println(fileName + "  GREEDY SOLUTION ");
            System.out.println("-------------------------------------");

            SupplySolution solution = new SupplySolution(graph1.vertexCt);
            while (!allSupplied(solution)) {
                greedyPlaceSupplies(graph1, solution);
            }

            findNeedToCover(solution);

            System.out.println(solution.toString());
            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            System.out.println("The program took " + timeElapsed / 1000000000 + " seconds / " + timeElapsed / 1000000 + " milliseconds!");
        }

        // Executes if user wants the best solution (will take more time)
        else {
            long startTime = System.nanoTime();
            System.out.println("-------------------------------------");
            System.out.println(fileName + "  BEST SOLUTION ");
            System.out.println("-------------------------------------");
            SupplySolution solution = new SupplySolution(graph1.vertexCt);

            solution = getBestSupplySolution(graph1, 0, amountOfSuppliesFromGreedy(graph1), solution);
            System.out.println(solution.toString());
            long endTime = System.nanoTime();
            long timeElapsed = endTime - startTime;
            System.out.println("The program took " + timeElapsed / 1000000000 + " seconds / " + timeElapsed / 1000000 + " milliseconds!");
        }
    }
}
