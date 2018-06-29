/**
 * This class sits just below the parent, Scheduler, class and sits parallel to
 * the QuantumScheduler. This pretty much covers the two simple schedulers'
 * general process execution. To be exact, this class is the parent of the
 * FirstcomeFirstServe scheduler and the ShortestFirst scheduler.
 */
public abstract class SimpleScheduler extends Scheduler
{
	/**
	 * No fields in this class to really instantiate except that it must 
	 * instantiate the parent constructor, which requires a simple String
	 * argument which is hard-coded into the subclasses themselves.
	 */
	public SimpleScheduler(String type)
	{
		super(type);
	}
	
	/**
	 * This method implements the superclass's abstract method runProcess().
	 * Simple schedulers simply run through the entire process completely before
	 * moving on to the next one; they are non-preemptive. Thus, I chose
	 * laziness and simply dropped the currently executing process to 0 cycles
	 * remaining and added the number of cycles it was reduced by to the total
	 * number of cycles the scheduler has run through.
	 */
	@Override
	public void runProcess()
	{
		// FIFO
		ProcessNode current = super.getProcessTable().getFirst();
		
		// How much a process is reduced by = how many cycles were remaining
		int reducedBy = current.reduceRemaining(current.getRemainingCycles());
		// increase the total cycles by how many cycles have "passed"
		super.increaseCycles(reducedBy);
		
		// If the process is done
		if (current.isFinished())
		{
			// We change the turnaround fields (for the average that will be
			// calculated at the end), print out the process's finishing cycle,
			// and remove the process from the table.
			super.adjustTurnaround();
			System.out.println("Process " + current.getProcessID() 
					+ " finishes on cycle " + super.getCycles() + ".");
			super.getProcessTable().removeFirst();
		}
	}
}
