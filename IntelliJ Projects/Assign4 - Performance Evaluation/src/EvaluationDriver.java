public class EvaluationDriver {
    static final int MAX_VALUE = 1_000_000;
    static final int MAX_ARRAY_SIZE = 100_000;
    static final int ARRAY_INCREMENT = 20_000;
    static final int NUMBER_SEARCHES = 50_000;

    public static void main(String[] args) {

        demoLinearSearchUnsorted();
        demoLinearSearchSorted();
        demoBinarySearchSelectionSort();
        demoBinarySearchFastSort();

    }

    /**
     * Demonstrates linear searching over an unsorted array
     *
     */
    public static void demoLinearSearchUnsorted() {
        System.out.println("--- Linear Search Timing (unsorted) ---");

        // Loops through max array size by array increment
        for (int i = ARRAY_INCREMENT; i <= MAX_ARRAY_SIZE; i += ARRAY_INCREMENT) {
            int[] list = generateNumbers(i, MAX_VALUE);
            int timesFound = 0;

            long startTime = System.currentTimeMillis();
            for (int j = 1; j < NUMBER_SEARCHES; j++) {
                int randomMax = (int)(Math.random() * MAX_VALUE);
                boolean found = linearSearch(list, randomMax);

                if (found) {
                    timesFound += 1;
                }
            }
            long endTime = System.currentTimeMillis();
            long timeElapsed = endTime - startTime;

            System.out.println("Number of items : " + list.length);
            System.out.println("Times value was found : " + timesFound);
            System.out.println("Total search time : " + timeElapsed + " ms\n");
        }
    }

    /**
     * Demonstrates linear searching over a sorted array
     *
     */
    public static void demoLinearSearchSorted() {
        System.out.println("--- Linear Search Timing (Selection Sort) ---");

        // Loops through max array size by array increment
        for (int i = ARRAY_INCREMENT; i <= MAX_ARRAY_SIZE; i += ARRAY_INCREMENT) {
            int[] list = generateNumbers(i, MAX_VALUE);
            int timesFound = 0;

            long startTime = System.currentTimeMillis();

            // Sorts list
            selectionSort(list);

            for (int j = 1; j < NUMBER_SEARCHES; j++) {
                int randomMax = (int)(Math.random() * MAX_VALUE);
                boolean found = linearSearch(list, randomMax);

                if (found) {
                    timesFound += 1;
                }
            }
            long endTime = System.currentTimeMillis();
            long timeElapsed = endTime - startTime;

            System.out.println("Number of items : " + list.length);
            System.out.println("Times value was found : " + timesFound);
            System.out.println("Total search time : " + timeElapsed + " ms\n");
        }
    }

    /**
     * Demonstrates binary searching when using a Selection Sort
     *
     */

    public static void demoBinarySearchSelectionSort() {
        System.out.println("--- Binary Search Timing (Selection Sort) ---");

        // Loops through max array size by array increment
        for (int i = ARRAY_INCREMENT; i <= MAX_ARRAY_SIZE; i += ARRAY_INCREMENT) {
            int[] newList = generateNumbers(i, MAX_VALUE);
            int timesFound = 0;

            long startTime = System.currentTimeMillis();
            selectionSort(newList);
            for (int j = 1; j < NUMBER_SEARCHES; j++) {
                int randomMax = (int)(Math.random() * MAX_VALUE);
                boolean found = binarySearch(newList, randomMax);

                if (found) {
                    timesFound += 1;
                }
            }
            long endTime = System.currentTimeMillis();
            long timeElapsed = endTime - startTime;

            System.out.println("Number of items : " + newList.length);
            System.out.println("Times value was found : " + timesFound);
            System.out.println("Total search time : " + timeElapsed + " ms\n");
        }
    }

    /**
     * Demonstrates binary searching when using the build in Arrays.sort
     *
     */
    public static void demoBinarySearchFastSort() {
        System.out.println("--- Binary Search Timing (Arrays.sort) ---");

        // Loops through max array size by array increment
        for (int i = ARRAY_INCREMENT; i <= MAX_ARRAY_SIZE; i += ARRAY_INCREMENT) {
            int[] newList = generateNumbers(i, MAX_VALUE);
            int timesFound = 0;

            long startTime = System.currentTimeMillis();
            java.util.Arrays.sort(newList);
            for (int j = 1; j < NUMBER_SEARCHES; j++) {
                int randomMax = (int)(Math.random() * MAX_VALUE);
                boolean found = binarySearch(newList, randomMax);

                if (found) {
                    timesFound += 1;
                }
            }
            long endTime = System.currentTimeMillis();
            long timeElapsed = endTime - startTime;

            System.out.println("Number of items : " + newList.length);
            System.out.println("Times value was found : " + timesFound);
            System.out.println("Total search time : " + timeElapsed + " ms\n");
        }
    }

    // Generates random numbers given howMany and the maxValue
    public static int[] generateNumbers(int howMany, int maxValue) {
        if (howMany <= 0) {
            return null;
        }
        else {
            int[] numbersList = new int[howMany];

            for (int i = 0; i < howMany; i++) {
                numbersList[i] = (int)(Math.random() * maxValue);
            }

            return numbersList;
        }
    }

    // Performs linear search from provided list and search value
    public static boolean linearSearch(int[] data, int search) {
        boolean result = false;
        for (int i = 0; i <= data.length - 1; i++) {
            if (data[i] == search) {
                result = true;
                break;
            }
        }

        return result;
    }

    // Performs binary search given list and search value, must be sorted before used
    public static boolean binarySearch(int[] data, int search) {
        boolean result = false;

        int low = 0;
        int high = data.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (data[mid] < search) {
                low = mid + 1;
            }
            else if (data[mid] > search){
                high = mid - 1;
            }
            else if (data[mid] == search) {
                result = true;
                return result;
            }
        }

        return result;
    }

    // Sorts list given the list provided
    public static int[] selectionSort(int[] data) {
        for (int i = 0; i < data.length - 1; i++)
        {
            int counter = i;

            for (int j = i + 1; j < data.length; j++){
                if (data[j] < data[counter]){
                    counter = j;
                }
            }
            int smallerNumber = data[counter];

            data[counter] = data[i];
            data[i] = smallerNumber;
        }

        return data;
    }
}
