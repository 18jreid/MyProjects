package Assign3;

import java.util.LinkedList;
import java.util.Queue;

public class TaskQueue {
    public final Queue<Integer> queue = new LinkedList<>();

    TaskQueue(int[] tasks) {
        for (int task : tasks) {
            queue.offer(task);
        }
    }

    int poll() {
        return this.queue.poll();
    }

    Boolean isEmpty() {
        return queue.isEmpty();
    }
}
