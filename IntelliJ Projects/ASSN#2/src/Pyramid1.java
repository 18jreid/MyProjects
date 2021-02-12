import java.util.Scanner;

public class Pyramid1 {
    public static void main(String[] args) {
        int numberOfLines = getUserInput();
        printPyramid(numberOfLines);
    }

    // Asks user how many lines they want
    private static int getUserInput() {
        System.out.print("Enter the number of lines: ");
        Scanner input = new Scanner(System.in);

        return input.nextInt();
    }

    // Prints the entire pyramid based off of users input
    private static void printPyramid(int numOfRows) {
        // Loop that prints one line, then the next
        int spacingCount = numOfRows;
        String space = "";

        for (int i = 1; i <= numOfRows; i++) {
            // Prints correct spacing for the line
            for (int x = (spacingCount); x >= 1; x--) {
                System.out.printf("%" + (findNumOfSpaces(numOfRows)) + "s", space);
            }
            // Loop that prints the left side of the line
            for (int j = i; j > 1; j--) {
                System.out.printf("%" + findNumOfSpaces(numOfRows) + "d", j);
            }

            // Loop that prints right side of the line
            for (int k = 1; k <= i; k++) {
                System.out.printf("%" + findNumOfSpaces(numOfRows) + "d", k);
            }
            System.out.println();
            spacingCount -= 1;
        }
    }

    // Find spacing for lines
    private static int findNumOfSpaces(int numOfRows) {
        String temp = Integer.toString(numOfRows);

        return temp.length() + 1;
    }
}
