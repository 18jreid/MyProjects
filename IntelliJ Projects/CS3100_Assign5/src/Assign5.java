import java.util.LinkedList;
import java.util.Queue;

public class Assign5 {
    private final static int CPU_COUNT = 1;

    public static void main(String[] args) {
        System.out.println("---------------------------------------------------------");
        demoFCFS();
        System.out.println();

        System.out.println("---------------------------------------------------------");
        demoSJF();
        System.out.println();

        System.out.println("---------------------------------------------------------");
        demoSRTF();
        System.out.println();

        System.out.println("---------------------------------------------------------");
        demoPriority();
        System.out.println();

        System.out.println("---------------------------------------------------------");
        demoRR();
        System.out.println();
    }

    /**
     * First-Come, First Served
     */
    private static void demoFCFS() {
        Platform platform = new Platform(CPU_COUNT);
        Queue<Process> processes = new LinkedList<>();
        processes.add(new Process("P1", 0, 24, 48));
        processes.add(new Process("P2", 0, 3, 6));
        processes.add(new Process("P3", 0, 3, 6));


        System.out.println("Starting First Come, First Served CPU scheduling simulation");
        Scheduler scheduler = new SchedulerFCFS(platform);
        platform.simulate(scheduler, processes);
        System.out.printf("Number of context switches: %d\n", scheduler.getNumberOfContextSwitches());
        System.out.println("FCFS CPU scheduling simulation complete");
    }

    /**
     * Shortest Job First
     */
    private static void demoSJF() {
        Platform platform = new Platform(CPU_COUNT);
        Queue<Process> processes = new LinkedList<>();
        processes.add(new Process("P1", 0, 6, 6));
        processes.add(new Process("P2", 0, 8, 8));
        processes.add(new Process("P3", 0, 7, 7));
        processes.add(new Process("P4", 0, 3, 3));

        System.out.println("Starting Shortest Job First CPU scheduling simulation");
        Scheduler scheduler = new SchedulerSJF(platform);
        platform.simulate(scheduler, processes);
        System.out.printf("Number of context switches: %d\n", scheduler.getNumberOfContextSwitches());
        System.out.println("SJF CPU scheduling simulation complete");
    }

    /**
     * Shortest Remaining Time First
     */
    private static void demoSRTF() {
        Platform platform = new Platform(CPU_COUNT);
        Queue<Process> processes = new LinkedList<>();
        processes.add(new Process("P1", 0, 8, 8));
        processes.add(new Process("P2", 1, 4, 4));
        processes.add(new Process("P3", 2, 9, 9));
        processes.add(new Process("P4", 3, 5, 5));

        System.out.println("Starting Shortest Remaining Time First CPU scheduling simulation");
        Scheduler scheduler = new SchedulerSRTF(platform);
        platform.simulate(scheduler, processes);
        System.out.printf("Number of context switches: %d\n", scheduler.getNumberOfContextSwitches());
        System.out.println("SRTF CPU scheduling simulation complete");
    }

    /**
     * Priority
     */
    private static void demoPriority() {
        Platform platform = new Platform(CPU_COUNT);
        Queue<Process> processes = new LinkedList<>();
        processes.add(new Process("P1", 0, 10, 10, 3));
        processes.add(new Process("P2", 0, 1, 1, 1));
        processes.add(new Process("P3", 0, 2, 2, 4));
        processes.add(new Process("P4", 0, 1, 1,5 ));
        processes.add(new Process("P5", 0, 5, 5, 2));

        System.out.println("Starting Priority CPU scheduling simulation");
        Scheduler scheduler = new SchedulerPriority(platform);
        platform.simulate(scheduler, processes);
        System.out.printf("Number of context switches: %d\n", scheduler.getNumberOfContextSwitches());
        System.out.println("Priority CPU scheduling simulation complete");
    }

    /**
     * Round Robin
     */
    private static void demoRR() {
        Platform platform = new Platform(CPU_COUNT);
        Queue<Process> processes = new LinkedList<>();
        processes.add(new Process("P1", 0, 24, 24));
        processes.add(new Process("P2", 0, 3, 3));
        processes.add(new Process("P3", 0, 3, 3));

        System.out.println("Starting Round Robin CPU scheduling simulation");
        Scheduler scheduler = new SchedulerRR(platform, 4);
        platform.simulate(scheduler, processes);
        System.out.printf("Number of context switches: %d\n", scheduler.getNumberOfContextSwitches());
        System.out.println("RR CPU scheduling simulation complete");
    }
}
