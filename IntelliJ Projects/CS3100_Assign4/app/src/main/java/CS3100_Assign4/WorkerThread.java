package CS3100_Assign4;

public class WorkerThread extends Thread{
    private final TaskQueue myQueue;
    private final MyTable myTable;

    WorkerThread(TaskQueue queue, MyTable table) {
        this.myQueue = queue;
        this.myTable = table;
    }

    public synchronized void run() {
        while(!myQueue.isEmpty()) {
            int myDigit = (int) myQueue.queue.poll();
            myTable.insertDigit(myDigit);
        }
    }
}
