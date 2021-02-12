public class Recursion {
    public static void main(String[] args) {

        int[] sumMe = { 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89 };
        System.out.printf("Array Sum: %d\n", arraySum(sumMe, 0));

        int[] minMe = { 0, 1, 1, 2, 3, 5, 8, -42, 13, 21, 34, 55, 89 };
        System.out.printf("Array Min: %d\n", arrayMin(minMe, 0));

        String[] amISymmetric =  {
                "You can cage a swallow can't you but you can't swallow a cage can you",
                "I still say cS 1410 is my favorite class"
        };
        for (String test : amISymmetric) {
            String[] words = test.toLowerCase().split(" ");
            System.out.println();
            System.out.println(test);
            System.out.printf("Is word symmetric: %b\n", isWordSymmetric(words, 0, words.length - 1));
        }

        double weights[][] = {
                { 51.18 },
                { 55.90, 131.25 },
                { 69.05, 133.66, 132.82 },
                { 53.43, 139.61, 134.06, 121.63 }
        };
        System.out.println();
        System.out.println("--- Weight Pyramid ---");
        for (int row = 0; row < weights.length; row++) {
            for (int column = 0; column < weights[row].length; column++) {
                double weight = computePyramidWeights(weights, row, column);
                System.out.printf("%.2f ", weight);
            }
            System.out.println();
        }

        char image[][] = {
                {'*','*',' ',' ',' ',' ',' ',' ','*',' '},
                {' ','*',' ',' ',' ',' ',' ',' ','*',' '},
                {' ',' ',' ',' ',' ',' ','*','*',' ',' '},
                {' ','*',' ',' ','*','*','*',' ',' ',' '},
                {' ','*','*',' ','*',' ','*',' ','*',' '},
                {' ','*','*',' ','*','*','*','*','*','*'},
                {' ',' ',' ',' ',' ',' ',' ',' ','*',' '},
                {' ',' ',' ',' ',' ',' ',' ',' ','*',' '},
                {' ',' ',' ','*','*','*',' ',' ','*',' '},
                {' ',' ',' ',' ',' ','*',' ',' ','*',' '}
        };
        int howMany = howManyOrganisms(image);
        System.out.println();
        System.out.println("--- Labeled Organism Image ---");
        for (char[] line : image) {
            for (char item : line) {
                System.out.print(item);
            }
            System.out.println();
        }
        System.out.printf("There are %d organisms in the image.\n", howMany);

    }

    // Checks if words can be flipped and mean the same thing
    public static boolean isWordSymmetric(String[] words, int start, int end) {
        if (words.length == 0) {
            return true;
        }

        else if ((words[start].toLowerCase()).equals(words[end].toLowerCase())) {
            if (start == end) {
                return true;
            }
            start++;
            end--;

            return isWordSymmetric(words, start, end);
        }

        else {
            return false;
        }
    }

    // Adds every int inside the array
    public static long arraySum(int[] data, int position) {
        if (data.length == 0) {
            return 0;
        }

        else if (position != data.length) {
            return data[position] + arraySum(data, position + 1);
        }

        else {
            return 0;
        }
    }

    // Finds lowest number in int array
    public static int arrayMin(int[] data, int position) {
        if (data.length == 0) {
            return 0;
        }

        else if (position != data.length - 1) {
            return Math.min(data[position], arrayMin(data, position + 1));
        }

        else {
            return data[position];
        }
    }

    // Computes weight at specific point
    public static double computePyramidWeights(double[][] weights, int row, int column) {
        if (weights[0].length == 0 || weights.length <= row || weights[row].length <= column){
            return 0;
        }

        // only returns top of pyramids weight
        else if (row == 0){
            return weights[0][0];
        }

        else if (column == 0){
            return computePyramidWeights(weights, row - 1, column) / 2 + weights[row][column];
        }

        else if (column == weights[row].length - 1){
            return computePyramidWeights(weights, row - 1, column - 1) / 2 + weights[row][column];
        }

        else {
            return computePyramidWeights(weights, row - 1, column)/2 + computePyramidWeights(weights, row - 1, column - 1) / 2 + weights[row][column] + weights[row][column];
        }
    }

    // Checks each spot around the specific cell
    public static void checkAround(char[][]image, int column, int row, char letter) {
        image[row][column] = letter;

        if (!(image.length - 1 < row + 1)) {
            if (image[row + 1][column] == '*') {
                checkAround(image, column, row + 1, letter); // below
            }
        }

        if ((row != 0 && image[row - 1].length - 1 >= column)) {
            if (image[row - 1][column] == '*') {
                checkAround(image, column, row - 1, letter); // above
            }
        }

        if (!(image[row].length - 1 < column + 1)) {
            if (image[row][column + 1] == '*') {
                checkAround(image, column + 1, row, letter); // right
            }
        }

        if (!(column == 0)) {
            if (image[row][column - 1] == '*') {
                checkAround(image, column - 1, row, letter); // left
            }
        }
    }

    // Checks how many organisms are in the picture from instrucrtions rules
    public static int howManyOrganisms(char[][] image){
        int count = 0;
        char[] characters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        for (int x = 0; x < image.length; x++) {
            for (int y = 0; y < image[x].length; y++) {
                if (image[x][y] == '*'){
                    checkAround(image, y, x, characters[count]);

                    count += 1;
                }
            }
        }
        return count;
    }
}
