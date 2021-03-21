package CS3100_Assign4;

public class WorkerThread extends Thread{
    private final TaskQueue myQueue;
    private final MyTable myTable;

    WorkerThread(TaskQueue queue, MyTable table) {
        this.myQueue = queue;
        this.myTable = table;
    }

    /***
     * Runs thread. Grabs decimal digit to be calculated from queue, and inserts it into the hash table.
     */
    public void run() {
        while(!myQueue.isEmpty()) {
            int myDigit;
            synchronized (myQueue.queue) {
                myDigit = myQueue.queue.poll();
            }

            myTable.insertDigit(myDigit);
        }
    }
}
