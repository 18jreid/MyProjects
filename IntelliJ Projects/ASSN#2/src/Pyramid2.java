import java.util.Scanner;

public class Pyramid2 {
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

    // Prints the entire pyramid for the user.
    private static void printPyramid(int numOfLines) {
        int startNum = 1;
        int currentLine = 1;

        // Loop that prints pyramid, each loop is one line
        for (int i = 1; i <= numOfLines; i++) {
            String space = "";

            // Loop that prints correct spacing for the line
            for (int x = numOfLines; x >= currentLine; x--) {
                System.out.printf("%" + findSpaces(numOfLines) + "s", space);
            }

            // Loop that prints left side of the line
            for (int j = 1; j < startNum; j *= 2) {
                System.out.printf("%" + findSpaces(numOfLines) + "d", j);
            }

            // Loop that prints right side of the line
            for (int k = startNum; k >= 1; k /= 2) {
                System.out.printf("%" + findSpaces(numOfLines) + "d", k);
            }
            startNum *= 2;
            currentLine += 1;
            System.out.println();
        }

    }

    // Finds maximum number possible out of the lines given
    private static int maxNum(int numOfLines) {
        int maxNumber = 1;
        for (int i = 1; i < numOfLines; i++) {
            maxNumber *= 2;
        }

        return maxNumber;
    }

    // Finds the correct spacing for numbers based off of the max number.
    private static int findSpaces(int numOfLines) {
        String temp = maxNum(numOfLines) + " ";

        return temp.length();
    }
}
