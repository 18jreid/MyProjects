package CS3100_Assign4;

import java.util.LinkedList;
import java.util.Queue;

public class TaskQueue {
    public final Queue<Integer> queue = new LinkedList<>();

    TaskQueue(int[] tasks) {
        for (int task : tasks) {
            queue.offer(task);
        }
    }

    Boolean isEmpty() {
        return queue.isEmpty();
    }
}
