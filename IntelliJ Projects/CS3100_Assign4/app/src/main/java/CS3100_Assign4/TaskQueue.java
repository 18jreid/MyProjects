package CS3100_Assign4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class TaskQueue {
    public final Queue<Integer> queue = new LinkedList<>();

    TaskQueue(ArrayList<Integer> tasks) {
        for (int task : tasks) {
            queue.offer(task);
        }
    }

    /***
     * Checks if queue is empty.
     * @return boolean value of whether the queue is empty.
     */
    Boolean isEmpty() {
        return queue.isEmpty();
    }
}
