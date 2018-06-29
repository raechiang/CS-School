/**
 * This class simply holds the information for the blocks of any of a process.
 */
public class BlockStatus
{
	private int start;
	private int timeRemaining;
	
	/**
	 * Constructor. Start is the pc at which the process begins this block and
	 * duration is the amount of cycles the process should block for.
	 */
	public BlockStatus(int start, int duration)
	{
		this.start = start;
		timeRemaining = duration;
	}
	
	/**
	 * Simple getters.
	 */
	public int getStart()
	{
		return start;
	}
	public int getRemaining()
	{
		return timeRemaining;
	}
	
	/**
	 * Reduces the duration. When it reaches zero, the block will be removed.
	 */
	public void reduceRemaining(int iterator)
	{
		System.out.print(timeRemaining + " -> ");
		int newTR = timeRemaining - iterator;
		if (newTR < 0)
		{
			timeRemaining = 0;
		}
		else
		{
			timeRemaining = newTR;
		}
		System.out.println(timeRemaining);
	}

}
