/**
 * Rachel Chiang
 * 
 * This simple class represents the nodes for the processes. The 
 * {@link #processID} and {@link #remainingCycles} fields are given by the input
 * file and instantiated in the constructor. There are methods for other classes
 * to adjust all of the fields except for the {@link #processID}.
 */
public class ProcessNode
{
	/**
	 * {@link #processID} and {@link #remainingCycles} are provided by the file 
	 * input, though to be more exact, the ProcessQueue class handles parsing 
	 * and passing the information to the nodes. These are all instantiated 
	 * with the constructor and two of them can be set otherwise.
	 */
	private int processID;
	private int remainingCycles;
	private ProcessNode next;
	
	/**
	 * The constructor. The parameters pid and totalCycles instantiate the 
	 * fields {@link #processID} and {@link #remainingCycles} respectively. The
	 * {@link #next} field is set to null and is meant to be changed later on if
	 * a new ProcessNode is queued or an existing one is requeued.
	 */
	public ProcessNode(int pid, int totalCycles)
	{
		processID = pid;
		remainingCycles = totalCycles;
		next = null;
	}
	
	/**
	 * This method allows external classes to adjust the {@link #next} node.
	 */
	public void setNext(ProcessNode n)
	{
		next = n;
	}
	
	/**
	 * This method allows external classes to reduce {@link #remainingCycles} by
	 * a given quantum. The {@link #remainingCycles} cannot be negative, so it 
	 * is protected by an if statement. It also returns by how many cycles a 
	 * process's remaining cycles has been reduced by.
	 */
	public int reduceRemaining(int quantum)
	{
		if ((remainingCycles - quantum) < 0)
		{
			int actualReduction = remainingCycles;
			remainingCycles = 0;
			return actualReduction;
		}
		else
		{
			remainingCycles -= quantum;
			return quantum;
		}
	}
	
	/**
	 * This method simply tells if a process has finished executing based off of
	 * how many {@link #remainingCycles} there are.
	 */
	public boolean isFinished()
	{
		if (remainingCycles == 0)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Getters.
	 * @return
	 */
	public int getProcessID()
	{
		return processID;
	}
	public int getRemainingCycles()
	{
		return remainingCycles;
	}
	public ProcessNode getNext()
	{
		return next;
	}
}