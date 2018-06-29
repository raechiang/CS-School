/**
 * Rachel Chiang
 * 
 * This class is the Shortest First scheduler and extends the Simple
 * Scheduler. Basically it works as a FIFO queue but it is special
 * in that it must sort the table before calling this:
 * {@link #setProcessTable(ProcessQueue)}.
 */
public class ShortestFirst extends SimpleScheduler
{
	/**
	 * The constructor. It has its introductory string and it sorts
	 * the processes using {@link #sortQueue(ProcessQueue)} and then
	 * passes this sorted queue as the argument for the superclass's
	 * {@link #setProcessTable(ProcessQueue)}.
	 */
	public ShortestFirst(ProcessQueue pQ)
	{
		super("shortest first scheduler");
		super.setProcessTable(sortQueue(pQ));
	}
	
	/**
	 * This method sorts the queue, putting the processes with the least
	 * cycles first. It makes a new queue called sortedQueue which is
	 * what it returns.
	 */
	private ProcessQueue sortQueue(ProcessQueue pQ)
	{
		ProcessQueue sortedQueue = new ProcessQueue();
		int unsortedSize = pQ.getSize();
		
		ProcessNode least = null;
		ProcessNode current = null;
		ProcessNode next = null;
		ProcessNode reLink = null;
		
		// if the sorted queue does not contain the same number of elements
		// as the original, loop to add more
		while (sortedQueue.getSize() != unsortedSize)
		{
			current = pQ.getFirst();
			least = pQ.getFirst();
			next = current.getNext();
			
			// cycle through the pQ
			while (next != null)
			{
				if (next.getRemainingCycles() < least.getRemainingCycles())
				{
					// use < and not <= because if two processes have the same
					// remaining cycles, it should take the one that was added
					// to the list first
					reLink = current;
					least = next;
				}
				current = current.getNext();
				next = next.getNext();
			}
			if (least == pQ.getFirst())
			{
				// if it's the first, we can remove it easily
				pQ.removeFirst();
			}
			else
			{
				// otherwise we have to set it differently
				reLink.setNext(least.getNext());
				least.setNext(null);
			}
			
			// finally we add the newest least to the sortedQueue
			sortedQueue.add(least);
		}
		
		return sortedQueue;
	}
	
	
}
