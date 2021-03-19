package Assign3;

public class WorkerThread {
    private Runtime runTime = Runtime.getRuntime();
    private TaskQueue myQueue;
    private MyTable myTable;

    WorkerThread(TaskQueue queue, MyTable table) {
        this.myQueue = queue;
        this.myTable = table;
    }
    
    void initiateThreads() {
        myTable.startClock();

        for (int i = 0; i < runTime.availableProcessors(); i++) {
            Thread myThread = new Thread() {
                public synchronized void run() {
                    while(!myQueue.isEmpty()) {
                        myTable.insertDigit(myQueue);
                    }
                }
            };

            myThread.start();
        }
    }
}
