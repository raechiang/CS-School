/**
 * Rachel Chiang
 */
import java.util.Random;

/**
 * This is the random scheduler. This scheduler assigns each process a
 * "chance", which is a process's remainingCycles divided by all process'
 * total remainingCycles. A process is thus more likely to be selected if it
 * has more remainingCycles. A random number generator picks a number between
 * 1 and the total remainingCycles, and whichever process corresponds with the
 * number is executed next. It is the only one that does not use the FIFO
 * attribute of the queue.
 */
public class RandomScheduler extends QuantumScheduler
{
	private ProcessNode current;
	
	/**
	 * Constructor.
	 */
	public RandomScheduler(ProcessQueue pQ, int quantum)
	{
		super("random scheduler with quantum " + quantum, quantum);
		super.setProcessTable(pQ);
		current = null;		
	}
	
	/**
	 * This method runs the process picked by {@link #selectProscess()}.
	 */
	@Override
	public void runProcess()
	{
		current = selectProcess();
		
		int reducedBy = current.reduceRemaining(super.getQuantum());
		super.increaseCycles(reducedBy);
		
		if (current.isFinished())
		{
			super.adjustTurnaround();
			System.out.println("Process " + current.getProcessID() 
					+ " finishes on cycle " + super.getCycles() + ".");
			relink();
		}
		else
		{
			if (current != super.getProcessTable().getLast())
			{
				relink();
				super.getProcessTable().add(current);
			}
		}
	}
	
	/**
	 * This method randomly selects a process. A number between 1 and the
	 * total remaining cycles (found using the help of
	 * {@link #findTotalRemaining()}) is randomly picked, called the
	 * "raffle" below. A determinator is held to find the range for a given
	 * process. Each process corresponds to an index in a two-dimensional
	 * array based on the order they are in in the queue. Note, the order
	 * does not affect the likelihood a process will be selected, so it is
	 * arbitrary how they correspond with the array. The array's
	 * dimensions are [the process table's size] by [2]. The array merely
	 * exists to keep the "range" of a process. The process which has a range
	 * that contains the raffle is returned.
	 */
	private ProcessNode selectProcess()
	{
		Random rng = new Random();
		int remaining = findTotalRemaining();
		int raffle = rng.nextInt(remaining) + 1;
		int determinator = 0;
		ProcessNode current = super.getProcessTable().getFirst();
		ProcessNode pick = current;
		
		// finding the ranges and filling the array of processes and their
		// ranges
		int[][] processes = new int[super.getProcessTable().getSize()][2];
		for (int i = 0; i < super.getProcessTable().getSize(); ++i)
		{
			// lower bound
			processes[i][0] = determinator;
			determinator += current.getRemainingCycles();
			// upper bound
			processes[i][1] = determinator;
			current = current.getNext();
		}
		
		// This loop checks the range against the process and returns the
		// corresponding process
		for (int index = 0; index < super.getProcessTable().getSize(); ++index)
		{
			if (raffle > processes[index][0] && raffle <= processes[index][1])
			{
				current = super.getProcessTable().getFirst();
				for (int i = 0; i < index; ++i)
				{
					current = current.getNext();
				}
				return current;
			}
		}
		
		return pick;
	}
	
	/**
	 * This method simply goes through all the processes and adds up
	 * the remaining cycles.
	 */
	private int findTotalRemaining()
	{
		int remaining = 0;
		ProcessNode cur = super.getProcessTable().getFirst();
		while (cur != null)
		{
			remaining += cur.getRemainingCycles();
			cur = cur.getNext();
		}
		return remaining;
	}
	
	/**
	 * This method relinks the process. It is called by {@link #runProcess()}
	 */
	private void relink()
	{
		if (current == super.getProcessTable().getFirst())
		{
			// if it's the first one it can just be dequeued simply
			super.getProcessTable().removeFirst();
		}
		else
		{
			// otherwise we have to fix the links arond it
			ProcessNode cur = super.getProcessTable().getFirst();
			while (cur.getNext() != current)
			{
					cur = cur.getNext();
			}
			cur.setNext(current.getNext());
			current.setNext(null);
		}
	}
}
