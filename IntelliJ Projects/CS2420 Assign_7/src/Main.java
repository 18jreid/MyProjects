import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter text file: ");
        System.out.println("1) case1.txt");
        System.out.println("2) case2.txt");
        System.out.println("3) case3.txt");
        System.out.println("4) case4.txt");
        System.out.println("5) case5.txt");
        System.out.println("6) case6.txt");
        System.out.println("7) case7.txt");

        int usersFile = myObj.nextInt();  // Read user input
        System.out.println("File chosen is: " + usersFile);  // Output user input

        String fileName = "case" + usersFile + ".txt";
        FileInputStream fc = new FileInputStream(fileName);
        Scanner sc = new Scanner(fc);

        int arraySize = sc.nextInt();
        UnionFind rivals = new UnionFind(arraySize);

        System.out.println("Input "  + fileName + " of size " + arraySize);
        while (sc.hasNextLine()) {
            int data1 = sc.nextInt();
            int data2 = sc.nextInt();

            attack(rivals.myArray[data1 - 1], rivals.myArray[data2 - 1], rivals);
        }

        rivals.printArray();
        rivals.printGroups();
        System.out.println("The largest group is of size " + rivals.findBiggest());
        System.out.println();
    }

    /**
     * Takes two nodes and checks if they have the same root, and adds opponents accordingly
     * @param node1 first node
     * @param node2 second node
     * @param rivals provides the unionFind array data
     */
    public static void attack(Node node1, Node node2, UnionFind rivals) {
        if (rivals.find(node1) != rivals.find(node2)) {
            addOpponent(node1, node2, rivals);
            addOpponent(node2, node1, rivals);

            System.out.println("Attack " + node1.id + " " + node2.id);
        }
        else {
            System.out.println("Ignored Attack " + node1.id + " " + node2.id);
        }
    }

    /**
     * Takes two nodes, and makes one the opponent of the other, then takes the first nodes opponents and unions them
     * with the opponent.
     * @param node node being attacked
     * @param opponent node attacking
     * @param rivals unionFind array data
     */
    public static void addOpponent(Node node, Node opponent, UnionFind rivals) {
        if (!node.opponents.isEmpty()) {
            for (Node opponentNode : node.opponents) {
                if (rivals.find(opponentNode) != rivals.find(opponent)) {
                    rivals.union(rivals.find(opponentNode), rivals.find(opponent));
                }
            }
        }
        node.opponents.add(opponent);
    }
}
