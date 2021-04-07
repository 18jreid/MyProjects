/**
 * Really hated to make such a small base class, but did so in order to
 * not repeat this simple boilerplate code needed for all schedulers.
 * All Scheduler classes must track how many context switches they
 * perform so they can be reported at the end of the simulation.
 */
public class SchedulerBase {
    protected int contextSwitches = 0;

    public int getNumberOfContextSwitches() { return this.contextSwitches; }
}
