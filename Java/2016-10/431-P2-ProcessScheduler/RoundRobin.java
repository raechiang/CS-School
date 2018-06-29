/**
 * Rachel Chiang
 * 
 * This is the round robin scheduler. It's a fairly simple scheduler because
 * it still has the FIFO way of moving through the cycles. This scheduler 
 * simply grabs the first in the queue, "executes" it for the quantum, and
 * requeues or removes it depending on whether it is done or not. Then repeats
 * the cycle.
 */
public class RoundRobin extends QuantumScheduler
{
	/**
	 * The constructor.
	 */
	public RoundRobin(ProcessQueue pQ, int quantum)
	{
		super("round robin scheduler with quantum " + quantum, quantum);
		super.setProcessTable(pQ);
	}
	
	/**
	 * A fairly simple scheduler. It just iterates through them and reduces
	 * the remainingCycles by the quantum.
	 */
	@Override
	public void runProcess()
	{
		ProcessNode current = super.getProcessTable().getFirst();
		int reducedBy = current.reduceRemaining(super.getQuantum());
		super.increaseCycles(reducedBy);
		
		if (current.isFinished())
		{
			super.adjustTurnaround();
			System.out.println("Process " + current.getProcessID() 
					+ " finishes on cycle " + super.getCycles() + ".");
			super.getProcessTable().removeFirst();
		}
		else
		{
			super.getProcessTable().removeFirst();
			super.getProcessTable().add(current);
		}
	}
}
