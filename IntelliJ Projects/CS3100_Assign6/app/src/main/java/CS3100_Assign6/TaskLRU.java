package CS3100_Assign6;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

public class TaskLRU implements Runnable {
    int[] sequence;
    int[] maxMemoryFrames;
    int maxPageReference;
    int[] pageFaults;

    LinkedList<Integer> queue = new LinkedList<>();

    public TaskLRU(int[] sequence, int maxMemoryFrames, int maxPageReference, int[] pageFaults) {
        this.sequence = sequence;
        this.maxMemoryFrames = new int[maxMemoryFrames];
        this.maxPageReference = maxPageReference;
        this.pageFaults = pageFaults;
    }

    @Override
    public void run() {
        pageFaults[this.maxMemoryFrames.length] = simulate();
    }

    /***
     * Simulates FIFO page replacement schema.
     * @return number of page faults.
     */
    private int simulate() {
        int faults = 0;

        for (int page : this.sequence) {
            if (!isFound(page)) {
                if (hasFreeMemory()) {
                    for (int i = 0; i < this.maxMemoryFrames.length; i++) {
                        if (this.maxMemoryFrames[i] == 0) {
                            this.maxMemoryFrames[i] = page;
                            faults++;

                            queue.add(i);
                            break;
                        }
                    }
                }
                else {
                    int index = queue.pop();

                    this.maxMemoryFrames[index] = page;
                    faults++;

                    queue.add(index);
                }
            }
            else {
                int index = queue.pop();
                queue.add(index);
            }
        }

        return faults;
    }

    /***
     * Checks if MemoryFrames has a free slot of memory.
     * @return bool of whether MemoryFrames has free slot of memory.
     */
    private boolean hasFreeMemory() {
        for (int i = 0; i < this.maxMemoryFrames.length; i++) {
            if (this.maxMemoryFrames[i] == 0) {
                return true;
            }
        }

        return false;
    }

    /***
     * Searchs for num in memory slot.
     * @param num number to be found.
     * @return bool of whether num was found.
     */
    private boolean isFound(int num) {
        for (int i = 0; i < this.maxMemoryFrames.length; i++) {
            if (this.maxMemoryFrames[i] == num) {
                return true;
            }
        }

        return false;
    }
}
