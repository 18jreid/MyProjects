// Justin Reid
// A02276642
// OS & Concurrency
// Assignment 5

import java.util.LinkedList;
import java.util.Queue;

public class SchedulerRR extends SchedulerBase implements Scheduler {
    private Platform platform;
    private int quantumTime;
    private Queue<Process> queue = new LinkedList<>();

    public SchedulerRR(Platform platform, int i) {
        this.platform = platform;
        this.quantumTime = i;
    }

    /***
     * gets number of context switches.
     * @return number of context switches.
     */
    @Override
    public int getNumberOfContextSwitches() {
        return contextSwitches;
    }

    /***
     * Adds new process to queue.
     * @param p process to be added.
     */
    @Override
    public void notifyNewProcess(Process p) {
        queue.add(p);
    }

    /***
     * Updates the cpu based on the RR algorithm.
     * @param cpu cpu to be updated.
     * @return returns the state of the cpu.
     */
    @Override
    public Process update(Process cpu) {
        Process nextProcess = null;

        if (cpu != null) {
            if (queue.peek() != null) {
                if (cpu.getElapsedTotal() > this.quantumTime) {
                    platform.log("Time quantum completed for Process " + cpu.getName());
                    contextSwitches++;
                    queue.add(cpu);

                    platform.log("Scheduled: " + queue.peek().getName());
                    contextSwitches++;
                    return queue.poll();
                }
            }
            // Check if execution is completed
            if (!cpu.isExecutionComplete()) {
                nextProcess = cpu;
            }
            else {
                platform.log("Process " + cpu.getName() + " execution complete");
                contextSwitches++;

                // Grab next process to be updated
                if (queue.peek() != null) {
                    platform.log("Scheduled:" + queue.peek().getName());
                    nextProcess = queue.poll();
                    contextSwitches++;
                }
            }
        }
        else if (queue.peek() != null) {
            platform.log("Scheduled: " + queue.peek().getName());
            nextProcess = queue.poll();
            contextSwitches++;
        }

        return nextProcess;
    }
}
