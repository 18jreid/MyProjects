/**
 * This interface defines the behaviors required of any scheduler.
 */
public interface Scheduler {
    /**
     * A scheduler must track the number of context switches performed during the simulation.
     * This method returns that count.
     * @return The number of context switches that occurred during the simulation
     */
    int getNumberOfContextSwitches();

    /**
     * Used to notify the scheduler a new process has just entered the ready state.
     */
    void notifyNewProcess(Process p);

    /**
     * Update the scheduling algorithm for a single CPU.
     * @return Reference to the process that is executing on the CPU; result might be null
     *         if no process available for scheduling.
     */
    Process update(Process cpu);
}
