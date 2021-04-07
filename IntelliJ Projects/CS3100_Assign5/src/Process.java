/**
 * Used to represent a Process for the scheduler simulation.  It only holds the information
 * necessary for a simulation, it isn't meant to be some mini version of the code used in
 * writing an actual operating system.
 */
public class Process {
    private String name;        // A name used to unique identify the process for reporting during the simulation
    private int startTime;      // The arrival time, from the start of the simulation, of this process
    private int burstTime;      // Length of the CPU burst
    private int totalTime;      // Total execution time for the process
    private int elapsedBurst = 0;   // How long the current burst has executed
    private int elapsedTotal = 0;   // How long, in total, the process has executed
    private int priority = 0;       // Priority of the process, smaller number is higher priority

    public Process(String name,int startTime, int burstTime, int totalTime) {
        this.name = name;
        this.startTime = startTime;
        this.burstTime = burstTime;
        this.totalTime = totalTime;
    }

    /**
     * Overloaded constructor that also accepts priority; needed by the priority scheduling simulation
     */
    public Process(String name,int startTime, int burstTime, int totalTime, int priority) {
        this(name, startTime, burstTime, totalTime);
        this.priority = priority;
    }

    /**
     * Increment the state of the process
     * @return true if the process still needs time to complete, false otherwise.
     */
    public boolean update() {
        if (this.elapsedBurst == this.burstTime) {
            this.elapsedBurst = 0;
        }
        this.elapsedBurst++;

        this.elapsedTotal++;
        return !(this.elapsedTotal == this.totalTime);
    }

    /**
     * @return true if current burst has completed, false otherwise
     */
    public boolean isBurstComplete() {
        return this.elapsedBurst == this.burstTime;
    }

    /**
     * @return true if the process overall execution time has completed, false otherwise
     */
    public boolean isExecutionComplete() {
        return this.elapsedTotal == this.totalTime;
    }

    /**
     * Various getters for the state of the process.
     */
    public String getName() { return this.name; }
    public int getStartTime() { return this.startTime; }
    public int getBurstTime() { return this.burstTime; }
    public int getTotalTime() { return this.totalTime; }
    public int getElapsedTotal() { return this.elapsedTotal; }
    public int getPriority() { return this.priority; }
    public int getElapsedBurst() { return this.elapsedBurst; }
    public int getRemainingBurst() { return this.burstTime - this.elapsedBurst; }
}
