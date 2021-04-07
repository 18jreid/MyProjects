import java.util.Queue;

/**
 * The simulation of the OS takes place here.  The specific scheduling
 * algorithm is passed in as a parameter to the contructor, as this
 * code is unaware of the scheduling specifics.
 */
public class Platform implements Logger {
    private int cpuCount;
    private Process[] running;
    private int clockCounter;

    public Platform(int cpuCount) {
        this.cpuCount = cpuCount;
    }

    /**
     * This is the core of the platform simulation.  It advances by 1 clock count each time
     * through the loop.
     * @param processes
     */
    public void simulate(Scheduler scheduler, Queue<Process> processes) {
        this.running = new Process[cpuCount];
        this.clockCounter = 0;

        boolean done = simulationDone(processes);;
        while (!done) {
            //
            // Look at the queue of incoming processes and see if any of those need to be added to scheduler
            // as newly ready processes.
            boolean doneAdding = false;
            while (!doneAdding) {
                Process p = processes.peek();
                if (p != null && p.getStartTime() <= clockCounter) {
                    scheduler.notifyNewProcess(p);
                    processes.remove();
                }
                else {
                    doneAdding = true;
                }
            }

            //
            // For each CPU, update the state of the running process.
            for (int cpu = 0; cpu < this.cpuCount; cpu++) {
                if (this.running[cpu] != null) {
                    this.running[cpu].update();
                }
                this.running[cpu] = scheduler.update(this.running[cpu]);
            }
            clockCounter++;

            done = simulationDone(processes);
        }
    }

    /**
     * If there are no processes left in the list of processes and all of the CPUs have no
     * running processes, then we are done with the simulation.
     */
    private boolean simulationDone(Queue<Process> processes) {
        boolean allEmpty = true;
        for (Process p : this.running) {
            if (p != null) {
                allEmpty = false;
            }
        }
        return allEmpty && processes.size() == 0;
    }

    /**
     * Implementation of the Logger interface.  Schedulers use this interface to report events.
     */
    public void log(String message) {
        System.out.printf("Time %3d : %s\n", this.clockCounter, message);
    }
}
