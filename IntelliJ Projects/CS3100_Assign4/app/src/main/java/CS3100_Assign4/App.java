// Justin Reid A02276642
// OS & Concurrency
// Assign4

package CS3100_Assign4;

import java.util.ArrayList;
import java.util.Collections;

public class App {
    public String getGreeting() { return "Hello World!"; }

    private static final Runtime runTime = Runtime.getRuntime();
    private static final ArrayList<WorkerThread> workerThreads = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Integer> decimalDigits = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            decimalDigits.add(i);
        }
        Collections.shuffle(decimalDigits);

        TaskQueue myQueue = new TaskQueue(decimalDigits);
        MyTable myTable = new MyTable();

        calculatePI(myQueue, myTable);
    }

    /***
     * Calculates PI up to 1000 decimal places.
     * @param queue queue of decimals places to be calculated.
     * @param table hash table to place calculated decimal places.
     * @throws InterruptedException
     */
    static void calculatePI(TaskQueue queue, MyTable table) throws InterruptedException {
        System.out.print("\nStarting Calculation");
        table.startClock();

        for (int i = 0; i < runTime.availableProcessors(); i++) {
            WorkerThread thread = new WorkerThread(queue, table);

            workerThreads.add(thread);
            thread.start();
        }

        for (Thread thread : workerThreads) {
            thread.join();
        }

        table.printPi();
    }
}
