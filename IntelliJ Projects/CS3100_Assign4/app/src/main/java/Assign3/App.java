package Assign3;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        int[] digits = new int[1000];
        for (int i = 0; i < digits.length; i++) {
            digits[i] = i + 1;
        }

        TaskQueue myQueue = new TaskQueue(digits);
        MyTable hashTable = new MyTable();
        WorkerThread worker = new WorkerThread(myQueue, hashTable);

        worker.initiateThreads();
    }
}
